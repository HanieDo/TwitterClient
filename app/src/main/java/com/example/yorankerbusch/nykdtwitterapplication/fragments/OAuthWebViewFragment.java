package com.example.yorankerbusch.nykdtwitterapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yorankerbusch.nykdtwitterapplication.R;

public class OAuthWebViewFragment extends Fragment {
//    private AuthenticationFragmentListener authListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oauth_web_view, container, false);

        //Code for the fragment here...

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
