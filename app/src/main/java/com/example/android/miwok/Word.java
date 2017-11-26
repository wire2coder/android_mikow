package com.example.android.miwok;


public class Word {

    // 2 private states(variables)
    private String mDeafultTranslation;
    private String mMiwokTranslation;

    // constructor
    public Word(String defaultTranslation, String miwokTranslation) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public String getmDeafultTranslation() {
        return mDeafultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

} // class
