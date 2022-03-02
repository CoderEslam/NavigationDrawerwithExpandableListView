package com.doubleclick.navigationdrawerwithexpandablelistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] item;
    private ExpandableListView expandableListView;
    private castomExtandableListAdapter castomExtandableListAdapter;
    private List<String> listTitel;
    private Map<String,List<String>> listChild;
    private NavigationManger navigationManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        navigationManger = FragemtNevigationManeger.getInstance(MainActivity.this);
        
        initView();

        getData();

        addDrawerItem();
        setUpDrawer();

        if (savedInstanceState==null){
            selectFristItemAsDefult();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("EslamGhazy");

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFristItemAsDefult() {
        if (navigationManger!=null){
            String fristItem = listTitel.get(0);
            navigationManger.showFragment(fristItem);
            getSupportActionBar().setTitle(fristItem);
        }

    }

    private void setUpDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                getSupportActionBar().setTitle("EslamGhazy");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();

            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    private void addDrawerItem() {
        castomExtandableListAdapter =  new castomExtandableListAdapter(this,listTitel, listChild);
        expandableListView.setAdapter(castomExtandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(listTitel.get(groupPosition).toString()); //setTitel for ToolBar when open menu.

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("EslamGhazy");

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // change fragment when click on item

                String selectedItem =((List) (listChild.get(listTitel.get(groupPosition)))).get(childPosition).toString();
                String selecetedTitel = listTitel.get(groupPosition).toString();

                getSupportActionBar().setTitle(selectedItem);
                Toast.makeText(MainActivity.this,""+selectedItem,Toast.LENGTH_LONG).show();
//                Toast.makeText(MainActivity.this,""+selecetedTitel,Toast.LENGTH_LONG).show();

//                    if (item[0].equals(listTitel.get(groupPosition))) {
//                        navigationManger.showFragment(selectedItem);
//                    }
//                    else {
//                        throw new IllegalArgumentException("Not Supported fragment");
//                    }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    private void getData() {

//        List<String> title = Arrays.asList("E","S","L","f");
//        List<String> title = new ArrayList<>();
//        title.add("E");
//        title.add("S");
//        title.add("L");
//        title.add("f");

        List<String> childItem = Arrays.asList(getResources().getStringArray(R.array.Computer));
//        List<String> childItem = new ArrayList<>();
//        childItem.add("Bigan");
//        childItem.add("InterMdiat");
//        childItem.add("Advanc");
//        childItem.add("Profissonal");

        List<String> electronics = Arrays.asList("hiter","frizer","televition","fan");

        List<String> e1 = Arrays.asList("de","gt","fr","hy");

        List<String> e2 = Arrays.asList("cc","fg","rg","we");

        listChild = new TreeMap<>();
//        listChild = new HashMap<>();

//        listChild.put(title.get(3),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(2),e1);
//        listChild.put(title.get(3),e2);
        ExpandableListDataSource expandableListDataSource = new ExpandableListDataSource();
        listChild = expandableListDataSource.getData(MainActivity.this);

        Toast.makeText(MainActivity.this,"Key Set = "+listChild.keySet(),Toast.LENGTH_LONG).show();
        listTitel = new ArrayList<>(); //to linked child with perants
        listTitel.addAll(listChild.keySet());

    }

    private void initView() {

//        item = new String[]{"E","S","L","f"};

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}