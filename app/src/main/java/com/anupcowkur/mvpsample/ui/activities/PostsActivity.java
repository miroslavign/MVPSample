package com.anupcowkur.mvpsample.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.anupcowkur.mvpsample.MvpSampleApplication;
import com.anupcowkur.mvpsample.R;
import com.anupcowkur.mvpsample.events.ErrorEvent;
import com.anupcowkur.mvpsample.events.NewPostsEvent;
import com.anupcowkur.mvpsample.ui.adapters.PostsListAdapter;
import com.anupcowkur.mvpsample.ui.decorators.DividerItemDecoration;
import com.anupcowkur.mvpsample.ui.presenters.PostsPresenter;
import com.anupcowkur.mvpsample.ui.screen_contracts.PostsScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity implements PostsScreen {

    @Inject
    PostsPresenter postsPresenter;

    @BindView(R.id.posts_recycler_view)
    RecyclerView postsRecyclerView;

    @BindView(R.id.error_view)
    TextView errorView;

    PostsListAdapter postsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_posts);

        ((MvpSampleApplication)getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);

        initRecyclerView();
        postsPresenter.loadPostsFromAPI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initRecyclerView() {
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(postsRecyclerView.getContext()));
        postsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        postsRecyclerView.addItemDecoration(new DividerItemDecoration(postsRecyclerView.getContext(),
                                                                      DividerItemDecoration.VERTICAL_LIST));
        postsListAdapter = new PostsListAdapter();
        postsRecyclerView.setAdapter(postsListAdapter);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NewPostsEvent newPostsEvent) {
        hideError();
        postsListAdapter.addPosts(newPostsEvent.getPosts());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent errorEvent) {
        showError();
    }

    private void hideError() {
        postsRecyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showError() {
        postsRecyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
