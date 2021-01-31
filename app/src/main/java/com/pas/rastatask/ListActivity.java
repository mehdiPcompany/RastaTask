package com.pas.rastatask;

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
import java.util.Random;

@SuppressLint("RtlHardcoded")
public class ListActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private DrawerLayout drawerLayout1;

    FloatingActionButton floatingActionButton;

//    add view

    ArrayList<String> statusAsliTask = new ArrayList<>();
    ArrayList<String> onvanAsliTask = new ArrayList<>();
    ArrayList<String> contentAsliTask = new ArrayList<>();
    ArrayList<Integer> statusTask = new ArrayList<>();

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
            finish();
            startActivity(AddMngActivity.class);
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
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

//        add view

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new CustomAdapter(ListActivity.this, statusAsliTask, onvanAsliTask, contentAsliTask,statusTask);
        recyclerView.setAdapter(customAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
//                        Toast.makeText(ListActivity.this, mobileNumbers.get(position), Toast.LENGTH_SHORT).show();
                        Context context = ListActivity.this;
                        Intent myIntent = new Intent(context,AddUserActivity.class);
                        context.startActivity(myIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

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

                        int Rnd1 = Rnd(0,3);
                        String sstatus  ;
                        int col_status = 0;

                        onvanAsliTask.add("جهت تست این پروژه سریعا اقدام نمائید");
                        contentAsliTask.add("این پروژه چندین خط دارد و ممکن است برای همه باغث زیان و ضرر شود با این من این پروپزه رو قبول میکنم و ممئن باشید به نجوه احسنت آمرا به گایان خواهم رساند. جلوتر خواهیم دید.");
                        if(Rnd1==0){
                            col_status = R.drawable.recycler_item_corner_radius_red;
                        }else if(Rnd1==1){
                            col_status = R.drawable.recycler_item_corner_radius_orange;
                        }else if(Rnd1==2){
                            col_status = R.drawable.recycler_item_corner_radius_green;
                        }
                        sstatus = "درحال بررسی";
                        statusTask.add(col_status);
                        statusAsliTask.add("وضعیت: "+sstatus);

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
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(ListActivity.this,"خطا در برقراری ارتباط",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static int Rnd(int Min, int Max){
        return Min + new Random().nextInt(Max - Min);
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