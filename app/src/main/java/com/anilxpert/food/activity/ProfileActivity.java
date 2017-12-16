package com.anilxpert.food.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;


/**
 * Created by this pc on 11/23/2017.
 */

public class ProfileActivity extends AppCompatActivity implements TextWatcher {
    private TextView activity_title;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;


    private EditText txtFName;
    private EditText txtLName;
    private TextView txtEmail;
    private EditText txtPhone;

    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        mContext = ProfileActivity.this;
        dilogCustom = new DilogCustom();
        initialize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);

        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.n_my_profile));

        txtFName = (EditText) findViewById(R.id.txtFName);
        txtLName = (EditText) findViewById(R.id.txtLName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtMobile);

        txtFName.addTextChangedListener(this);
        txtLName.addTextChangedListener(this);
        txtPhone.addTextChangedListener(this);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        setDataFromSP();
    }

    protected void setDataFromSP() {
        if (SharedPref.getSP(ConstantField.USER_ID) != null) {
            txtFName.setText(SharedPref.getSP(ConstantField.USER_F_NAME));
            txtLName.setText(SharedPref.getSP(ConstantField.USER_L_NAME));
            txtEmail.setText(SharedPref.getSP(ConstantField.USER_EMAIL));
            txtPhone.setText(SharedPref.getSP(ConstantField.USER_MOBILE));

        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (Utils.getEditText(txtFName).equals(SharedPref.getSP(ConstantField.USER_F_NAME)) && Utils.getEditText(txtLName).equals(SharedPref.getSP(ConstantField.USER_L_NAME)) && Utils.getEditText(txtPhone).equals(SharedPref.getSP(ConstantField.USER_MOBILE))) {
            btnUpdate.setBackgroundColor(getColor(R.color.gray));
        } else {
            btnUpdate.setBackgroundColor(getColor(R.color.btnRed));

        }
    }
}