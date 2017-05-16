package com.example.yorankerbusch.nykdtwitterapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yorankerbusch.nykdtwitterapplication.R;
import com.example.yorankerbusch.nykdtwitterapplication.SingletonTweets;
import com.example.yorankerbusch.nykdtwitterapplication.TweetListAdapter;

/**
 * Fragment for the list of all tweets on the main menu of the app.
 */
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
                //A tweet has been clicked! Get the name of the tweet's writer from the adapter.
                String requestedUserName = tweetListAdapter.getItem(position).getUserName();

                //Send this user's name to the main activity so it can handle it.
                mListener.onFragmentInteraction(requestedUserName);
            }
        });

        return rootView;
    }

    /**
     * Standard method to check if another activity implementing the OnFragmentInteractionListener
     *  also implements the method and vice versa. If yes, set the listener.
     *
     * @param context is the context of the activity using the interface.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * TODO: What is this method exactly???
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Interface to let other activities know a list item has been clicked.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String userName);
    }
}
