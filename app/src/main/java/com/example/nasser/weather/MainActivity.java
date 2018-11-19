package com.example.nasser.weather;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    FragmentManager fManager = getFragmentManager();
    Fragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Show cities", Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){

        }
        toolbar.setTitle("Weather app");
        toolbar.setSubtitle("Get forcast in an easy way");
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDrawer,
                R.string.closeDrawer){
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
            }
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
            }
        };

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    //Show the temperature for three days for a specific city
                    case R.id.show_cities:
                        frag = new CityTemperature();
                        fManager.beginTransaction().add(R.id.fragmentHolder, frag).commit();
                        return true;
                    case R.id.about_us:
                        frag = new CityTemperature();
                        fManager.beginTransaction().add(R.id.fragmentHolder, frag).commit();
                        return true;
                    default:
                        return true;
                }
            }
        });



    }


}
