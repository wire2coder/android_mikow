package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);


        // 1. make collection
        ArrayList<Word> family_array = new ArrayList<Word>();

        // 2. make words, add words to collection
        family_array.add(new Word("father", "әpә"));
        family_array.add(new Word("mother", "әṭa"));
        family_array.add(new Word("son", "angsi"));
        family_array.add(new Word("daughter", "tune"));
        family_array.add(new Word("older brother", "taachi"));
        family_array.add(new Word("younger brother", "chalitti"));
        family_array.add(new Word("older sister", "teṭe"));
        family_array.add(new Word("younger sister", "kolliti"));
        family_array.add(new Word("grandmother", "ama"));
        family_array.add(new Word("grandfather", "paapa"));

        // 3. make a new ArrayAdapter
        WordAdapter adapter1 = new WordAdapter(this, family_array);

        // 4. find the ListView
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // 5. use ListView with ArrayAdapter
        listView.setAdapter(adapter1);

    }
}
