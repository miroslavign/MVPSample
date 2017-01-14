/*
 * NetModule.java
 * MVP test
 *
 * Created by Miroslav Ignjatovic on 1.1.2017.
 * Copyright (c) 2016 Novotek All rights reserved.
 */

package com.anupcowkur.mvpsample.dagger.modules;

import android.content.Context;

import com.anupcowkur.mvpsample.dagger.scopes.MvpSampleApplicationScope;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = ContextModule.class)
public class NetModule {

    private static final int TIMEOUT = 30;

    @MvpSampleApplicationScope
    @Provides
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @MvpSampleApplicationScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @MvpSampleApplicationScope
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @MvpSampleApplicationScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }
}
