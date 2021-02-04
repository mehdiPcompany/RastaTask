package com.pas.rastatask;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.pas.rastatask.myclass.KeyValueStore;
import com.pas.rastatask.myclass.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import androidx.annotation.NonNull;

public interface Library {


    static Typeface changeFont(@NonNull Context ctx, Boolean bold) {
        String path;

        if (bold) {
            path = "fonts/Shabnam-Bold.ttf";
        } else {
            path = "fonts/Shabnam.ttf";
        }

        return Typeface.createFromAsset(ctx.getAssets(), path);
    }

    static void saveAHSharedPreferences(@NonNull Context ctx, String Key, Object Value) {
        sql sql1 = new sql();
        KeyValueStore keyValueStore = new KeyValueStore(sql1);
        keyValueStore.Initialize(ctx.getApplicationContext().getFilesDir().toString(), "AllValue.db");
        keyValueStore.Put(Key, Value);
    }

    static String readAHSharedPreferences(@NonNull Context ctx, String Key) {
        sql sql1 = new sql();
        KeyValueStore keyValueStore = new KeyValueStore(sql1);
        keyValueStore.Initialize(ctx.getApplicationContext().getFilesDir().toString(), "AllValue.db");
        if(keyValueStore.ContainsKey(Key)){
            return keyValueStore.Get(Key);
        }else {
            return "0";
        }
    }

    static void colorDrawableRadius(View v, int color, float radius){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(radius);
        gd.setStroke(0, 0);
        v.setBackground(gd);
    }
    static void colorDrawableBorder(View v, int color, float radius, int border_radius, int boder_color){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(radius);
        gd.setStroke(border_radius, boder_color);
        v.setBackground(gd);
    }

    static void setCornerRadii(View v, int color, float radius, int border_radius, int boder_color, Float Rx_TopLeft, Float Ry_TopLeft, Float Rx_TopRight, Float Ry_TopRight, Float Rx_BottomRight, Float Ry_BottomRight, Float Rx_BottomLeft, Float Ry_BottomLeft){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
//        gd.setCornerRadius(radius);
        gd.setCornerRadii(new float[] { Rx_TopLeft, Ry_TopLeft, Rx_TopRight, Ry_TopRight, Rx_BottomRight, Ry_BottomRight, Rx_BottomLeft, Ry_BottomLeft});
        gd.setStroke(border_radius, boder_color);
        v.setBackground(gd);
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

    static int Rnd(int Min, int Max) {
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
