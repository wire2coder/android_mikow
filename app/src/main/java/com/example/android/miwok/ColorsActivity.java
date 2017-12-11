package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);

        // 1. make an ArrayList
        final ArrayList<Word> colorsArray = new ArrayList<Word>();

        // 2. make copy of Word.java
        Word color1 = new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red);

        // 2.1 add word to the array
        colorsArray.add(color1);
         // 2.2 short cut
        colorsArray.add(new Word("green", "greenkoki", R.drawable.color_green, R.raw.color_green));
        colorsArray.add(new Word("brown", "chokkoki", R.drawable.color_brown, R.raw.color_brown));
        colorsArray.add(new Word("grey", "ṭakaakki", R.drawable.color_gray, R.raw.color_gray));
        colorsArray.add(new Word("black", "ṭopoppi", R.drawable.color_black, R.raw.color_black));
        colorsArray.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorsArray.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsArray.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // 3. something WordAdapter
        WordAdapter adapter1 = new WordAdapter(this, colorsArray, R.color.category_colors);

        // 4. something ListView
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // 5. User WordAdapter.java in ListView
        listView.setAdapter(adapter1);

        // 6. set onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word one_word = colorsArray.get(position);

                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, one_word.getmSoundResourceId());
                mMediaPlayer.start();

                // setup a listener on the media player, so that we can stop and releae the
                // media player once the sounds finished playing.
                mMediaPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }
        });

    } // onCreate


    // make the player stop playing when you navigate out of the program.
    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }


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
