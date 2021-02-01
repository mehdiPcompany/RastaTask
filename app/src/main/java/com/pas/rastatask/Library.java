package com.pas.rastatask;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import androidx.annotation.NonNull;

public interface Library {


    static Typeface changeFont(@NonNull Context ctx,Boolean bold){
        String path;

        if(bold){
            path = "fonts/Shabnam-Bold.ttf";
        }else{
            path = "fonts/Shabnam.ttf";
        }

        return  Typeface.createFromAsset(ctx.getAssets(),path);
    }

    static String convertNum(String str, boolean Persion) {
        String[][] chars = new String[][]{
                {"0", "۰"},
                {"1", "۱"},
                {"2", "۲"},
                {"3", "۳"},
                {"4", "۴"},
                {"5", "۵"},
                {"6", "۶"},
                {"7", "۷"},
                {"8", "۸"},
                {"9", "۹"}
        };

        for (String[] num : chars) {
            if (Persion) {
                str = str.replace(num[0], num[1]);
            } else {
                str = str.replace(num[1], num[0]);
            }
        }
        return str;
    }

    static int Rnd(int Min, int Max){
        return Min + new Random().nextInt(Max - Min);
    }

    static Bitmap getRoundedCornerBitmap(@NonNull Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    static Bitmap getBitmapFromAsset(@NonNull Context ctx, String strName) {
        AssetManager assetManager = ctx.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(istr);
    }

}
