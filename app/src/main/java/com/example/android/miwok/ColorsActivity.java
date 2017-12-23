package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // make a new AudioManager object
    private AudioManager mAudioManager;

    // make a new listener for
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // make another listener for
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause audio
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN ) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS ) {
                releaseMediaPlayer();
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // get system service for audio manager
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> colorsArray = new ArrayList<Word>();
        colorsArray.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorsArray.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));
        colorsArray.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        colorsArray.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorsArray.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorsArray.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorsArray.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colorsArray.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        // 3. something WordAdapter
        WordAdapter adapter1 = new WordAdapter(this, colorsArray, R.color.category_colors);

        // 4. something ListView
        ListView listView = (ListView) findViewById(R.id.list);

        // 5. User WordAdapter.java in ListView
        listView.setAdapter(adapter1);

        // 6. set onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word one_word = colorsArray.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();


                // handle audiofocus
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_GAIN) {

                    // got audio focus
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, one_word.getAudioResourceId());
                    mMediaPlayer.start();

                    // setup a listener on the media player, so that we can stop and releae the
                    // media player once the sounds finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }

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

        // Regardless of whether or not we were granted audio focus, abandon it. This also
        // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

} // class
