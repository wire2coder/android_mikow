package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // listview_for_all.xml
        setContentView(R.layout.listview_for_all);

//        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Word> alist1 = new ArrayList<Word>();

        // make a new word object
        Word word1 = new Word("one", "lutti");

        // put word1 object into array words
        alist1.add(word1);
        alist1.add(new Word("two", "otiiko")) ;
        alist1.add(new Word("three", "tolookosu"));
        alist1.add(new Word("four", "oyyisa"));
        alist1.add(new Word("five", "massokka"));
        alist1.add(new Word("six", "temmokka"));
        alist1.add(new Word("seven", "kenekaku"));
        alist1.add(new Word("eight", "kawinta"));
        alist1.add(new Word("nine", "wo’e"));
        alist1.add(new Word("ten", "na’aacha"));


        // make ArrayAdapter <data type>
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item_for_wordadapter, words);
        WordAdapter adapter = new WordAdapter(this, alist1);

        // make a new ArrayAdapter, (context, data source)
//        WordAdapter adapter = new WordAdapter(this, words);

        // reference ListView in XML
        ListView listView = (ListView) findViewById(R.id.listview_for_all);

        // put ArrayAdapter inside the XML
        listView.setAdapter(adapter);



    } // onCreate
}
