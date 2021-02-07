package com.pas.rastatask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.pas.rastatask.API.APIClient;
import com.pas.rastatask.API.APIInterface;
import com.pas.rastatask.MyRecycler.CustomAdapter;
import com.pas.rastatask.MyRecycler.RecyclerItemClickListener;
import com.pas.rastatask.myclass.Library;
import com.pas.rastatask.myclass.startActivity;
import com.pas.rastatask.retroalltask.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressLint("RtlHardcoded")
public class ListActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private DrawerLayout drawerLayout1;
    private TextView lbHeder;

    FloatingActionButton floatingActionButton;

//    add view

    ArrayList<String> statusAsliTask = new ArrayList<>();
    ArrayList<String> onvanAsliTask = new ArrayList<>();
    ArrayList<String> contentAsliTask = new ArrayList<>();
    ArrayList<String> statusTask = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    CustomAdapter customAdapter;

    int pages = 1;

//    end view

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        drawerLayout1 = findViewById(R.id.drawerLayout);
        floatingActionButton = findViewById(R.id.fab);

        if (Library.readAHSharedPreferences(this, "TypeUser").equals("2")) {
            floatingActionButton.setVisibility(View.GONE);
        } else {
            floatingActionButton.setOnClickListener(v -> {
                finish();
                startActivity.Activity2(this,AddMngActivity.class);
            });
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.item1:
                    Log.d("TAG", "item1");
                    break;
                case R.id.item2:
                    Log.d("TAG", "item12");
                    break;
                case R.id.item3:
                    Log.d("TAG", "item13");
                    break;
            }

            return false;
        });

        LinearLayout pan_menu = findViewById(R.id.pan_menu);
        pan_menu.setOnClickListener(v -> {
            if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout1.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout1.openDrawer(Gravity.RIGHT);
            }
        });

//        lbHeder = findViewById(R.id.lb_menu_header);
//        lbHeder.setText(Library.readAHSharedPreferences(this,"NameUser"));
//        lbHeder.setTypeface(Library.changeFont(this, false));

//        add view

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new CustomAdapter(ListActivity.this, id, statusAsliTask, onvanAsliTask, contentAsliTask, statusTask);
        recyclerView.setAdapter(customAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startActivity.Activity2(ListActivity.this,AddUserActivity.class);
                        Library.saveAHSharedPreferences(ListActivity.this,"TagShow",view.getTag());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        luddite();

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.teal_200,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            statusAsliTask.clear();
            onvanAsliTask.clear();
            contentAsliTask.clear();
            statusTask.clear();
            Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
            pages = 1;
            luddite();
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
//                    mSwipeRefreshLayout.setRefreshing(true);
//                    pages++;
//                    luddite();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Library.readAHSharedPreferences(ListActivity.this, "TypeUser").equals("1")) {
                    if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                        floatingActionButton.hide();
                    } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                        floatingActionButton.show();
                    }
                }
            }
        });

//        end view

    }


    void luddite() {

        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<Task> call = service.getAllTask("Bearer "+Library.readAHSharedPreferences(ListActivity.this, "Token"), "Mnek!w@ZP(*s");

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    Task getBody = response.body();
                    int getCode = Objects.requireNonNull(getBody).getResponse().getCode();

                    if (getCode == 200) {

                        for (int i = 0; i < getBody.getResponse().getMessage().size(); i++) {
                            id.add(getBody.getResponse().getMessage().get(i).getId());
                            onvanAsliTask.add(getBody.getResponse().getMessage().get(i).getTitle());
                            contentAsliTask.add(getBody.getResponse().getMessage().get(i).getComment());
                            statusTask.add(getBody.getResponse().getMessage().get(i).getColorStatus());
                            statusAsliTask.add("وضعیت: " + getBody.getResponse().getMessage().get(i).getStatus());
                        }

                        customAdapter.notifyDataSetChanged();

                        mSwipeRefreshLayout.setRefreshing(false);

                    } else {
                        Toast.makeText(ListActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ListActivity.this, "خطا، لطفا مجددا تلاش فرمائید.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Task> call, @NonNull Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout1.closeDrawer(Gravity.RIGHT);
            } else {
                if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                    super.onBackPressed();
                    finish();
                    System.exit(0);
                } else {
                    Toast.makeText(getBaseContext(), "برای خروج دوباره کلیک کنید...", Toast.LENGTH_SHORT).show();
                }
                mBackPressed = System.currentTimeMillis();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {

            if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout1.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout1.openDrawer(Gravity.RIGHT);
            }
            return true;

        } else {
            return super.onKeyUp(keyCode, event);
        }
    }

}