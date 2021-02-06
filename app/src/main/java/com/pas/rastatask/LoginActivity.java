package com.pas.rastatask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pas.rastatask.API.APIClient;
import com.pas.rastatask.API.APIInterface;
import com.pas.rastatask.myclass.Library;
import com.pas.rastatask.retrologin.Login;

import java.util.Objects;

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

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressdialog = new ProgressDialog(this);
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
                progressdialog.show();
                SetLogin();
            }

        });

        lbCheck.setOnClickListener(v -> checkBox.setChecked(!checkBox.isChecked()));

    }


    void SetLogin() {

        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<Login> call = service.getLogin(edMobile.getText().toString(), edPass.getText().toString(), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                progressdialog.dismiss();

                if (response.isSuccessful()) {
                    Login getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode == 200) {
                        Library.saveAHSharedPreferences(LoginActivity.this, "TypeLogin", checkBox.isChecked());
                        Library.saveAHSharedPreferences(LoginActivity.this, "UserPhone", edMobile.getText().toString());
                        Library.saveAHSharedPreferences(LoginActivity.this, "NameUser", getBody.getResponse().getMessage().get(0).getNameUser());
                        Library.saveAHSharedPreferences(LoginActivity.this, "IdUser", getBody.getResponse().getMessage().get(0).getIdUser());
                        Library.saveAHSharedPreferences(LoginActivity.this, "Token", getBody.getResponse().getMessage().get(0).getToken());
                        finish();
                        startActivity(LoginActivity.this, ListActivity.class);
                    }else{
                        Toast.makeText(LoginActivity.this, "نام‌کاربری یا رمزعبور اشتباه می‌باشد.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(LoginActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
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