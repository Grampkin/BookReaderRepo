package com.example.admin.test2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.test2.Adapter.MyAdapter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private Button like, gotoView;
    private static final String TAG = "myLogs";

    private ViewPager viewPager;
    private TabLayout tabLayout;


    private SectionPagerAdapter sectionPagerAdapter;

//    boolean buttonState;

//    List<Item> items = new ArrayList<>();
//    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG,"test");

        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);



        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        like();
//        gotoViewer();

        //Randomizer
//        random10Data();

        //Init view
//        RecyclerView recycler = (RecyclerView)findViewById(R.id.book_rv);
//        RecyclerView recycler2 = (RecyclerView)findViewById(R.id.book_rv_2);
//        RecyclerView recycler3 = (RecyclerView)findViewById(R.id.book_rv_3);
//
//        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        adapter = new MyAdapter(recycler,this,items);
//        recycler.setAdapter(adapter);
//
//        recycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        adapter = new MyAdapter(recycler2,this,items);
//        recycler2.setAdapter(adapter);
//
//        recycler3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        adapter = new MyAdapter(recycler3,this,items);
//        recycler3.setAdapter(adapter);

//        final AssetManager mgr = getAssets();
//        getBooksList(mgr,"books");

        //Set load more
//        adapter.setLoadMore(new ILoadMore() {
//            @Override
//            public void onLoadMore() {
//                if (items.size() <= 20) {
//                    items.add(null);
//                    adapter.notifyItemChanged(items.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            items.remove(items.size()-1);
//                            adapter.notifyItemRemoved(items.size());
//
//                            //Randome more
//                            int index = items.size();
//                            int end = index+10;
//                            for(int i = index; i<end;i++) {
//                                String bookName = UUID.randomUUID().toString();
//                                Item item = new Item(bookName, "Твой отец");
//                                items.add(item);
//                            }
//                            adapter.notifyDataSetChanged();
//                            adapter.setLoading();
//                        }
//                    },2000);
//                } else {
//                    Toast.makeText(MainActivity.this, "No more books!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }

    public void setupViewPager(ViewPager viewPager) {
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        sectionPagerAdapter.addFragment(new ShelfMode(), "SHELF");
        sectionPagerAdapter.addFragment(new ListMode(), "LIST");
        viewPager.setAdapter(sectionPagerAdapter);

//        final Intent intent = new Intent(this, MyAdapter.class);
//
//
//         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//
//
//                int pos = tab.getPosition();
//
//                intent.putExtra("Selected tab",pos);
//                startActivity(intent);
//
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });



    }








//    public void getBooksList(AssetManager assetManager, String path) {
//        try {
//            String list[] = assetManager.list(path);
//            if (list != null)
//                for (int i = 0; i < list.length; ++i) {
//                    String bookName = list[i];
//                    String shortBookName = bookName.substring(0,Math.min(bookName.length(),bookName.length()-4));
////                    shortBookName = shortBookName.substring(0,1).toUpperCase() + shortBookName.substring(1);
//                    Item item = new Item(shortBookName,"Author of this pretty good book");
//                    items.add(item);
//                    Log.v("Assets", list[i]);
//                    getBooksList(assetManager, path + "/" + list[i]);
//                }
//        } catch (IOException e) {
//            Log.v("List error:", "can't list" + path);
//        }
//
//
//    }




//    public void random10Data() {
//        for (int i = 0; i < 10; i++) {
//            String bookName = UUID.randomUUID().toString();
//            Item item = new Item(bookName, "Твоя мать");
//            items.add(item);
//        }
//    }

//    public void gotoViewer() {
//        gotoView = findViewById(R.id.invButton);
//
//        gotoView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(".ViewerMode");
//                        startActivity(i);
//                    }
//                }
//        );
//    }

//    public void like() {
//
//        like = findViewById(R.id.likeButton);
//
//        like.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(buttonState==false){
//                            like.setBackgroundResource(R.drawable.like_button);
//                            buttonState = true;
//                        }
//                        else {
//                            like.setBackgroundResource(R.drawable.dislike_button);
//                            buttonState = false;
//                        }
//                    }
//                }
//        );
//
//    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void showToast() {
        Toast.makeText(this,"Unable on this version",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.filter:
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
                builder.setTitle("Filter");
                final String[] filter_modes = new String[] {
                        "By title",
                        "By author name",
                        "Last read first",
                        "Favorite first"
                };



                builder.setSingleChoiceItems(filter_modes, 0, null);


                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast();
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_change_acc) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
