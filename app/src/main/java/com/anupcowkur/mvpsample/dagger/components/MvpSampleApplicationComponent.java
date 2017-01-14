/*
 * MvpSampleApplicationComponent.java
 * MVP test
 *
 * Created by Miroslav Ignjatovic on 1/14/2017.
 * Copyright (c) 2016 Novotek All rights reserved.
 */

package com.anupcowkur.mvpsample.dagger.components;

import com.anupcowkur.mvpsample.dagger.modules.ApiEndpointServiceModule;
import com.anupcowkur.mvpsample.dagger.modules.AppModule;
import com.anupcowkur.mvpsample.dagger.scopes.MvpSampleApplicationScope;
import com.anupcowkur.mvpsample.ui.activities.MainActivity;
import com.anupcowkur.mvpsample.ui.activities.PostsActivity;

import dagger.Component;

@MvpSampleApplicationScope
@Component(modules = {ApiEndpointServiceModule.class, AppModule.class})
public interface MvpSampleApplicationComponent {
    void inject(MainActivity activity);
    void inject(PostsActivity activity);
}
