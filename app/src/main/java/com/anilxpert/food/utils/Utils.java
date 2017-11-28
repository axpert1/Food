package com.anilxpert.food.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by AnilXpert 9887230800 on 11/21/2017.
 */

public class Utils {
    //to start any activity.
    public static void startActivity(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(context, class1);
        ((Activity) context).startActivity(intent);
    }



}
