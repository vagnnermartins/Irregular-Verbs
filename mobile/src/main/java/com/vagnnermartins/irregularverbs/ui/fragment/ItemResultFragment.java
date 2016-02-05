package com.vagnnermartins.irregularverbs.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.adapter.ItemResultAdapter;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.ui.helper.ItemResultHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vagnnermartins on 05/02/16.
 */
public class ItemResultFragment extends Fragment {

    private static final String VERBS = "verbs";
    private ItemResultHelper ui;
    private ItemResultAdapter listAdapter;
    private List<ResultVerbPojo> verbs;
    private GameTypeEnum gameTypeEnum;

    public static ItemResultFragment newInstance(List<ResultVerbPojo> verbs, GameTypeEnum gameTypeEnum) {
        ItemResultFragment fragment = new ItemResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(VERBS, (Serializable) verbs);
        args.putSerializable(ResultGameFragment.GAME_TYPE, gameTypeEnum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = new ItemResultHelper(inflater.inflate(R.layout.fragment_item_result, container, false));
        init();
        return ui.view;
    }

    private void init() {
        recoverExtras();
        ui.searchView.setOnQueryTextListener(onQueryTextListener());
        setList();
    }

    private void recoverExtras() {
        gameTypeEnum = (GameTypeEnum) getArguments().getSerializable(GameFragment.GAME_TYPE);
        verbs = (List<ResultVerbPojo>) getArguments().getSerializable(VERBS);
    }

    private void setList(){
        listAdapter = new ItemResultAdapter(getActivity(), R.layout.item_result, verbs, gameTypeEnum);
        ui.listView.setAdapter(listAdapter);
    }

    private SearchView.OnQueryTextListener onQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(listAdapter != null){
                    listAdapter.getFilter().filter(newText);
                }
                return false;
            }
        };
    }
}
