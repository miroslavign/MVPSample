package com.anupcowkur.mvpsample.ui.presenters;

import com.anupcowkur.mvpsample.events.ErrorEvent;
import com.anupcowkur.mvpsample.events.NewPostsEvent;
import com.anupcowkur.mvpsample.model.ApiEndpointService;
import com.anupcowkur.mvpsample.model.pojo.Post;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PostsPresenter {

    ApiEndpointService apiEndpointService;

    @Inject
    PostsPresenter(ApiEndpointService apiEndpointService) {
        this.apiEndpointService = apiEndpointService;
    }

    public void loadPostsFromAPI() {

        apiEndpointService.getPostsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onNext(List<Post> newPosts) {
                        EventBus.getDefault().post(new NewPostsEvent(newPosts));
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(new ErrorEvent());
                    }

                });
    }

}
