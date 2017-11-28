package com.example.android.miwok;


public class ReportCard {

    int mEnglishGrade;
    int mMathGrade;
    int mBiologyGrade;

    String mFirstName;
    String mLastName;

    // Constructor
    public ReportCard(int englishGrade, int mathGrade, int biologyGrade) {
        mEnglishGrade = englishGrade;
        mMathGrade = mathGrade;
        mBiologyGrade = biologyGrade;
    }

    @Override
    public String toString() {
        return mFirstName + " " + mLastName + " " + "your English grade is: " + mEnglishGrade;
    }

    public int getmEnglishGrade() {
        return mEnglishGrade;
    }

    public void setmEnglishGrade(int mEnglishGrade) {
        this.mEnglishGrade = mEnglishGrade;
    }

    public int getmMathGrade() {
        return mMathGrade;
    }

    public void setmMathGrade(int mMathGrade) {
        this.mMathGrade = mMathGrade;
    }

    public int getmBiologyGrade() {
        return mBiologyGrade;
    }

    public void setmBiologyGrade(int mBiologyGrade) {
        this.mBiologyGrade = mBiologyGrade;
    }

}
