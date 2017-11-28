package com.anilxpert.food.custom_class;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class ResizableImageView extends ImageView {

    public ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();

        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right
            // edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) Math.ceil((float) width* (float) d.getIntrinsicHeight()/     (float) d.getIntrinsicWidth());
            // int height=10;
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
