package com.anilxpert.food.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.fragments.RegisterFragment;
import com.anilxpert.food.fragments.SigninFragment;
import com.anilxpert.food.loopjServcice.Constant;
import com.anilxpert.food.utils.Utils;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class OrderSummryActivity extends AppCompatActivity {
    private TextView activity_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.app_name));


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
}
