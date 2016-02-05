package com.vagnnermartins.irregularverbs.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.ui.helper.VerbHelper;

/**
 * Created by vagnnermartins on 05/02/16.
 */
public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
