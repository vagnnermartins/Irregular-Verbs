package com.vagnnermartins.irregularverbs.pojo;

import com.vagnnermartins.irregularverbs.bean.Verb;

/**
 * Created by vagnnermartins on 04/02/16.
 */
public class ResultVerbPojo {

    private Verb verb;
    private String sp;
    private String pp;

    public ResultVerbPojo(Verb verb, String sp, String pp) {
        this.verb = verb;
        this.sp = sp;
        this.pp = pp;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    @Override
    public String toString() {
        return verb.toString() + sp + pp;
    }
}
