package com.example.wkmin.rxsample.mvp;

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
        System.out.println("subscribeOn");
        System.out.println("model -> " + gitHubModel);
        gitHubModel.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHubModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull GitHubModel gitHubModel) {
                        System.out.println("onNext");
                        System.out.println("model -> " + gitHubModel);
                        System.out.println("model url -> " + gitHubModel.getUrl());

                        view.showMessage(gitHubModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
