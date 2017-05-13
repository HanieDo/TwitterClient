package com.example.yorankerbusch.nykdtwitterapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TweetListStartFragment extends Fragment {
    private ListView tweetListView;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tweet_list_start, container, false);

        tweetListView = (ListView) rootView.findViewById(R.id.lv_tweets_sidebar);
        final TweetListAdapter tweetListAdapter = new TweetListAdapter(rootView.getContext(), R.layout.tweet_list_item, SingletonTweets.getInstance().getTweetList());
        tweetListView.setAdapter(tweetListAdapter);

        tweetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: I have tested with toasts, but for some reason the app can't reach this
                //TODO     onClickListener. I have no clue why, could you check this? I feel like I'm
                //TODO     looking right over something xD
                String requestedUserName = tweetListAdapter.getItem(position).getUserName();

                mListener.onFragmentInteraction(requestedUserName);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String userName);
    }
}
