package com.vagnnermartins.irregularverbs.task;

import android.os.AsyncTask;

import com.vagnnermartins.irregularverbs.Singleton.SingletonAdapter;
import com.vagnnermartins.irregularverbs.bean.Verb;
import com.vagnnermartins.irregularverbs.callback.Callback;

import java.util.List;

/**
 * Created by vagnnermartins on 02/02/16.
 */
public class GetVerbsTask extends AsyncTask<Void, Void, List<Verb>> {

    private final Callback callback;

    public GetVerbsTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Verb> doInBackground(Void... params) {
        return SingletonAdapter.getInstance().getAdapter().findAll(Verb.class);
    }

    @Override
    protected void onPostExecute(List<Verb> result) {
        super.onPostExecute(result);
        this.callback.onReturn(null, result);
    }
}
