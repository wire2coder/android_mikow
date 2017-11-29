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
        family_array.add(new Word("father", "әpә", R.drawable.family_father));
        family_array.add(new Word("mother", "әṭa", R.drawable.family_mother));
        family_array.add(new Word("son", "angsi", R.drawable.family_son));
        family_array.add(new Word("daughter", "tune", R.drawable.family_daughter));
        family_array.add(new Word("older brother", "taachi", R.drawable.family_older_brother));
        family_array.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother));
        family_array.add(new Word("older sister", "teṭe", R.drawable.family_older_sister));
        family_array.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister));
        family_array.add(new Word("grandmother", "ama", R.drawable.family_grandmother));
        family_array.add(new Word("grandfather", "paapa", R.drawable.family_grandfather));

        // 3. make a new ArrayAdapter
        WordAdapter adapter1 = new WordAdapter(this, family_array);

        // 4. find the ListView
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // 5. use ListView with ArrayAdapter
        listView.setAdapter(adapter1);

    }
}
