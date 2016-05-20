package org.fuckboilerplate.rxsocialconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.LinkedInApi20;
import com.github.scribejava.apis.TwitterApi;

import org.fuckboilerplate.rx_social_connect.RxSocialConnect;

public class RxSocialConnectFragment extends Fragment {
    private Helper helper;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new Helper(this);

        setUpTwitter();
        setUpFacebook();
        setUpGoogle();
        setUpLinkedIn();

        findViewById(R.id.bt_all_disconnect).setOnClickListener(v -> helper.closeAllConnection());
    }

    private void setUpTwitter() {
        findViewById(R.id.bt_twitter).setOnClickListener(v -> {
            RxSocialConnect.with(RxSocialConnectFragment.this, helper.twitterService())
                    .subscribe(response -> response.targetUI().helper.showResponse(response.token()),
                            error -> helper.showError(error));
        });

        findViewById(R.id.bt_twitter_disconnect).setOnClickListener(v -> helper.closeConnection(TwitterApi.class));
    }

    private void setUpFacebook() {
        findViewById(R.id.bt_facebook).setOnClickListener(v -> {
            RxSocialConnect.with(RxSocialConnectFragment.this, helper.facebookService())
                    .subscribe(response -> response.targetUI().helper.showResponse(response.token()),
                            error -> helper.showError(error));
        });

        findViewById(R.id.bt_facebook_disconnect).setOnClickListener(v -> helper.closeConnection(FacebookApi.class));
    }

    private void setUpGoogle() {
        findViewById(R.id.bt_google).setOnClickListener(v -> {
            RxSocialConnect.with(RxSocialConnectFragment.this, helper.googleService())
                    .subscribe(response -> response.targetUI().helper.showResponse(response.token()),
                            error -> helper.showError(error));
        });

        findViewById(R.id.bt_google_disconnect).setOnClickListener(v -> helper.closeConnection(GoogleApi20.class));
    }

    private void setUpLinkedIn() {
        findViewById(R.id.bt_linkedin).setOnClickListener(v -> {
            RxSocialConnect.with(RxSocialConnectFragment.this, helper.linkedinService())
                    .subscribe(response -> response.targetUI().helper.showResponse(response.token()),
                            error -> helper.showError(error));
        });

        findViewById(R.id.bt_linkedin_disconnect).setOnClickListener(v -> helper.closeConnection(LinkedInApi20.class));
    }

    private View findViewById(int resId) {
        return getView().findViewById(resId);
    }
}