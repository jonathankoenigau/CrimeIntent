package com.example.jonathan.crimeintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    // Activity used to create the crime input fragment
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
