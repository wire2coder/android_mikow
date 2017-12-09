package com.example.android.miwok;


public class Word {

    // add a Class variable and make it's value unchangable
    private static final int NO_IMAGE_PROVIDED = -1;

    // 2 private states(variables)
    private String mDeafultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mSoundResourceId;

    // constructor
    public Word(String defaultTranslation, String miwokTranslation) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    // constructor number 2
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    // constructor number 3
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int soundResourceId) {
        mDeafultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mSoundResourceId = soundResourceId;
    }

    public String getmDeafultTranslation() {
        return mDeafultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() { return mImageResourceId; }

    public int getmSoundResourceId() { return mSoundResourceId; }

    public boolean hasImage() {
        return mImageResourceId != -1;
    }



} // class
