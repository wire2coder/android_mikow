package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    // global variables
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);


        // 1. make collection
        final ArrayList<Word> family_array = new ArrayList<Word>();

        // 2. make words, add words to collection
        family_array.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        family_array.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        family_array.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        family_array.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        family_array.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        family_array.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        family_array.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        family_array.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        family_array.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        family_array.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        // 3. make a new ArrayAdapter
        WordAdapter adapter1 = new WordAdapter(this, family_array, R.color.category_family);

        // 4. find the ListView
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // 5. use ListView with ArrayAdapter
        listView.setAdapter(adapter1);

        // 6. make an onClick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word one_word = family_array.get(position);

                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, one_word.getmSoundResourceId());
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

}
