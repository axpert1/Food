package com.anilxpert.food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;

public class Splace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_splace);
        threadSplace();
    }

    private void threadSplace() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if (SharedPref.getSP(ConstantField.USER_ID) == null) {
                        Utils.clearPriviousActivity(Splace.this, SelectionActivity.class);
                    } else {
                        Utils.clearPriviousActivity(Splace.this, DashBordActivity.class);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
