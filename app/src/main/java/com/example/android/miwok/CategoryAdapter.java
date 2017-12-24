package com.example.android.miwok;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
     * each list item based on a data source which is a list of {@link Word} objects.
     */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else if (position == 3) {
            return mContext.getString(R.string.category_phrases);
        }

        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else if (position == 3) {
            return new PhrasesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


} // class
