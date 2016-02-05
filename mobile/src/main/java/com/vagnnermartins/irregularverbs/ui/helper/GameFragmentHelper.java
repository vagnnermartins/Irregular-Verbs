package com.vagnnermartins.irregularverbs.ui.helper;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class GameFragmentHelper {

    public View view;
    public TextView timer;
    private LinearLayout heartMain;
    public TextView infinitive;
    public EditText sp;
    public EditText pp;
    public TextView validate;

    public GameFragmentHelper(View view){
        this.view = view;
        this.timer = (TextView) view.findViewById(R.id.game_timer);
        this.heartMain = (LinearLayout) view.findViewById(R.id.game_heart_main);
        this.infinitive = (TextView) view.findViewById(R.id.game_infinitive);
        this.sp = (EditText) view.findViewById(R.id.game_ps);
        this.pp = (EditText) view.findViewById(R.id.game_pp);
        this.validate = (TextView) view.findViewById(R.id.game_validate);
        this.sp.addTextChangedListener(onTextChangeListener());
        this.pp.addTextChangedListener(onTextChangeListener());
    }

    private TextWatcher onTextChangeListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(checkVisibility(sp) && checkVisibility(pp)){
                    if(validateEditText(sp) || validateEditText(pp)){
                        setEnable(false);
                    }else{
                        setEnable(true);
                    }
                }else if(checkVisibility(sp) && !checkVisibility(pp)){
                    if(validateEditText(sp)){
                        setEnable(false);
                    }else{
                        setEnable(true);
                    }
                }else if(checkVisibility(pp) && !checkVisibility(sp)){
                    if(validateEditText(pp)){
                        setEnable(false);
                    }else{
                        setEnable(true);
                    }
                }
            }

            private void setEnable(boolean isEnable){
                if(isEnable){
                    validate.setAlpha(1f);
                }else {
                    validate.setAlpha(0.5f);
                }
                validate.setEnabled(isEnable);
            }

            private boolean validateEditText(EditText edit){
                return edit.getEditableText().toString().equals("");
            }

            private boolean checkVisibility(EditText edit){
                return edit.getVisibility() == View.VISIBLE;
            }
        };
    }

    public void checkVisibilitiesGame(GameTypeEnum gameType){
        if(gameType != GameTypeEnum.BOTH && gameType != GameTypeEnum.SP){
            this.sp.setVisibility(View.GONE);
        }
        if(gameType != GameTypeEnum.BOTH && gameType != GameTypeEnum.PP){
            this.pp.setVisibility(View.GONE);
        }
        checkImeOptions(gameType);
    }

    public void checkImeOptions(GameTypeEnum gameType){
        if(gameType == GameTypeEnum.SP){
            sp.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void requestFocus(){
        if(sp.getVisibility() == View.VISIBLE){
            sp.requestFocus();
        }else{
            pp.requestFocus();
        }
    }

    public void setLife(Context context, int lives){
        for (int i = 0; i < lives; i++){
            ImageView heart = new ImageView(context);
            heart.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            heart.setAdjustViewBounds(true);
            heart.setImageResource(R.drawable.heart);
            heartMain.addView(heart);
        }
    }

    public void removeOneLife(){
        if(heartMain.getChildCount() > 0){
            heartMain.removeViewAt(heartMain.getChildCount() - 1);
        }
    }

    public void clearEditText(){
        sp.setText("");
        pp.setText("");
    }
}
