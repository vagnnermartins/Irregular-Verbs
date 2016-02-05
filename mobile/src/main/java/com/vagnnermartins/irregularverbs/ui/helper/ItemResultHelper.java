package com.vagnnermartins.irregularverbs.ui.helper;

import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.vagnnermartins.irregularverbs.R;

/**
 * Created by vagnnermartins on 03/02/16.
 */
public class ItemResultHelper {

    public View view;
    public ListView listView;
    public SearchView searchView;

    public ItemResultHelper(View view){
        this.view = view;
        this.listView = (ListView) view.findViewById(R.id.item_result_list);
        this.searchView = (SearchView) view.findViewById(R.id.item_result_search);
    }
}
