package com.pas.rastatask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.pas.rastatask.myclass.Library;
import com.pas.rastatask.myclass.startActivity;
import com.wang.avi.AVLoadingIndicatorView;

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
        Bitmap bm = Library.getBitmapFromAsset(this,"logo.png");
        logo.setImageBitmap(bm);

        avLoadingIndicatorView = findViewById(R.id.avi);

        ver_main = findViewById(R.id.ver_main);
        ver_main.setText(Library.convertNum(ver_main.getText().toString(),true));
        ver_main.setTypeface(Library.changeFont(this,true));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                finish();
                startActivity.Activity2(MainActivity.this,LoginActivity.class);
            }
        },3000);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

}