package com.vagnnermartins.irregularverbs.app;

import android.app.Application;
import android.util.Log;

import com.codeslap.persistence.DatabaseSpec;
import com.codeslap.persistence.PersistenceConfig;
import com.vagnnermartins.irregularverbs.Singleton.SingletonAdapter;
import com.vagnnermartins.irregularverbs.bean.Verb;
import com.vagnnermartins.irregularverbs.db.Database;

import java.util.List;

/**
 * Created by vagnnermartins on 02/02/16.
 */
public class App extends Application {

    public List<Verb> verbs;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initDB();
    }

    private void initDB() {
        Database db = new Database(getApplicationContext());
        db.getWritableDatabase();
        DatabaseSpec database = PersistenceConfig.registerSpec(Database.DATABASE_SPEC, Database.DATABASE_VERSION);
        database.match(Verb.class);
        SingletonAdapter.getInstance(getApplicationContext());
        List<Verb> verbs = SingletonAdapter.getInstance().getAdapter().findAll(Verb.class);
    }
}
