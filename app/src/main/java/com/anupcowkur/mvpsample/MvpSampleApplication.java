/*
 * MvpSampleApplication.java
 * MVP test
 *
 * Created by Miroslav Ignjatovic on 1/6/2017.
 * Copyright (c) 2016 Novotek All rights reserved.
 */

package com.anupcowkur.mvpsample;

import android.app.Activity;
import android.app.Application;

import com.anupcowkur.mvpsample.dagger.components.DaggerMvpSampleApplicationComponent;
import com.anupcowkur.mvpsample.dagger.components.MvpSampleApplicationComponent;
import com.anupcowkur.mvpsample.dagger.modules.AppModule;
import com.anupcowkur.mvpsample.dagger.modules.ContextModule;

public class MvpSampleApplication extends Application {

    public static MvpSampleApplication get(Activity activity) {
        return (MvpSampleApplication) activity.getApplication();
    }

    private MvpSampleApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // need to initialize only modules which need constructor parameter
        component = DaggerMvpSampleApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .appModule(new AppModule(this))
                .build();
    }

    public MvpSampleApplicationComponent getComponent() {
        return component;
    }

}
