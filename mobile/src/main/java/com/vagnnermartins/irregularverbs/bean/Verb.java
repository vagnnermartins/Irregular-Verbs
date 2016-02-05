package com.vagnnermartins.irregularverbs.bean;

/**
 * Created by vagnnermartins on 02/02/16.
 */
public class Verb {

    private int id;
    private String infinitive;
    private String sp;
    private String pp;

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    @Override
    public String toString() {
        return infinitive + sp + pp;
    }
}
