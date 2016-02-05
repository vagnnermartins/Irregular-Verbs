package com.vagnnermartins.irregularverbs.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.adapter.ResultPagerAdapter;
import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.ui.helper.ResultGameHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class ResultGameFragment extends Fragment {

    private static final String CORRECT_VERBS = "correct_verbs";
    private static final String WRONG_VERBS = "wrong_verbs";
    public static final String GAME_TYPE = "game_type";
    private ResultGameHelper ui;
    private ResultPagerAdapter mSectionsPagerAdapter;
    private List<ResultVerbPojo> correctVerbs, wrongVerbs;
    private GameTypeEnum gameType;

    public static ResultGameFragment newInstance(List<ResultVerbPojo> correctVerbs,
                                                 List<ResultVerbPojo> wrongVerbs,
                                                 GameTypeEnum gameType) {
        ResultGameFragment fragment = new ResultGameFragment();
        Bundle args = new Bundle();
        args.putSerializable(CORRECT_VERBS, (Serializable) correctVerbs);
        args.putSerializable(WRONG_VERBS, (Serializable) wrongVerbs);
        args.putSerializable(WRONG_VERBS, (Serializable) wrongVerbs);
        args.putSerializable(GAME_TYPE, gameType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = new ResultGameHelper(inflater.inflate(R.layout.fragment_result_game, container, false));
        init();
        return ui.view;
    }

    private void init() {
        recoverExtras();
        mSectionsPagerAdapter = new ResultPagerAdapter(getChildFragmentManager(), correctVerbs, wrongVerbs, gameType);
        ui.mViewPager.setAdapter(mSectionsPagerAdapter);
        ui.tabLayout.setupWithViewPager(ui.mViewPager);
    }

    private void recoverExtras() {
        correctVerbs = (List<ResultVerbPojo>) getArguments().getSerializable(CORRECT_VERBS);
        wrongVerbs = (List<ResultVerbPojo>) getArguments().getSerializable(WRONG_VERBS);
        gameType = (GameTypeEnum) getArguments().getSerializable(GAME_TYPE);
    }
}
