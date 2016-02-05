package com.vagnnermartins.irregularverbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.ui.fragment.ItemResultFragment;
import com.vagnnermartins.irregularverbs.ui.fragment.ResultGameFragment;

import java.util.List;

public class ResultPagerAdapter extends FragmentPagerAdapter {

    private List<ResultVerbPojo> correctVerbs, wrongVerbs;
    private final GameTypeEnum gameType;

    public ResultPagerAdapter(FragmentManager fm, List<ResultVerbPojo> correctVerbs,
                              List<ResultVerbPojo> wrongVerbs, GameTypeEnum gameType) {
        super(fm);
        this.correctVerbs = correctVerbs;
        this.wrongVerbs = wrongVerbs;
        this.gameType = gameType;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ItemResultFragment.newInstance(correctVerbs, gameType);
            case 1:
                return ItemResultFragment.newInstance(wrongVerbs, gameType);
            case 2:
                return ItemResultFragment.newInstance(correctVerbs, gameType);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Hits";
            case 1:
                return "Mistakes";
            case 2:
                return "Ranking";
        }
        return null;
    }
}