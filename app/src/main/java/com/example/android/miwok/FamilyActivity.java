package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class FamilyActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // pause audio
                        mMediaPlayer.pause();
                        // play audio from the beginning
                        mMediaPlayer.seekTo(0);
                    } else if ( focusChange == AudioManager.AUDIOFOCUS_GAIN ) {
                        // resume playback
                        mMediaPlayer.start();
                    } else if ( focusChange == AudioManager.AUDIOFOCUS_LOSS ) {
                        // lost audio control permanent
                        // clean up the audio player object
                        releaseMediaPlayer();

                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_for_all);

        // get Android system service for audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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

                Word one_word = family_array.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // got auido control
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, one_word.getmSoundResourceId() );
                    mMediaPlayer.start();

                    // setup a listener on the media player, so that we can stop and release
                    // the media player once the sounds finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
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

            // Abandon audio focus when playback is completed
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

}
