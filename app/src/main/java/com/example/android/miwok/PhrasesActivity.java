package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);

        ArrayList<Word> phrases_array = new ArrayList<Word>();

        phrases_array.add( new Word("Where are you going?", "minto wuksus"));
        phrases_array.add( new Word("What is your name?", "tinnә oyaase'nә"));
        phrases_array.add( new Word("My name is ...", "oyaaset..."));
        phrases_array.add( new Word("How are you feeling?", "michәksәs?"));
        phrases_array.add( new Word("I am feeling good.", "kuchi achit"));
        phrases_array.add( new Word("Are you coming?", "әәnәs'aa?"));
        phrases_array.add( new Word("Yes, I am coming.", "hәә’ әәnәm"));
        phrases_array.add( new Word("I am coming.", "әәnәm"));
        phrases_array.add( new Word("Let us go", "yoowutis"));
        phrases_array.add( new Word("Come here", "әnni'nem"));

        WordAdapter adapter = new WordAdapter(this, phrases_array);

        ListView listView = (ListView) findViewById(R.id.listview_for_all);
        listView.setAdapter(adapter);

    }
}
