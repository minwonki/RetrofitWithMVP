package com.example.wkmin.rxsample.mvp;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wk.min on 28/09/2017.
 * Sample GitHubService
 */

interface GitHubService {
    @GET("users/{username}")
    Observable<GitHubModel> getGitHubUser(@Path("username") String userName);
}
