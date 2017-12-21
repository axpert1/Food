package com.anilxpert.food.utils;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class AppUrl {
    public static String BASE_URL = "http://wingstud.in/food/Api/";
    public static String TOKEN = "/6f3621b8e4f27f14bbb8c6179719f6e2";

    public static String USER_CONTROLLER = "User_controller/";

    public static String ADD_USER = BASE_URL + USER_CONTROLLER + "add_user" + TOKEN;

    public static String LOG_IN = BASE_URL + USER_CONTROLLER + "login" + TOKEN;

    public static String CHANGE_PASSWORD = BASE_URL + USER_CONTROLLER + "change_password" + TOKEN;

    public static String FORGOT_PASSWORD = BASE_URL + USER_CONTROLLER + "forgot_password" + TOKEN;

    public static String LOG_OUT = BASE_URL + USER_CONTROLLER + "logout" + TOKEN;

    public static String GET_USER_PROFILE = BASE_URL + USER_CONTROLLER + "get_user_profile" + TOKEN;
    public static String UPDATE_MY_PROFILE = BASE_URL + USER_CONTROLLER + "my_profile" + TOKEN;

    public static String CONTACT_US = BASE_URL + USER_CONTROLLER + "contact_us" + TOKEN;

    public static String GET_PRODUCTS = BASE_URL + USER_CONTROLLER + "get_products" + TOKEN;

    public static String GET_LOCATIONS = BASE_URL + USER_CONTROLLER + "get_locations" + TOKEN;

    public static String ADD_USER_FB = BASE_URL + USER_CONTROLLER + "add_user_fb" + TOKEN;

    public static String GET_COUPON_CODE_LIST = BASE_URL + USER_CONTROLLER + "get_coupon_code" + TOKEN;
    public static String CHECK_COUPON_CODE = BASE_URL + USER_CONTROLLER + "apply_coupon_code" + TOKEN;

    public static String PLACE_ORDER = BASE_URL + USER_CONTROLLER + "set_order" + TOKEN;
    public static String GET_ORDER_LIST = BASE_URL + USER_CONTROLLER + "get_order_list" + TOKEN;
    public static String GET_ORDER_DETAILS = BASE_URL + USER_CONTROLLER + "get_order_details" + TOKEN;
}
