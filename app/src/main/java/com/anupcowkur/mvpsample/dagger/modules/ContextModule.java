package com.anupcowkur.mvpsample.dagger.modules;

import android.content.Context;

import com.anupcowkur.mvpsample.dagger.scopes.MvpSampleApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @MvpSampleApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
