package com.anilxpert.food.custom_class;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.anilxpert.food.AppInitialization;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class EditTextViewRegularBold extends EditText {
    public EditTextViewRegularBold(Context context) {
        super(context);
        initialization();
    }

    public EditTextViewRegularBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    public EditTextViewRegularBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization();
    }

    public EditTextViewRegularBold(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialization();
    }

    public void initialization() {
        setTypeface(AppInitialization.getFontSemiBold());
    }
}
