package com.anupcowkur.mvpsample.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anupcowkur.mvpsample.dagger.scopes.MvpSampleApplicationScope;
import com.anupcowkur.mvpsample.ui.presenters.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @MvpSampleApplicationScope
    @Provides
    MainPresenter providesMainPresenter() {
        return new MainPresenter();
    }

    @MvpSampleApplicationScope
    @Provides
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
