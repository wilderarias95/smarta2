package com.wilderarias.smarta2.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.wilderarias.smarta2.cuadrar.CuadrarActivity;
import com.wilderarias.smarta2.InfoAppActivity;
import com.wilderarias.smarta2.login.LoginActivity;
import com.wilderarias.smarta2.R;

public class MainActivity extends NavigationDrawerActivity implements NavigationView.OnNavigationItemSelectedListener {

    PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager());
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.iRutaBello) {
            viewPager.setCurrentItem(0); //Se desplaza a la vista de la rutaBello
        } else if (id == R.id.iCuadrar) {
            intent=new Intent(MainActivity.this,CuadrarActivity.class);
            startActivity(intent);
        }  else if (id == R.id.iInfoApp) {
            intent=new Intent(MainActivity.this,InfoAppActivity.class);
            startActivity(intent);
        } else if (id == R.id.iCerrar) {
            intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
