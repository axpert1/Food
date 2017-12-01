package com.anilxpert.food.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.TextView;

import com.anilxpert.food.loopjServcice.ConstantField;

/**
 * Created by AnilXpert 9887230800 on 11/21/2017.
 */

public class Utils {


    public static void replaceFrg(FragmentActivity ctx, Fragment frag, boolean addToBackStack, int placeHolderId) {
        FragmentManager fm = ctx.getSupportFragmentManager();
        int addedFrgCount = fm.getBackStackEntryCount();
        FragmentTransaction ft = fm
                .beginTransaction();
        ft.replace(placeHolderId, frag, frag.getClass().getName());

        if (addToBackStack)
            ft.addToBackStack(null);
        ft.commit();

    }
    public static String getDateWithFormat(int day, int month, int year) {

        String dateFormat = String.valueOf(new StringBuilder().append(day).append("-")
                .append(getMonthName(month)).append("-").append(year));

        return dateFormat;
    }
    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";
        }

        return "";
    }
    //to start any activity.
    public static void startActivity(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(context, class1);
        ((Activity) context).startActivity(intent);
    }

    //to start any activity.
    public static void startActivityPutValue(Context context, Class<?> class1, String value) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(context, class1);
        intent.putExtra(ConstantField.INTENT_1, value);
        ((Activity) context).startActivity(intent);
    }

    public static void clearPriviousActivity(Context context, Class<?> class1) {
        Intent intent = new Intent(context, class1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static String getintentString(String key, Intent intent) {
        String output = key;
        Bundle b = intent.getExtras();
        if (b != null) {
            output = (String) b.get(key);

        }
        return output;
    }

    public static boolean getEditTextBool(EditText editText) {
        if (editText.getText().toString().length() > 0) {
            return true;

        }
        return false;
    }

    public static boolean getTextBool(TextView textview) {
        if (textview.getText().toString().length() > 0) {
            return true;

        }
        return false;
    }
    public static String getEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

}
