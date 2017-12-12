package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
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

import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class NumbersActivity extends AppCompatActivity {

    // global variable

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // listview_for_all.xml
        setContentView(R.layout.listview_for_all);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//        ArrayList<String> words = new ArrayList<String>();
        final ArrayList<Word> number_array = new ArrayList<Word>();

        // make a new word object and put word1 object into array words
        number_array.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        number_array.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        number_array.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        number_array.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        number_array.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        number_array.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        number_array.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        number_array.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        number_array.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        number_array.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        // make ArrayAdapter <data type>
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item_for_wordadapter, words);
        WordAdapter adapter = new WordAdapter(this, number_array, R.color.category_numbers);

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

                Word one_word = number_array.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream, CONSTANT value in the AudioManager.java
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus, CONSTANT value in the AudioManager.java
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // got audio control

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, one_word.getmSoundResourceId());
                    mMediaPlayer.start();

                    // setup a listener on the media player, so that we can stop and releae the
                    // media player once the sounds finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });


    } // onCreate


    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    } // onStop


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
