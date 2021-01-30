package com.pas.rastatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

public class ListActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        drawerLayout1 = findViewById((R.id.drawerLayout));

        LinearLayout pan_close = findViewById(R.id.pan_close);
        pan_close.setOnClickListener(v -> {

            if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)){
                drawerLayout1.closeDrawer(Gravity.RIGHT);
            }else{
                drawerLayout1.openDrawer(Gravity.RIGHT);
            }
        });
    }

    @Override
    public void onBackPressed() {
        drawerLayout1.closeDrawer(Gravity.RIGHT);
    }


}