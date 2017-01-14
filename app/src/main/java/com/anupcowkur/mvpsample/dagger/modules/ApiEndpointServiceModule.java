/*
 * ApiEndpointServiceModule.java
 * MVP test
 *
 * Created by Miroslav Ignjatovic on 1/6/2017.
 * Copyright (c) 2016 Novotek All rights reserved.
 */

package com.anupcowkur.mvpsample.dagger.modules;

import com.anupcowkur.mvpsample.dagger.scopes.MvpSampleApplicationScope;
import com.anupcowkur.mvpsample.model.ApiEndpointService;
import com.anupcowkur.mvpsample.model.Constants;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetModule.class)
public class ApiEndpointServiceModule {

    @MvpSampleApplicationScope
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ADDRESS)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @MvpSampleApplicationScope
    @Provides
    ApiEndpointService provideApiEndpointService(Retrofit retrofit) {
        return retrofit.create(ApiEndpointService.class);
    }

}
