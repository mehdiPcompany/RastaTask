package com.pas.rastatask;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    AVLoadingIndicatorView avLoadingIndicatorView;

    TextView ver_main;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.main_logo);
        Bitmap bm = getBitmapFromAsset("logo.png");
        logo.setImageBitmap(bm);

        avLoadingIndicatorView = findViewById(R.id.avi);

        ver_main = findViewById(R.id.ver_main);
        ver_main.setText(convertNum(ver_main.getText().toString(),true));
        ver_main.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                startActivity(MainActivity.this,LoginActivity.class);
                finish();
            }
        },3000);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }


    public void startActivity(Context context, Class activity) {
        Intent myIntent = new Intent(context, activity);
        context.startActivity(myIntent);
    }

    public static String convertNum(String str, boolean Persion) {
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

    private Bitmap getBitmapFromAsset(String strName)
    {
        AssetManager assetManager = getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(istr);
    }
}