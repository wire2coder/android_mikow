package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);

        final ArrayList<Word> phrases_array = new ArrayList<Word>();

        phrases_array.add( new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrases_array.add( new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrases_array.add( new Word("My name is ...", "oyaaset...", R.raw.phrase_my_name_is));
        phrases_array.add( new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases_array.add( new Word("I am feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrases_array.add( new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrases_array.add( new Word("Yes, I am coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrases_array.add( new Word("I am coming.", "әәnәm", R.raw.phrase_im_coming));
        phrases_array.add( new Word("Let us go", "yoowutis", R.raw.phrase_lets_go));
        phrases_array.add( new Word("Come here", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this, phrases_array, R.color.category_phrases);


        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        listView.setAdapter(adapter);

        // set onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word one_word = phrases_array.get(position);

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, one_word.getmSoundResourceId());
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
