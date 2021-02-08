package com.pas.rastatask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.pas.rastatask.API.APIClient;
import com.pas.rastatask.API.APIInterface;
import com.pas.rastatask.myclass.Library;
import com.pas.rastatask.myclass.startActivity;
import com.pas.rastatask.retroaddtask.AddTask;
import com.pas.rastatask.retroallstatus.Status;
import com.pas.rastatask.retrouser.User;

import java.util.ArrayList;
import java.util.Objects;

public class AddMngActivity extends AppCompatActivity {

    LinearLayout panClose;
    TextView lbOnvan;
    TextView lbNext;
    TextView lbAddUser;
    TextView lbSelVaz;

    EditText edOnvan;
    EditText edContent;

    NestedScrollView scrollView;

    int position_user = 0;
    int sel_uuser = 0;

    int position_status = 0;
    int sel_sstatus = 0;

    ArrayList<String> list_state = new ArrayList<>();
    ArrayList<String> list_id_state = new ArrayList<>();
    String id_state = "";

    ArrayList<String> list_user = new ArrayList<>();
    ArrayList<String> list_id_user = new ArrayList<>();
    String id_user = "";

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_add);

        progressdialog = new ProgressDialog(this);
        progressdialog.setCancelable(false);
        progressdialog.setMessage("لطفا کمی صبر کنید");
        progressdialog.show();

        panClose = findViewById(R.id.pan_close);
        lbOnvan = findViewById(R.id.lb_Onvan);

        lbNext = findViewById(R.id.lb_next);

        lbAddUser = findViewById(R.id.btn_sel_user);
        lbSelVaz = findViewById(R.id.btn_sel_vaz);

        edOnvan = findViewById(R.id.onvan_ed_mng);
        edContent = findViewById(R.id.tozihat_ed_mng);

        scrollView = findViewById(R.id.scroll_add_mng);

        lbOnvan.setTypeface(Library.changeFont(this,false));
        lbNext.setTypeface(Library.changeFont(this,false));

        lbAddUser.setTypeface(Library.changeFont(this,false));
        lbSelVaz.setTypeface(Library.changeFont(this,false));

        edOnvan.setTypeface(Library.changeFont(this,false));
        edContent.setTypeface(Library.changeFont(this,false));

        lbAddUser.setOnClickListener(v -> showUserDialog());
        lbSelVaz.setOnClickListener(v -> showStatusDialog());

        get_state();

        lbNext.setOnClickListener(v -> {
            if(edContent.getText().toString().isEmpty()||edOnvan.getText().toString().isEmpty()||id_state.isEmpty()||id_user.isEmpty()){
                Toast.makeText(AddMngActivity.this, "فیلدها نباید خالی باشد", Toast.LENGTH_SHORT).show();
            }else{
                progressdialog.show();
                set_comment();
            }
        });

        panClose.setOnClickListener(v -> {
            finish();
            startActivity.Activity2(AddMngActivity.this,ListActivity.class);
        });

    }

    private void get_state(){
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<Status> call = service.getStatus("Bearer "+Library.readAHSharedPreferences(AddMngActivity.this, "Token"), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {

                if(response.isSuccessful()){
                    Status getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==200){
                        for (int i = 0; i < getBody.getResponse().getMessage().size(); i++) {
                            list_state.add(getBody.getResponse().getMessage().get(i).getText());
                            list_id_state.add(getBody.getResponse().getMessage().get(i).getId());
                        }
                        get_user();
                    }else {
                        Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void get_user(){
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<User> call = service.getUser("Bearer "+Library.readAHSharedPreferences(AddMngActivity.this, "Token"), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                progressdialog.dismiss();

                if(response.isSuccessful()){
                    User getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==200){
                        for (int i = 0; i < getBody.getResponse().getMessage().size(); i++) {
                            list_user.add(getBody.getResponse().getMessage().get(i).getName());
                            list_id_user.add(getBody.getResponse().getMessage().get(i).getId());
                        }
                    }else {
                        Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMngActivity.this);

        TextView title = new TextView(this);
        title.setText("انتخاب کاربر");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        title.setTypeface(Library.changeFont(this,true));

        builder.setCustomTitle(title);

        final String[] charSequence = new String[list_user.size()];
        for (int i = 0; i < list_user.size(); i++) {
            charSequence[i] =  list_user.get(i);
        }

        builder.setSingleChoiceItems(charSequence, position_user, (dialog, which) -> sel_uuser  = which);

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {
                    position_user = sel_uuser;
                    String s = String.valueOf(charSequence[position_user]);
                    lbAddUser.setText(s);
                    id_user = list_id_user.get(position_user);
                });
        String negativeText = "بازگشت";
        builder.setNegativeButton(negativeText,
                (dialog, which) -> {

                });

        AlertDialog dialog = builder.create();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        dialog.show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.RED);
        positiveButton.setTypeface(Library.changeFont(this,false));

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.RED);
        negativeButton.setTypeface(Library.changeFont(this,false));

    }

    private void showStatusDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMngActivity.this);

        TextView title = new TextView(this);
        title.setText("اولویت");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        title.setTypeface(Library.changeFont(this,true));

        builder.setCustomTitle(title);

        final String[] charSequence = new String[list_state.size()];
        for (int i = 0; i < list_state.size(); i++) {
            charSequence[i] =  list_state.get(i);
        }

        builder.setSingleChoiceItems(charSequence, position_status, (dialog, which) -> sel_sstatus = which);

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {
                    position_status = sel_sstatus;
                    String s = String.valueOf(charSequence[position_status]);
                    lbSelVaz.setText(s);
                    id_state = list_id_state.get(position_status);
                });
        String negativeText = "بازگشت";
        builder.setNegativeButton(negativeText,
                (dialog, which) -> {

                });

        AlertDialog dialog = builder.create();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        dialog.show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.RED);
        positiveButton.setTypeface(Library.changeFont(this,false));

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.RED);
        negativeButton.setTypeface(Library.changeFont(this,false));

    }

    void set_comment(){

        APIInterface service = APIClient.getClient().create(APIInterface.class);

        JsonObject obj = new JsonObject();
        JsonObject payerReg = new JsonObject();
        payerReg.addProperty("user",id_user);
        payerReg.addProperty("title",edOnvan.getText().toString());
        payerReg.addProperty("password","Mnek!w@ZP(*s");
        payerReg.addProperty("state",1);
        payerReg.addProperty("status",id_state);
        payerReg.addProperty("comment",edContent.getText().toString());

        obj.add("rqp",payerReg);

        Call<AddTask> call = service.setTask("Bearer "+Library.readAHSharedPreferences(AddMngActivity.this, "Token") ,obj);

        call.enqueue(new Callback<AddTask>() {
            @Override
            public void onResponse(@NonNull Call<AddTask> call, @NonNull Response<AddTask> response) {
                progressdialog.dismiss();

                if (response.isSuccessful()) {
                    AddTask getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==202){
                        Toast.makeText(AddMngActivity.this, "توضیحات شما با موفقیت ارسال گردید.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity.Activity2(AddMngActivity.this,ListActivity.class);
                    }else {
                        Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddTask> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddMngActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity.Activity2(AddMngActivity.this,ListActivity.class);
    }

}