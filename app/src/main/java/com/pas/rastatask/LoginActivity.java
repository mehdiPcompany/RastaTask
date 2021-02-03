package com.pas.rastatask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pas.rastatask.retrologin.Login;

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

    private static Retrofit retrofit;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressdialog = new ProgressDialog(LoginActivity.this);
        progressdialog.setCancelable(false);
        progressdialog.setMessage("لطفا کمی صبر کنید");

        image_back = findViewById(R.id.image_back);
        Bitmap bm = Library.getBitmapFromAsset(this, "back.jpg");
        image_back.setImageBitmap(Library.getRoundedCornerBitmap(bm, 40));

        image_logo = findViewById(R.id.image_logo);
        Bitmap bmimage_logo = Library.getBitmapFromAsset(this, "icon.png");
        image_logo.setImageBitmap(bmimage_logo);

        btLogin = findViewById(R.id.Btlogin);
        edMobile = findViewById(R.id.mobile_edl);
        edPass = findViewById(R.id.pass_edl);
        lbCheck = findViewById(R.id.text_check_login);
        checkBox = findViewById(R.id.check_l);

        btLogin.setTypeface(Library.changeFont(this, false));
        edMobile.setTypeface(Library.changeFont(this, false));
        edPass.setTypeface(Library.changeFont(this, false));
        lbCheck.setTypeface(Library.changeFont(this, false));

        btLogin.setOnClickListener(v -> {
            Log.d("TAG", "onCreate: ");
            String strMobile = edMobile.getText().toString();
            String strPass = edPass.getText().toString();

            if (strMobile.equals("")) {
                Toast.makeText(LoginActivity.this, "لطفا شماره موبایل را وارد نمائید", Toast.LENGTH_SHORT).show();
            } else if (!strMobile.startsWith("09") || strMobile.length() < 11) {
                Toast.makeText(LoginActivity.this, "لطفا شماره موبایل را درست وارد نمائید", Toast.LENGTH_SHORT).show();
            } else if (strPass.equals("")) {
                Toast.makeText(LoginActivity.this, "لطفا رمزعبور را وارد نمائید", Toast.LENGTH_SHORT).show();
            } else {
//                Library.saveAHSharedPreferences(this,"TypeLogin",checkBox.isChecked());
//                Library.saveAHSharedPreferences(this,"UserPhone",strMobile);
//                finish();
//                startActivity(LoginActivity.this, ListActivity.class);
                progressdialog.show();
                SetLogin();
            }

        });

        lbCheck.setOnClickListener(v -> checkBox.setChecked(!checkBox.isChecked()));

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://rastatask.pasandsoft.ir")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


    }


    void SetLogin() {
        APIInterface service = retrofit.create(APIInterface.class);

        Call<Login> call = service.getLogin(edMobile.getText().toString(), edPass.getText().toString(), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login responses;
                progressdialog.dismiss();
                if (response.isSuccessful()) {
                    responses = response.body();
                    int strr = responses.getResponse().getCode();
                    Log.d("TstrrAG", String.valueOf(strr));

                    if (strr == 202) {
                        Toast.makeText(LoginActivity.this, "نام کاربری یا رمز عبور اشتباه می باشد.", Toast.LENGTH_SHORT).show();
                    } else if (strr == 200) {
                        Library.saveAHSharedPreferences(LoginActivity.this,"token",responses.getResponse().getMessage().getToken());
                        Library.saveAHSharedPreferences(LoginActivity.this,"nameUser",responses.getResponse().getMessage().getNameUser());
                        Library.saveAHSharedPreferences(LoginActivity.this,"idUser",responses.getResponse().getMessage().getIdUser());
                        Library.saveAHSharedPreferences(LoginActivity.this,"typeUser",responses.getResponse().getMessage().getTypeUser());
                        Library.saveAHSharedPreferences(LoginActivity.this,"username",responses.getResponse().getMessage().getUsername());

                        startActivity(LoginActivity.this,ListActivity.class);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progressdialog.dismiss();
                Log.d("TAG123456", t.getMessage());
                Toast.makeText(LoginActivity.this, "لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void startActivity(Context context, Class activity) {
        Intent myIntent = new Intent(context, activity);
        context.startActivity(myIntent);
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