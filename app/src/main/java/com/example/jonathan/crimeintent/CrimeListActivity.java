package com.example.jonathan.crimeintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

    // Activity used to create the crime list fragment
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
