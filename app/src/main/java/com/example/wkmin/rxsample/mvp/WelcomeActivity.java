package com.example.wkmin.rxsample.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.wkmin.rxsample.R;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

    private WelcomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setMVP();
        this.presenter.load();
    }

    private void setMVP() {
        new WelcomePresenter(this, new DefaultRetrofit().invoke().create(GitHubService.class));
    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(GitHubModel gitHubModel) {
        Toast.makeText(this, gitHubModel.getUrl(), Toast.LENGTH_LONG).show();
    }
}
