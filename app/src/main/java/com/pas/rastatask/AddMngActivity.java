package com.pas.rastatask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_add);

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

        panClose.setOnClickListener(v -> {
            finish();
            Intent myIntent = new Intent(AddMngActivity.this, ListActivity.class);
            AddMngActivity.this.startActivity(myIntent);
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

        final CharSequence[] charSequence = new CharSequence[] {"کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک"};

        builder.setSingleChoiceItems(charSequence, position_user, (dialog, which) -> sel_uuser  = which);

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {
                    position_user = sel_uuser;
                    String s = String.valueOf(charSequence[position_user]);
                    lbAddUser.setText(s);
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

        final CharSequence[] charSequence = new CharSequence[] {"عادی","مهم","ضروری"};

        builder.setSingleChoiceItems(charSequence, position_status, (dialog, which) -> sel_sstatus = which);

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {
                    position_status = sel_sstatus;
                    String s = String.valueOf(charSequence[position_status]);
                    lbSelVaz.setText(s);
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

    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(AddMngActivity.this, ListActivity.class);
        AddMngActivity.this.startActivity(myIntent);
    }

}