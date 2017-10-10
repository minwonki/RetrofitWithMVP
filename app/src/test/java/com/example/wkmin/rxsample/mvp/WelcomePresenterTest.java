package com.example.wkmin.rxsample.mvp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by wk.min on 29/09/2017.
 *
 */
public class WelcomePresenterTest {

    @Mock
    GitHubService gitHubService;

    @Mock
    WelcomeContract.View view;

    private WelcomePresenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.presenter = new WelcomePresenter(view, gitHubService);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.reset();
    }

    @Test
    public void testSetPresenter() throws Exception {
        this.presenter = new WelcomePresenter(view, gitHubService);
        verify(view).setPresenter(presenter);
    }

    @Test
    public void testRetrofitLoad() throws Exception {
        GitHubModel gitHubModel = new GitHubModel();
        gitHubModel.setUrl("sampleUrl");

        when(gitHubService.getGitHubUser("minwonki")).thenReturn(Observable.just(gitHubModel));
        presenter.load();
        verify(view).showMessage(gitHubModel);
    }
}