package com.vagnnermartins.irregularverbs.ui.helper;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 03/02/16.
 */
public class PlayHelper {

    public View view;
    public CheckBox ps;
    public CheckBox pp;
    public FloatingActionButton play;

    public PlayHelper(View view){
        this.view = view;
        this.ps = (CheckBox) view.findViewById(R.id.play_ps);
        this.pp = (CheckBox) view.findViewById(R.id.play_pp);
        this.play = (FloatingActionButton) view.findViewById(R.id.play_start);
        init();
    }

    private void init() {
        pp.setOnCheckedChangeListener(onCheckedChangeListener());
        ps.setOnCheckedChangeListener(onCheckedChangeListener());
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!ps.isChecked() && !pp.isChecked()){
                    play.setEnabled(false);
                    play.setAlpha(0.5f);
                }else{
                    play.setEnabled(true);
                    play.setAlpha(1f);
                }
            }
        };
    }
}
