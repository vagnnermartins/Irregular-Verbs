package com.vagnnermartins.irregularverbs.ui.helper;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class ResultGameHelper {

    public View view;
    public Toolbar toolbar;
    public TextView timer;
    public ViewPager mViewPager;
    public TabLayout tabLayout;

    public ResultGameHelper(View view){
        this.view = view;
        this.toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        this.timer = (TextView) view.findViewById(R.id.game_timer);
        this.mViewPager = (ViewPager) view.findViewById(R.id.result_game_pager);
        this.tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    }
}
