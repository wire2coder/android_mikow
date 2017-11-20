package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        words.add("eleven");

        // make a LinearLayout
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for( int i=0; i < words.size(); i++) {
//            TextView view1 = new TextView(this);
//            view1.setText(words.get(i));
//            rootView.addView(view1);
//        }

        // make ArrayAdapter (data type)
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        // reference ListView in XML
        ListView listView = (ListView) findViewById(R.id.list);
        // put ArrayAdapter inside the XML
        listView.setAdapter(itemsAdapter);


    } // onCreate
}
