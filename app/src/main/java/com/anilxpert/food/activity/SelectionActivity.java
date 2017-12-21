package com.anilxpert.food.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;

import static com.anilxpert.food.loopjServcice.ConstantField.LOGIN_RESULT;

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
                Utils.startActivityPutValueResultCode(mContext, LogRegActivity.class, getString(R.string.str_sign_in), LOGIN_RESULT);

                break;
            case R.id.btnRegister:
                Utils.startActivityPutValueResultCode(mContext, LogRegActivity.class, getString(R.string.str_register), LOGIN_RESULT);

                break;
            case R.id.btnMenu:
                Utils.startActivity(mContext, DashBordActivity.class);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantField.LOGIN_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                Utils.startActivity(mContext, DashBordActivity.class);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        }


    }
}
