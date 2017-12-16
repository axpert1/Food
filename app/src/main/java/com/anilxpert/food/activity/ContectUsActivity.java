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
import com.anilxpert.food.activity.DashBordActivity;
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
 * Created by this pc on 11/24/2017.
 */

public class ContectUsFragment extends Fragment implements View.OnClickListener, NetworkManager.onCallback {
    private View view;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;

    private Button btnSend;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtMobile;
    private EditText edtMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        view = inflater.inflate(R.layout.contect_us, null);
        mContext = getActivity();
        dilogCustom = new DilogCustom();
        initialize();
        retryClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilogCustom.dismissRetryAlert();
             //   ((DashBordActivity) getActivity()).gotoNextScreen(new HomeFragment(), mContext.getString(R.string.n_find_us));
            }
        };
        return view;
    }

    private void initialize() {
        btnSend = (Button) view.findViewById(R.id.btnSend);
        edtName = (EditText) view.findViewById(R.id.edtName);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMobile = (EditText) view.findViewById(R.id.edtMobile);
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);

        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                if (getTextEmpety()) {
                    //String name,String email,String mobile,String comment
                    RequestParams params = CmdParams.getParamsContactUs(Utils.getEditText(edtName), Utils.getEditText(edtEmail), Utils.getEditText(edtMobile), Utils.getEditText(edtMessage));
                    apiCall(getString(R.string.please_wait), AppUrl.CONTACT_US, params, true, ConstantField.WHITCH_1);
                }
                break;
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
        dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), commonModel.message, "", getString(R.string.ok), retryClick);


    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Log.e("Responce", " Failed " + response);
    }

    private boolean getTextEmpety() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SetRules.serRule(Utils.getEditText(edtName), ConstantField.CMD_NAME));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(edtMobile), ConstantField.CMD_MOBILE));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(edtEmail), ConstantField.CMD_EMAIL));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(edtMessage), ConstantField.CMD_MESSAGE));


        if (stringBuilder.toString().trim().length() > 0) {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), stringBuilder.toString(), getString(R.string.cancel), "", this);
            return false;
        } else {
            return true;
        }

    }

}