package com.vagnnermartins.irregularverbs.ui.helper;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class GameActivityHelper {

    public View view;
    public Toolbar toolbar;

    public GameActivityHelper(View view){
        this.view = view;
        this.toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }
}
