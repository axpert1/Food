package com.anilxpert.food.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.anilxpert.food.R;
import com.anilxpert.food.custom_class.QRcode;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.Utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by AnilXpert 9887230800 on 16-Dec-17.
 */

public class ShowQrCode extends BaseActivity_ implements QRcode.qrCode {
    ImageView imageView;
    String qrCode;
    QRcode qRcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_show);
        setupToolbar(getString(R.string.scan));
        qrCode = Utils.getintentString(ConstantField.INTENT_1, getIntent());
        imageView = (ImageView) findViewById(R.id.imageView);
        qRcode = new QRcode();



    }

    @Override
    protected void onResume() {
        super.onResume();
        qRcode.bitmapCall(getApplicationContext(), qrCode, 0, this);
    }


    @Override
    public void onSuccessQr(boolean success, Bitmap response, int which) {
        if (success) {
            imageView.setImageBitmap(response);
        }

    }
}
