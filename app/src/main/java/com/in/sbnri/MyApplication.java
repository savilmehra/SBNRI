package com.in.sbnri;


import android.app.Application;

import com.facebook.stetho.Stetho;
import com.in.sbnri.roomdatabase.RoomDatabase;


public class MyApplication extends Application {
    private RoomDatabase db;


    @Override
    public void onCreate() {
        super.onCreate();
        db = RoomDatabase.getAppDatabase(getApplicationContext());
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

    RoomDatabase getDb()
    {
        return db;
    }
}
