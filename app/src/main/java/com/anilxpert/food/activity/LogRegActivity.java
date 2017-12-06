package com.anilxpert.food.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.fragments.RegisterFragment;
import com.anilxpert.food.fragments.SigninFragment;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.Utils;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class LogRegActivity extends AppCompatActivity {
    private Fragment fragment = null;
    private TextView activity_title;
    private String intentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_reg);
        intentValue = Utils.getintentString(ConstantField.INTENT_1, getIntent());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(intentValue);
        if (intentValue.equals(getString(R.string.str_sign_in))) {
            fragment = new SigninFragment();
            setScreen(fragment);
        } else {
            fragment = new RegisterFragment();
            setScreen(fragment);
        }

    }

    public void setScreen(Fragment fragment) {
        if (fragment != null) {
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);

            Utils.replaceFrg(LogRegActivity.this, fragment, true, R.id.fmContainerLog);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}