package com.example.android.miwok;


public class Word {

    // 2 private states(variables)
    private String mDeafultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId;

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

    public int getmImageResourceId() { return mImageResourceId; }

} // class
