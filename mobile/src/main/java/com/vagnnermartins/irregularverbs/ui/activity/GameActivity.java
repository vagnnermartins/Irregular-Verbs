package com.vagnnermartins.irregularverbs.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.ui.fragment.GameFragment;
import com.vagnnermartins.irregularverbs.ui.fragment.ResultGameFragment;
import com.vagnnermartins.irregularverbs.ui.helper.GameActivityHelper;

import java.util.List;

public class GameActivity extends AppCompatActivity implements GameFragment.GameOverListener {

    private GameActivityHelper ui;
    private GameTypeEnum gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    private void init() {
        recoverExtras();
        ui = new GameActivityHelper(getWindow().getDecorView().findViewById(android.R.id.content));
        setSupportActionBar(ui.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment fragment = GameFragment.newInstance(gameType);
        initFragment(fragment);
    }

    private void recoverExtras() {
        gameType = (GameTypeEnum) getIntent().getExtras().getSerializable(GameFragment.GAME_TYPE);
    }

    private void initFragment(Fragment fragment){
        try{
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.game_content_frame, fragment).commit();
        }catch (Exception e){}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGameOver(List<ResultVerbPojo> correctVerbs, List<ResultVerbPojo> wrongVerbs, GameTypeEnum gameType) {
        getSupportActionBar().setTitle(R.string.title_fragment_result);
        initFragment(ResultGameFragment.newInstance(correctVerbs, wrongVerbs, gameType));
    }
}
