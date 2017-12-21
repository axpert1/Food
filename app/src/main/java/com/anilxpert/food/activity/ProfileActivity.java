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
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.CommonModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SetRules;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;


/**
 * Created by this pc on 11/23/2017.
 */

public class ProfileActivity extends BaseActivity_ implements NetworkManager.onCallback {
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
        setupToolbar(getString(R.string.n_my_profile));
        mContext = ProfileActivity.this;
        dilogCustom = new DilogCustom();
        initialize();
    }


    private void initialize() {
        txtFName = (EditText) findViewById(R.id.txtFName);
        txtLName = (EditText) findViewById(R.id.txtLName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtMobile);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.getEditText(txtFName).equals(SharedPref.getSP(ConstantField.USER_F_NAME)) && Utils.getEditText(txtLName).equals(SharedPref.getSP(ConstantField.USER_L_NAME)) && Utils.getEditText(txtPhone).equals(SharedPref.getSP(ConstantField.USER_MOBILE))) {

                } else {
                    if (getTextEmpety()) {
                        //String f_name, String l_name, String email, String mobile, String password
                        RequestParams params = CmdParams.getProfileUpdateParams(Utils.getEditText(txtFName), Utils.getEditText(txtLName), Utils.getEditText(txtPhone));
                        apiCall(getString(R.string.please_wait), AppUrl.UPDATE_MY_PROFILE, params, true, ConstantField.WHITCH_1);
                    }
                }
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


    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        CommonModel commonModel = JsonDeserializer.deserializeJson(response, CommonModel.class);
        if (commonModel.status == 1) {
            SharedPref.putSP(ConstantField.USER_MOBILE, Utils.getEditText(txtPhone));
            SharedPref.putSP(ConstantField.USER_F_NAME, Utils.getEditText(txtFName));
            SharedPref.putSP(ConstantField.USER_L_NAME, Utils.getEditText(txtLName));
            Toast.makeText(mContext, commonModel.message, Toast.LENGTH_SHORT).show();
        } else {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), commonModel.message, getString(R.string.cancel), "", null);
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

    }

    private boolean getTextEmpety() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SetRules.serRule(Utils.getEditText(txtFName), ConstantField.CMD_F_NAME));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(txtPhone), ConstantField.CMD_MOBILE));
        if (stringBuilder.toString().trim().length() > 0) {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), stringBuilder.toString(), getString(R.string.cancel), "", null);
            return false;
        } else {
            return true;
        }

    }
}