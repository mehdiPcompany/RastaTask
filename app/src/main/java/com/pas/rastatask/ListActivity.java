090package com.pas.rastatask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.pas.rastatask.retrofitdata.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressLint("RtlHardcoded")
public class ListActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private DrawerLayout drawerLayout1;

    FloatingActionButton floatingActionButton;

//    add view

    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    private static Retrofit retrofit;

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

        drawerLayout1 = findViewById((R.id.drawerLayout));
        floatingActionButton = findViewById((R.id.fab));

        floatingActionButton.setOnClickListener(v -> {
            if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout1.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout1.openDrawer(Gravity.RIGHT);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.item1:
                    startActivity(AddUserActivity.class);
                    break;
                case R.id.item2:
                    startActivity(AddMngActivity.class);
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

//        add view

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new CustomAdapter(ListActivity.this, personNames, emailIds, mobileNumbers);
        recyclerView.setAdapter(customAdapter);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        luddite();

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.teal_200,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            personNames.clear();
            emailIds.clear();
            mobileNumbers.clear();
            Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
            pages = 1;
            luddite();
        });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    pages++;
                    luddite();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
            }

        });

//        end view

    }


    void luddite() {

        APIInterface service = retrofit.create(APIInterface.class);

        Call<List<Response>> call = service.getHeroList(pages);

        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(@NonNull Call<List<Response>> call, @NonNull retrofit2.Response<List<Response>> response) {
                List<Response> responses;

                if (response.isSuccessful()) {
                    responses = response.body();
                    for (int i = 0; i < Objects.requireNonNull(responses).size(); i++) {
                        personNames.add(responses.get(i).getName());
                        emailIds.add(responses.get(i).getEmail());
                        mobileNumbers.add(responses.get(i).getBody());
                    }

                    customAdapter.notifyDataSetChanged();

                    mSwipeRefreshLayout.setRefreshing(false);


                } else {
                    Log.d("TAG", "onError");
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Response>> call, @NonNull Throwable t) {
                Log.d("TAG", Objects.requireNonNull(t.getMessage()));
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

        }else{
            return super.onKeyUp(keyCode, event);
        }
    }

    public void startActivity(Class activity) {
        Context context = ListActivity.this;
        Intent myIntent = new Intent(context, activity);
        context.startActivity(myIntent);
    }

}