package com.example.wkmin.rxsample.mvp;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wk.min on 29/09/2017.
 * WelcomePresenter
 */

class WelcomePresenter implements WelcomeContract.Presenter {
    private WelcomeContract.View view;
    private GitHubService service;

    WelcomePresenter(WelcomeContract.View view, GitHubService service) {
        this.view = view;
        this.service = service;
        this.view.setPresenter(this);
    }

    @Override
    public void load() {

        Observable<GitHubModel> gitHubModel = service.getGitHubUser("minwonki");
        Log.d("Output", "subscribeOn");
        gitHubModel.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHubModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("Output", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull GitHubModel gitHubModel) {
                        Log.d("Output", "onNext");
                        Log.d("Output", gitHubModel.getUrl());
                        view.showMessage(gitHubModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Output", "onError:"+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Output", "onComplete");
                    }
                });
    }

}
