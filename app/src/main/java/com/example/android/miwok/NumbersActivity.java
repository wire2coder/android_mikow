package com.example.android.miwok;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

// import media player class
// make a new media player object
// make a new onClick listener
import android.media.MediaPlayer;
import android.widget.Toast;

public class NumbersActivity extends AppCompatActivity {

    // global variable
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // listview_for_all.xml
        setContentView(R.layout.listview_for_all);

//        ArrayList<String> words = new ArrayList<String>();
        final ArrayList<Word> alist1 = new ArrayList<Word>();

        // make a new word object
        // Word word1 = new Word("one", "lutti");

        // put word1 object into array words
        alist1.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        alist1.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        alist1.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        alist1.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        alist1.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        alist1.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        alist1.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        alist1.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        alist1.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        alist1.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        // make ArrayAdapter <data type>
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item_for_wordadapter, words);
        WordAdapter adapter = new WordAdapter(this, alist1, R.color.category_numbers);

        // make a new ArrayAdapter, (context, data source)
//        WordAdapter adapter = new WordAdapter(this, words);

        // reference ListView in XML
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // put ArrayAdapter inside the XML
        listView.setAdapter(adapter);

        // make an onClick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word one_word = alist1.get(position);

                // toString
                // Log.v("NumberActivities", "Current word: " + one_word);

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, one_word.getmSoundResourceId());
                mMediaPlayer.start();

                // setup a listener on the media player, so that we can stop and releae the
                // media player once the sounds finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


    } // onCreate


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }


} // class
