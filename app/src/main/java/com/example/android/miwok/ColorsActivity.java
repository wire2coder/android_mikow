package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_for_all);

        // 1. make an ArrayList
        ArrayList<Word> colorsArray = new ArrayList<Word>();

        // 2. make copy of Word.java
        Word color1 = new Word("red", "weṭeṭṭi");

        // 2.1 add word to the array
        colorsArray.add(color1);
         // 2.2 short cut
        colorsArray.add(new Word("green", "chokkoki"));
        colorsArray.add(new Word("brown", "chokkoki"));
        colorsArray.add(new Word("grey", "ṭakaakki"));
        colorsArray.add(new Word("black", "ṭopoppi"));
        colorsArray.add(new Word("white", "kelelli"));
        colorsArray.add(new Word("dusty yellow", "ṭopiisә"));
        colorsArray.add(new Word("mustard yellow", "chiwiiṭә"));

        // 3. something WordAdapter
        WordAdapter adapter1 = new WordAdapter(this, colorsArray);

        // 4. something ListView
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // 5. User WordAdapter.java in ListView
        listView.setAdapter(adapter1);


    } // onCreate

} // class
