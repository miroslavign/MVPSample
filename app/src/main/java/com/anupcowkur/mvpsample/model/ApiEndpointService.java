/*
 * ApiEndpointInterface.java
 * MVP test
 *
 * Created by Miroslav Ignjatovic on 1.1.2017.
 * Copyright (c) 2016 Novotek All rights reserved.
 */


package com.anupcowkur.mvpsample.model;

import com.anupcowkur.mvpsample.model.pojo.Post;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiEndpointService {


    //////// GET posts /////////////
    @GET("posts")
    Observable<List<Post>> getPostsObservable();
    ////////////////////////////////

}
