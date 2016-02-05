package com.vagnnermartins.irregularverbs.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.ui.activity.GameActivity;
import com.vagnnermartins.irregularverbs.ui.helper.PlayHelper;
import com.vagnnermartins.irregularverbs.ui.helper.VerbHelper;
import com.vagnnermartins.irregularverbs.util.NavegationUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayFragment extends Fragment {

    private PlayHelper ui;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = new PlayHelper(inflater.inflate(R.layout.fragment_play, container, false));
        init();
        return ui.view;
    }

    private void init() {
        ui.play.setOnClickListener(onPlayClickListener());
    }

    private View.OnClickListener onPlayClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Serializable> extras = new HashMap<>();
                GameTypeEnum gameType = null;
                if(ui.ps.isChecked() && ui.pp.isChecked()){
                    gameType = GameTypeEnum.BOTH;
                }else if(ui.ps.isChecked()){
                    gameType = GameTypeEnum.SP;
                }else if(ui.pp.isChecked()){
                    gameType = GameTypeEnum.PP;
                }
                extras.put(GameFragment.GAME_TYPE, gameType);
                NavegationUtil.startActivityExtras(getActivity(), GameActivity.class, extras);
            }
        };
    }
}
