package com.anilxpert.food.custom_class;

import android.content.Context;
import android.graphics.Bitmap;

import com.anilxpert.food.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by AnilXpert 9887230800 on 16-Dec-17.
 */

public class QRcode {
    public interface qrCode {
        public void onSuccessQr(boolean success, Bitmap response, int which);


    }

    public final static int QRcodeWidth = 500;
    Bitmap bitmap;
    BitMatrix bitMatrix;

    public void bitmapCall(Context context, String data, final int which, final qrCode qrcode) {

        try {
            try {
                bitMatrix = new MultiFormatWriter().encode(
                        data,
                        BarcodeFormat.DATA_MATRIX.QR_CODE,
                        QRcodeWidth, QRcodeWidth, null
                );
            } catch (WriterException e) {
                qrcode.onSuccessQr(false, bitmap, which);
                e.printStackTrace();
            }

        } catch (IllegalArgumentException Illegalargumentexception) {

            qrcode.onSuccessQr(false, bitmap, which);
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        context.getResources().getColor(R.color.bgBlack) : context.getResources().getColor(R.color.light_white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        qrcode.onSuccessQr(true, bitmap, which);

    }
}
