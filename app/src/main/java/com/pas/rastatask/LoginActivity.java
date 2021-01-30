package com.pas.rastatask;

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
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    ImageView image_back;
    ImageView image_logo;

    Button btLogin;
    EditText edMobile;
    EditText edPass;
    TextView lbCheck;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        image_back = findViewById(R.id.image_back);
        Bitmap bm = getBitmapFromAsset("back.jpg");
        image_back.setImageBitmap(getRoundedCornerBitmap(bm,40));

        image_logo = findViewById(R.id.image_logo);
        Bitmap bmimage_logo = getBitmapFromAsset("icon.png");
        image_logo.setImageBitmap(bmimage_logo);

        btLogin = findViewById(R.id.Btlogin);
        edMobile = findViewById(R.id.mobile_edl);
        edPass = findViewById(R.id.pass_edl);
        lbCheck = findViewById(R.id.text_check_login);
        checkBox = findViewById(R.id.check_l);

        btLogin.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));
        edMobile.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));
        edPass.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));
        lbCheck.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));

        btLogin.setOnClickListener(v -> {
            Log.d("TAG", "onCreate: ");
            String strMobile = edMobile.getText().toString();
            String strPass = edPass.getText().toString();

            if(strMobile.equals("")){
                Toast.makeText(LoginActivity.this,"لطفا شماره موبایل را وارد نمائید",Toast.LENGTH_SHORT).show();
            }else if(!strMobile.startsWith("09")||strMobile.length()<11){
                Toast.makeText(LoginActivity.this,"لطفا شماره موبایل را درست وارد نمائید",Toast.LENGTH_SHORT).show();
            }else if(strPass.equals("")){
                Toast.makeText(LoginActivity.this,"لطفا رمزعبور را وارد نمائید",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this,"لطفا کمی صبر نمائید",Toast.LENGTH_SHORT).show();
            }

        });

        lbCheck.setOnClickListener(v -> checkBox.setChecked(!checkBox.isChecked()));

    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
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

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            System.exit(0);
            return;
        } else {
            Toast.makeText(getBaseContext(), "برای خروج دوباره کلیک کنید...", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

}