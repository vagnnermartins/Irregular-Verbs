package com.vagnnermartins.irregularverbs.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.app.App;
import com.vagnnermartins.irregularverbs.bean.Verb;
import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.ui.helper.GameFragmentHelper;
import com.vagnnermartins.irregularverbs.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class GameFragment extends Fragment {

    private static final int TIMER = 22000;
    private static final int LIVES = 3;
    public static final String GAME_TYPE = "game_type";

    private App app;
    private GameFragmentHelper ui;
    private GameOverListener listener;
    private int currentLife;
    private Verb currentVerb;
    private List<ResultVerbPojo> correctVerbs, wrongVerbs;
    private GameTypeEnum gameType;
    private CountDownTimer cdt;

    @SuppressWarnings("unused")
    public static GameFragment newInstance(GameTypeEnum gameType) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putSerializable(GAME_TYPE, gameType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = new GameFragmentHelper(inflater.inflate(R.layout.fragment_game, container, false));
        recoverExtras();
        init();
        return ui.view;
    }

    private void recoverExtras() {
        gameType = (GameTypeEnum) getArguments().getSerializable(GAME_TYPE);
    }

    private void init() {
        app = (App) getActivity().getApplication();
        currentLife = LIVES;
        ui.setLife(getActivity(), LIVES);
        ui.checkVisibilitiesGame(gameType);
        ui.validate.setOnClickListener(onValidateClickListener());
        sortVerb();
        correctVerbs = new ArrayList<>();
        wrongVerbs = new ArrayList<>();

    }

    private void sortVerb(){
        Random r = new Random();
        currentVerb = app.verbs.get(r.nextInt(app.verbs.size()));
        ui.infinitive.setText(currentVerb.getInfinitive());
        execTimer();
    }

    private void execTimer() {
        if(cdt != null){
            cdt.cancel();
        }
        cdt = new CountDownTimer(TIMER, 1000) {
            public void onTick(long millisUntilFinished) {
                ui.timer.setText(String.valueOf((millisUntilFinished / 1000)-1));
            }

            public void onFinish() {
                checkLives();
            }

        }.start();
    }

    private void checkLives() {
        if(isAdded()){
            currentLife--;
            if(currentLife == 0){
                listener.onGameOver(correctVerbs, wrongVerbs, gameType);
            }else{
                ui.removeOneLife();
                ui.clearEditText();
                sortVerb();
            }
        }
    }

    private View.OnClickListener onValidateClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSp = ui.sp.getEditableText().toString();
                String sPp = ui.pp.getEditableText().toString();
                ResultVerbPojo result = new ResultVerbPojo(currentVerb, sSp, sPp);
                boolean isSpellOk = true;
                if(gameType == GameTypeEnum.BOTH || gameType == GameTypeEnum.SP){
                    if(!StringUtils.compareVerb(currentVerb.getSp(), sSp)){
                        isSpellOk = false;
                    }
                }
                if(gameType == GameTypeEnum.BOTH || gameType == GameTypeEnum.PP){
                    if(!StringUtils.compareVerb(currentVerb.getPp(), sPp)){
                        isSpellOk = false;
                    }
                }
                ui.clearEditText();
                ui.requestFocus();
                if(!isSpellOk){
                    checkLives();
                    wrongVerbs.add(result);
                }else{
                    sortVerb();
                    correctVerbs.add(result);
                }
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (GameOverListener) context;
    }

    public interface GameOverListener {
        void onGameOver(List<ResultVerbPojo> correctVerbs, List<ResultVerbPojo> wrongVerbs, GameTypeEnum gameType);
    }
}
