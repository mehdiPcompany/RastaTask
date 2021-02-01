package com.pas.rastatask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

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

        lbSelVaz.setOnClickListener(v -> showStatusDialog());

        panClose.setOnClickListener(v -> {
            finish();
            Intent myIntent = new Intent(AddUserActivity.this, ListActivity.class);
            AddUserActivity.this.startActivity(myIntent);
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

        final CharSequence[] charSequence = new CharSequence[] {"درحال بررسی","درحال انجام","پایان"};

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
        Intent myIntent = new Intent(AddUserActivity.this, ListActivity.class);
        AddUserActivity.this.startActivity(myIntent);
    }
}