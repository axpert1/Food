package com.anilxpert.food.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anilxpert.food.R;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.CommonModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SetRules;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class ChangePasswordFragment extends Fragment implements View.OnClickListener, NetworkManager.onCallback {
    private View view;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;

    private Button btnResetPassword;
    private EditText edtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
//        view = inflater.inflate(R.layout.change_password, null);
//        mContext = getActivity();
//        dilogCustom = new DilogCustom();
//        initialize();

        return view;
    }

    private void initialize() {
        btnResetPassword = (Button) view.findViewById(R.id.btnResetPassword);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);

        btnResetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnResetPassword:
                if (getTextEmpety()) {
                    RequestParams params = CmdParams.getParamsForgot(Utils.getEditText(edtEmail));
                    apiCall(getString(R.string.please_wait), AppUrl.FORGOT_PASSWORD, params, true, ConstantField.WHITCH_1);
                }

                break;
        }

    }

    private boolean getTextEmpety() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SetRules.serRule(Utils.getEditText(edtEmail), ConstantField.CMD_EMAIL));
        if (stringBuilder.toString().trim().length() > 0) {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), stringBuilder.toString(), getString(R.string.cancel), "", this);
            return false;
        } else {
            return true;
        }

    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        Log.e("Responce", " Success " + response);
        CommonModel commonModel = JsonDeserializer.deserializeJson(response, CommonModel.class);
        if (commonModel.status == 1) {


        } else {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), commonModel.message, getString(R.string.cancel), "", this);

        }

    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Log.e("Responce", " Failed " + response);
    }

}
