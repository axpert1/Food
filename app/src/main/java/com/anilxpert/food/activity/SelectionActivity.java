package com.anilxpert.food.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anilxpert.food.R;
import com.anilxpert.food.utils.Utils;

/**
 * Created by AnilXpert 9887230800 on 11/21/2017.
 */

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignup;
    private Button btnRegister;
    private Button btnMenu;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        mContext = SelectionActivity.this;
        inslisization();
    }

    private void inslisization() {
        btnSignup = (Button) findViewById(R.id.btnSignUp);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnMenu = (Button) findViewById(R.id.btnMenu);


        btnSignup.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                Utils.startActivityPutValue(SelectionActivity.this, LogRegActivity.class,  getString(R.string.str_sign_in));
                break;
            case R.id.btnRegister:
                Utils.startActivityPutValue(SelectionActivity.this, LogRegActivity.class,  getString(R.string.str_register));

                break;
            case R.id.btnMenu:
                Utils.startActivity(mContext, DashBordActivity.class);
                break;
        }

    }
}
