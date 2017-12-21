package com.anilxpert.food.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class TermsActivity extends BaseActivity_ {
    private TextView activity_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms);
        setupToolbar(getString(R.string.n_terms_of_use));
        initialize();
    }

    private void initialize() {

    }


}