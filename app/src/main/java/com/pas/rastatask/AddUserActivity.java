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
import com.pas.rastatask.retroallstate.State;
import com.pas.rastatask.retroonetask.OneTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AddUserActivity extends AppCompatActivity {

    LinearLayout panClose;
    TextView lbOnvan;
    TextView lbNext;

    TextView lbSelVaz;

    TextView lbContentMng;
    TextView lbOnvanMng;

    EditText edContent;

    NestedScrollView scrollView;

    int position_status = 0;
    int sel_sstatus = 0;

    ArrayList<String> list_state = new ArrayList<>();
    ArrayList<String> list_id_state = new ArrayList<>();
    String id_state;

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        progressdialog = new ProgressDialog(this);
        progressdialog.setCancelable(false);
        progressdialog.setMessage("لطفا کمی صبر کنید");
        progressdialog.show();

        panClose = findViewById(R.id.pan_close);
        lbOnvan = findViewById(R.id.lb_Onvan);

        lbNext = findViewById(R.id.lb_next);

        lbSelVaz = findViewById(R.id.btn_sel_status);

        lbOnvanMng = findViewById(R.id.onvan_ed_mng_user);
        lbContentMng = findViewById(R.id.tozihat_ed_mng_user);

        edContent = findViewById(R.id.tozihat_ed_user);

        scrollView = findViewById(R.id.scroll_add_mng);

        lbOnvan.setTypeface(Library.changeFont(this,false));
        lbNext.setTypeface(Library.changeFont(this,false));

        lbSelVaz.setTypeface(Library.changeFont(this,false));

        lbOnvanMng.setTypeface(Library.changeFont(this,false));
        lbContentMng.setTypeface(Library.changeFont(this,false));

        edContent.setTypeface(Library.changeFont(this,false));

        if (Library.readAHSharedPreferences(this, "TypeUser").equals("1")) {
            edContent.setEnabled(false);
            lbSelVaz.setEnabled(false);
            lbNext.setVisibility(View.GONE);
        }

        show_task();

        lbSelVaz.setOnClickListener(v -> showStatusDialog());

        panClose.setOnClickListener(v -> {
            finish();
            startActivity.Activity2(AddUserActivity.this,ListActivity.class);
        });

        lbNext.setOnClickListener(v -> {
            progressdialog.show();
            set_comment();
        });

    }

    private void show_task(){
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<OneTask> call = service.getTask("Bearer "+Library.readAHSharedPreferences(AddUserActivity.this, "Token"), "Mnek!w@ZP(*s",Library.readAHSharedPreferences(this,"TagShow"));

        call.enqueue(new Callback<OneTask>() {
            @Override
            public void onResponse(@NonNull Call<OneTask> call, @NonNull Response<OneTask> response) {
                if(response.isSuccessful()){
                    OneTask getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==200){
                        if(getBody.getResponse().getMessage().get(0).getState()!=null){
                            lbSelVaz.setText(getBody.getResponse().getMessage().get(0).getState());
                        }
                        if(getBody.getResponse().getMessage().get(0).getTitle()!=null){
                            lbOnvanMng.setText(getBody.getResponse().getMessage().get(0).getTitle());
                        }
                        if(getBody.getResponse().getMessage().get(0).getCommentmanager()!=null){
                            lbContentMng.setText(getBody.getResponse().getMessage().get(0).getCommentmanager());
                        }
                        if(getBody.getResponse().getMessage().get(0).getCommentuser()!=null){
                            edContent.setText(getBody.getResponse().getMessage().get(0).getCommentuser());
                        }
                        get_state();
                    }else{
                        progressdialog.dismiss();
                        Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressdialog.dismiss();
                    Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<OneTask> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void get_state(){
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<State> call = service.getState("Bearer "+Library.readAHSharedPreferences(AddUserActivity.this, "Token"), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(@NonNull Call<State> call, @NonNull Response<State> response) {
                progressdialog.dismiss();

                if(response.isSuccessful()){
                    State getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==200){
                        for (int i = 0; i < getBody.getResponse().getMessage().size(); i++) {
                            list_state.add(getBody.getResponse().getMessage().get(i).getText());
                            list_id_state.add(getBody.getResponse().getMessage().get(i).getId());
                        }
                    }else {
                        Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<State> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showStatusDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);

        TextView title = new TextView(this);
        title.setText("آخرین وضعیت");
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

//        HashMap<String,Object> mBody= new HashMap<>();
//        HashMap<String,Object> mBody= new HashMap<>();
        JsonObject obj = new JsonObject();
        JsonObject payerReg = new JsonObject();
        payerReg.addProperty("user",Library.readAHSharedPreferences(AddUserActivity.this, "IdUser"));
        payerReg.addProperty("password","Mnek!w@ZP(*s");
        payerReg.addProperty("state",id_state);
        payerReg.addProperty("idtask",Library.readAHSharedPreferences(this,"TagShow"));
        payerReg.addProperty("comment",edContent.getText().toString());

//        HashMap<String,Object> mBody2= new HashMap<>();
//        mBody2.put("rqp",mBody);
        obj.add("payerReg",payerReg);

        Call<AddTask> call = service.setTask("Bearer "+Library.readAHSharedPreferences(AddUserActivity.this, "Token") ,obj.toString());

        call.enqueue(new Callback<AddTask>() {
            @Override
            public void onResponse(@NonNull Call<AddTask> call, @NonNull Response<AddTask> response) {
                progressdialog.dismiss();

                if (response.isSuccessful()) {
                    AddTask getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if(getCode==202){
                        Toast.makeText(AddUserActivity.this, "توضیحات شما با موفقیت ارسال گردید.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity.Activity2(AddUserActivity.this,ListActivity.class);
                    }else {
                        Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddTask> call, @NonNull Throwable t) {
                progressdialog.dismiss();
                Log.d("onFailureTAG", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(AddUserActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity.Activity2(AddUserActivity.this,ListActivity.class);
    }

}