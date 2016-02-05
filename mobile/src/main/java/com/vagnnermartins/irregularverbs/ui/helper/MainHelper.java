package com.vagnnermartins.irregularverbs.ui.helper;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 22/01/16.
 */
public class MainHelper {

    public Toolbar toolbar;
    public DrawerLayout drawer;
    public NavigationView navigationView;

    public MainHelper(View view){
        this.toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        this.drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) view.findViewById(R.id.nav_view);
    }
}
