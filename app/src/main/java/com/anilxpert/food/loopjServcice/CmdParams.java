package com.anilxpert.food.loopjServcice;

import com.loopj.android.http.RequestParams;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class CmdParams {


    public static RequestParams register(String f_name, String l_name, String email, String mobile, String password) {
        RequestParams params = new RequestParams();       //    cmd params
        params.put("f_name", f_name);
        params.put("l_name", l_name);
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("image", "xyz");
        return params;
    }

    public static RequestParams registerFB(String f_name, String l_name, String email) {

        RequestParams params = new RequestParams();       //    cmd params
        params.put("email", email);
        params.put("f_name", f_name);
        params.put("l_name", l_name);

        return params;
    }

    public static RequestParams getParams(String email, String pass) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", pass);
        return params;
    }

    public static RequestParams getParamsForgot(String email) {
        RequestParams params = new RequestParams();
        params.put("email", email);

        return params;
    }

    public static RequestParams getParamsContactUs(String name, String email, String mobile, String comment) {
        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("comment", comment);
        return params;
    }
}
