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
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.activity.LogRegActivity;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener, NetworkManager.onCallback {
    private Fragment signinFragment = new SigninFragment();
    private EditText etxtFName;
    private EditText etxtLName;
    private EditText etxtMobile;
    private EditText etxtEmail;
    private EditText etxtPass;

    private Button btnRegister;
    private Button btnSignIn;
    private Button btnFacebook;

    private View view;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;

    private CallbackManager callbackManager;
    private List<String> permissionNeeds = Arrays.asList("public_profile", "email", "user_friends");
    private AccessToken token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        view = inflater.inflate(R.layout.register, null);

        mContext = getActivity();
        dilogCustom = new DilogCustom();

        initialize();

        return view;
    }

    private void initialize() {
        etxtFName = (EditText) view.findViewById(R.id.edtFName);
        etxtLName = (EditText) view.findViewById(R.id.edtLName);
        etxtMobile = (EditText) view.findViewById(R.id.edtMobile);
        etxtEmail = (EditText) view.findViewById(R.id.edtEmail);
        etxtPass = (EditText) view.findViewById(R.id.edtPassword);


        btnSignIn = (Button) view.findViewById(R.id.btnSignIn);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnFacebook = (Button) view.findViewById(R.id.btnFacebook);

        btnSignIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);

        retryClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilogCustom.dismissRetryAlert();
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (getTextEmpety()) {
                    //String f_name, String l_name, String email, String mobile, String password
                    RequestParams params = CmdParams.register(Utils.getEditText(etxtFName), Utils.getEditText(etxtLName), Utils.getEditText(etxtEmail), Utils.getEditText(etxtMobile), Utils.getEditText(etxtPass));
                    apiCall(getString(R.string.please_wait), AppUrl.ADD_USER, params, true, ConstantField.WHITCH_1);
                }

                break;
            case R.id.btnSignIn:
                ((LogRegActivity) getActivity()).setScreen(signinFragment);
                break;
            case R.id.btnFacebook:
                facebookLogin();
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
        if (commonModel.status == 1) {

            SharedPref.putSP(ConstantField.USER_ID, commonModel.userId);
            SharedPref.putSP(ConstantField.USER_EMAIL, Utils.getEditText(etxtEmail));
            SharedPref.putSP(ConstantField.USER_MOBILE, Utils.getEditText(etxtMobile));
            SharedPref.putSP(ConstantField.USER_F_NAME, Utils.getEditText(etxtFName));
            SharedPref.putSP(ConstantField.USER_L_NAME, Utils.getEditText(etxtLName));

            Utils.clearPriviousActivity(mContext, DashBordActivity.class);

        } else {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), Utils.getEditText(etxtEmail) + "\n Email is already Exists", getString(R.string.cancel), "", this);

        }

    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Log.e("Responce", " Failed " + response);
    }

    private boolean getTextEmpety() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SetRules.serRule(Utils.getEditText(etxtFName), ConstantField.CMD_F_NAME));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(etxtMobile), ConstantField.CMD_MOBILE));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(etxtEmail), ConstantField.CMD_EMAIL));
        stringBuilder.append(SetRules.serRule(Utils.getEditText(etxtPass), ConstantField.CMD_PASSWORD));


        if (stringBuilder.toString().trim().length() > 0) {
            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), stringBuilder.toString(), getString(R.string.cancel), "", this);
            return false;
        } else {
            return true;
        }

    }

    private void facebookLogin() {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(final LoginResult loginResults) {
                    token = loginResults.getAccessToken();
                    facebookCall(loginResults.getAccessToken().getToken());
                }

                @Override
                public void onCancel() {
                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    accessToken.setCurrentAccessToken(null);
                    Log.e("dd", "facebook login canceled");
                    // Utils.showCenterToast(getActivity(), "facebook login canceled");
                }

                @Override
                public void onError(FacebookException e) {
                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    accessToken.setCurrentAccessToken(null);
                    Log.e("dd", "facebook login failed error");
                    // Util.showCenterToast(getActivity(), "facebook login failed, please retry");
                }
            });
        } else {
            token = accessToken;
            facebookCall(accessToken.getToken());
        }
    }

    private void facebookCall(final String tokem) {
        GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.v("LoginActivity", response.toString());
                try {
                    JSONObject fbObject = response.getJSONObject();
                    String name = fbObject.getString("");


//                    if (fbObject.has(Constants.FLD_EMAIL)) {
//
//                        if (Util.isDeviceOnline(getActivity())) {
//                            //TODO.. found email call api here
//                        } else {
//                            Util.showCenterToast(getActivity(), getResources().getString(R.string.msg_internet_connection));
//                        }
//                    } else {
//                        if (email.length() == 0) {
//                            //TODO.. email not comes from facebook do further work here
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender, birthday,picture.type(small)");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
