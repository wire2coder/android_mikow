package com.example.android.miwok;


public class Word {

    // add a Class variable and make it's value unchangable
    private static final int NO_IMAGE_PROVIDED = -1;

    // 2 private states(variables)
    private String mDeafultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // constructor
    public Word(String defaultTranslation, String miwokTranslation) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    // constructor number 2
    public Word(String defaultTranslation, String miwokTranslation, int resourceId) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = resourceId;
    }

    public String getmDeafultTranslation() {
        return mDeafultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() { return mImageResourceId; }

    public boolean hasImage() {
        return mImageResourceId != -1;
    }



} // class
