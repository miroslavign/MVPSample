package com.anupcowkur.mvpsample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anupcowkur.mvpsample.MvpSampleApplication;
import com.anupcowkur.mvpsample.R;
import com.anupcowkur.mvpsample.ui.presenters.MainPresenter;
import com.anupcowkur.mvpsample.ui.screen_contracts.MainScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MvpSampleApplication)getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.show_posts_button)
    public void onListSampleButtonClick() {
        mainPresenter.OnShowPostsButtonClick(this);
    }

    @Override
    public void launchPostsActivity() {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }

}
