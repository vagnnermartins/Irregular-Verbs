package com.vagnnermartins.irregularverbs.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.app.App;
import com.vagnnermartins.irregularverbs.ui.fragment.AboutFragment;
import com.vagnnermartins.irregularverbs.ui.fragment.PlayFragment;
import com.vagnnermartins.irregularverbs.ui.fragment.VerbFragment;
import com.vagnnermartins.irregularverbs.ui.helper.MainHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private App app;
    private MainHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        app = (App) getApplication();
        helper = new MainHelper(getWindow().getDecorView().findViewById(android.R.id.content));
        setSupportActionBar(helper.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, helper.drawer, helper.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        helper.drawer.setDrawerListener(toggle);
        toggle.syncState();
        helper.navigationView.setNavigationItemSelectedListener(this);
        helper.navigationView.setCheckedItem(R.id.nav_play);
        selectItem(PlayFragment.class.getName());
        getSupportActionBar().setTitle(R.string.play);
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
        int id = item.getItemId();
        if (id == R.id.nav_play) {
            getSupportActionBar().setTitle(R.string.play);
            selectItem(PlayFragment.class.getName());
        }else if(id == R.id.nav_verbs){
            getSupportActionBar().setTitle(R.string.verbs);
            selectItem(VerbFragment.class.getName());
        }else if(id == R.id.nav_about){
            getSupportActionBar().setTitle(R.string.about);
            selectItem(AboutFragment.class.getName());
        }
        helper.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectItem(String fragmentName) {
        Fragment fragment = Fragment.instantiate(this, fragmentName, new Bundle());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content_frame, fragment).commit();
    }
}
