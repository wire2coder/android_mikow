package com.example.android.miwok;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    // constructor
    public WordAdapter (Context context, ArrayList<Word> words, int backGroundColor)
    {
        super(context, 0, words);

        mColorResourceId = backGroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml_wordadapter.xml layout with the ID miwok_text_view.
        TextView miwok_text_view = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        miwok_text_view.setText( currentWord.getmMiwokTranslation() );

        TextView default_text_view = (TextView) listItemView.findViewById(R.id.default_text_view);
        default_text_view.setText( currentWord.getmDeafultTranslation() );

        // Find the ImageView
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {

            // get the resource ID of the image
            iconView.setImageResource( currentWord.getmImageResourceId() );
            // set the ImageView to be visible
            iconView.setVisibility(View.VISIBLE);

        } else {

            // since GONE is a CLASS variable, so you access it by View.GONE
            iconView.setVisibility(View.GONE);
        }

        // Set background color for the linear layout
        View text_container = listItemView.findViewById(R.id.text_container);

        // find the color that resource ID map to
        int color1 = ContextCompat.getColor(getContext(), mColorResourceId);

        // set the background color
        text_container.setBackgroundColor(color1);

        return listItemView;
    }

} // class
