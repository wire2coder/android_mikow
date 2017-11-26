package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_numbers.xml
        setContentView(R.layout.activity_numbers);

//        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Word> alist1 = new ArrayList<Word>();

        // make a new word object
        Word word1 = new Word("one", "lutti");

        // put word1 object into array words
        alist1.add(word1);
        alist1.add(new Word("two", "otiiko")) ;
        alist1.add(new Word("three", "tolookosu"));


        // make ArrayAdapter <data type>
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
        WordAdapter adapter = new WordAdapter(this, alist1);

        // make a new ArrayAdapter, (context, data source)
//        WordAdapter adapter = new WordAdapter(this, words);

        // reference ListView in XML
        ListView listView = (ListView) findViewById(R.id.list);

        // put ArrayAdapter inside the XML
        listView.setAdapter(adapter);



    } // onCreate
}
