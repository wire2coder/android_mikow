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

public class NumbersActivity extends AppCompatActivity {

    // global variable
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT) {
                        https://youtu.be/1i2BqetT70I?t=3m36s
                    }
                }
            }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // listview_for_all.xml
        setContentView(R.layout.listview_for_all);

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

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus/control for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream, CONSTANT value in the AudioManager.java
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus, CONSTANT value in the AudioManager.java
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    // got audio control

                    Word one_word = number_array.get(position);
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
    }


} // class
