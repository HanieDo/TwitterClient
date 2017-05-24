package com.example.yorankerbusch.nykdtwitterapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yorankerbusch.nykdtwitterapplication.BaseService;
import com.example.yorankerbusch.nykdtwitterapplication.OAuthHandler;
import com.example.yorankerbusch.nykdtwitterapplication.R;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class OAuthWebViewFragment extends Fragment {
//    private AuthenticationFragmentListener authListener;
    private WebView oAuthWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oauth_web_view, container, false);

        oAuthWebView = (WebView) rootView.findViewById(R.id.wv_oauth);

        OAuthHandler oAuthHandler = new OAuthHandler(new BaseService());

        OAuth1RequestToken oAuth1RequestToken = oAuthHandler.getRequestToken();

        //Code for the Oauth progress and webView fragment here...
        oAuthWebView.loadUrl(oAuthHandler.getAuthorizationUrl(oAuth1RequestToken));

        oAuthWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("app://twitter-dev")) {
                    //...
                }

                return false;
            }
        });

        return rootView;
    }

//    public void onButtonPressed(Uri uri) {
//        if (authListener != null) {
//            authListener.onAuthFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof AuthenticationFragmentListener) {
//            authListener = (AuthenticationFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement AuthenticationFragmentListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        authListener = null;
//    }
//
//    public interface AuthenticationFragmentListener {
//        void onAuthFragmentInteraction(Uri uri);
//    }
}
