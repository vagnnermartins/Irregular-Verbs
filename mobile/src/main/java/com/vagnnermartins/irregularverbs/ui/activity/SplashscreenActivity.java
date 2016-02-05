package com.vagnnermartins.irregularverbs.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.app.App;
import com.vagnnermartins.irregularverbs.bean.Verb;
import com.vagnnermartins.irregularverbs.callback.Callback;
import com.vagnnermartins.irregularverbs.task.GetVerbsTask;
import com.vagnnermartins.irregularverbs.util.NavegationUtil;

import java.util.List;

public class SplashscreenActivity extends AppCompatActivity {

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        init();
    }

    private void init() {
        app = (App) getApplication();
        new GetVerbsTask(onGetVerbsCallback()).execute();
    }

    private Callback onGetVerbsCallback() {
        return new Callback() {
            @Override
            public void onReturn(Exception e, Object... args) {
                app.verbs = (List<Verb>) args[0];
                NavegationUtil.startActivity(SplashscreenActivity.this, MainActivity.class);
                finish();
            }
        };
    }
}
