package com.vagnnermartins.irregularverbs.ui.helper;

import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 03/02/16.
 */
public class VerbHelper {

    public View view;
    public ListView listView;
    public SearchView searchView;

    public VerbHelper(View view){
        this.view = view;
        this.listView = (ListView) view.findViewById(R.id.verb_list);
        this.searchView = (SearchView) view.findViewById(R.id.verb_search);
    }
}
