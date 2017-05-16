package com.example.yorankerbusch.nykdtwitterapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yorankerbusch.nykdtwitterapplication.Model.TwitterUser;
import com.example.yorankerbusch.nykdtwitterapplication.R;
import com.example.yorankerbusch.nykdtwitterapplication.SingletonTweets;
import com.example.yorankerbusch.nykdtwitterapplication.TweetListAdapter;
import com.example.yorankerbusch.nykdtwitterapplication.TwitterMainActivity;

/**
 * Fragment for when a tweet has been clicked, displaying the user page of the tweet's writer.
 */
public class UserPageFragment extends Fragment {
    private TextView nameUser, userDescription, followersCountUser, favoritesCountUser, statusesCountUser;
    private ListView userTweetsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_page, container, false);

        nameUser = (TextView) rootView.findViewById(R.id.tv_name_user_account);
        userDescription = (TextView) rootView.findViewById(R.id.tv_description_user_account);
        userDescription.setMovementMethod(new ScrollingMovementMethod());
        followersCountUser = (TextView) rootView.findViewById(R.id.tv_followers_user_account);
        favoritesCountUser = (TextView) rootView.findViewById(R.id.tv_favorites_user_account);
        statusesCountUser = (TextView) rootView.findViewById(R.id.tv_statuses_user_account);
        userTweetsListView = (ListView) rootView.findViewById(R.id.lv_user_tweets);
        //Get the bundle sent by the main activity, to tell us which user to display
        Bundle bundle = getArguments();

        if (bundle != null) {
            //Use the name obtained from the bundle to get the requested user and display their details.
            TwitterUser requestedUser = SingletonTweets.getInstance().getSpecificTwitterUser(bundle.getString(TwitterMainActivity.REQUESTED_USER));
            //Tell the singleton to prepare a list of tweets filtered using the obtained name.
            SingletonTweets.getInstance().setFilteredTweetList(requestedUser.getName());

            //Make a new adapter, as this one uses a different list than the main menu adapter.
            TweetListAdapter tweetListAdapter = new TweetListAdapter(rootView.getContext(), R.layout.tweet_list_item, SingletonTweets.getInstance().getFilteredTweetList());
            //Let the adapter know what user you want to display the tweets of.
            tweetListAdapter.setDataUserPageList(requestedUser.getName());
            userTweetsListView.setAdapter(tweetListAdapter);

            nameUser.setText(requestedUser.getName());
            userDescription.setText(requestedUser.getDescription());
            followersCountUser.setText(requestedUser.getFollowerCount() + " Follows");
            favoritesCountUser.setText(requestedUser.getFavoriteCount() + " Faves");
            statusesCountUser.setText(requestedUser.getStatusCount() + " Tweets");
        }
        else {
            //If the bundle has no data, something has gone wrong, the user page needs to know what
            // user to display, after all!
            throw new RuntimeException("User not found or information not sent right!");
        }

        return rootView;
    }
}
