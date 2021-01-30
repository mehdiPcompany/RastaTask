package com.pas.rastatask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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

        lbOnvan.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));
        lbNext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));

        lbAddUser.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));
        lbSelVaz.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

        edOnvan.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));
        edContent.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

        lbAddUser.setOnClickListener(v -> showUserDialog());
        lbSelVaz.setOnClickListener(v -> showStatusDialog());

        panClose.setOnClickListener(v -> {

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
        title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));

        builder.setCustomTitle(title);

        final CharSequence[] charSequence = new CharSequence[] {"کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک","کاربر دو","کاربر یک"};

        builder.setSingleChoiceItems(charSequence, 0, (dialog, which) -> {

        });

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {

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
        positiveButton.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.RED);
        negativeButton.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

    }

    private void showStatusDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMngActivity.this);

        TextView title = new TextView(this);
        title.setText("اولویت");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam-Bold.ttf"));

        builder.setCustomTitle(title);

        final CharSequence[] charSequence = new CharSequence[] {"عادی","مهم","ضروری","فوق ضروری"};

        builder.setSingleChoiceItems(charSequence, 0, (dialog, which) -> {

        });

        String positiveText = "اوکی";
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {

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
        positiveButton.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.RED);
        negativeButton.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Shabnam.ttf"));

    }
}