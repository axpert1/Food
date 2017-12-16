package com.anilxpert.food.loopjServcice;

import com.anilxpert.food.utils.SharedPref;
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

    public static RequestParams cuponCode(String code) {
        RequestParams params = new RequestParams();
        params.put("coupon_code", code);
        params.put("user_id", SharedPref.getSP(ConstantField.USER_ID));
        return params;
    }

    public static RequestParams orderList() {
        RequestParams params = new RequestParams();
        params.put("user_id", SharedPref.getSP(ConstantField.USER_ID));
        return params;
    }

    public static RequestParams orderDetails(String orderId) {
        RequestParams params = new RequestParams();
        params.put("order_id", orderId);
        return params;
    }

    public static RequestParams getParamsPlaceOrder(String dataJson, String totalAmount, String withDiscountAmount, String cuponID, String note) {
        RequestParams params = new RequestParams();
        params.put("user_id", SharedPref.getSP(ConstantField.USER_ID));
        params.put("date", SharedPref.getSP(ConstantField.DATE));
        params.put("time", SharedPref.getSP(ConstantField.TIME));
        params.put("location_id", SharedPref.getSP(ConstantField.ADDRESS_ID));
        params.put("order_json", dataJson);
        params.put("total_amount", totalAmount);//total amount
        params.put("rest_amount", withDiscountAmount);//after discount
        params.put("coupon_id", cuponID);
        params.put("notes", note);
        params.put("spiceness_level", SharedPref.getSP(ConstantField.SPICINESSLEVEL_NAME));
        params.put("dry_or_soup", SharedPref.getSP(ConstantField.DRYSHOP_NAME));


        return params;
    }
}
