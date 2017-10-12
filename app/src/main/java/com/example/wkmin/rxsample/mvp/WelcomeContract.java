package com.example.wkmin.rxsample.mvp;

import com.example.wkmin.rxsample.BasePresenter;
import com.example.wkmin.rxsample.BaseView;

/**
 * Created by wk.min on 29/09/2017.
 *
 */

interface WelcomeContract {

    interface View extends BaseView<Presenter> {
        void showMessage(GitHubModel gitHubModel);
    }

    interface Presenter extends BasePresenter {
        void load();

        void finish();
    }
}
