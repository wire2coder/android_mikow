package com.example.android.miwok;


public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;

    // constructor
    public Word(String defaultTranslation, String miworkTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miworkTranslation;
    }

    // get deafault translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    // get Miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

} // class
