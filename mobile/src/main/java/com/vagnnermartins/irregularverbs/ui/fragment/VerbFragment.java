package com.vagnnermartins.irregularverbs.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.adapter.VerbAdapter;
import com.vagnnermartins.irregularverbs.app.App;
import com.vagnnermartins.irregularverbs.ui.helper.VerbHelper;

public class VerbFragment extends Fragment {

    private App app;
    private VerbHelper ui;
    private VerbAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = new VerbHelper(inflater.inflate(R.layout.fragment_verb, container, false));
        init();
        return ui.view;
    }

    private void init() {
        app = (App) getActivity().getApplication();
        ui.searchView.setOnQueryTextListener(onQueryTextListener());
        setList();
    }

    private void setList(){
        listAdapter = new VerbAdapter(getActivity(), R.layout.item_verb, app.verbs);
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
