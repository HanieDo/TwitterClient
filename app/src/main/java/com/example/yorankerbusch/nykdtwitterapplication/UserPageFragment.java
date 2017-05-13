package com.example.yorankerbusch.nykdtwitterapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yorankerbusch.nykdtwitterapplication.Model.TwitterUser;

public class UserPageFragment extends Fragment {
    private TextView nameUser, userDescription, followersCountUser, favoritesCountUser, statusesCountUser;
    private ListView userTweetsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_page, container, false);

        nameUser = (TextView) rootView.findViewById(R.id.tv_name_user_account);
        userDescription = (TextView) rootView.findViewById(R.id.tv_description_user_account);
        followersCountUser = (TextView) rootView.findViewById(R.id.tv_followers_user_account);
        favoritesCountUser = (TextView) rootView.findViewById(R.id.tv_favorites_user_account);
        statusesCountUser = (TextView) rootView.findViewById(R.id.tv_statuses_user_account);
        userTweetsListView = (ListView) rootView.findViewById(R.id.lv_user_tweets);
        Bundle bundle = getArguments();

        if (bundle != null) {
            TwitterUser requestedUser = SingletonTweets.getInstance().getSpecificTwitterUser(bundle.getString(TwitterMainActivity.REQUESTED_USER));

            nameUser.setText(requestedUser.getName());
            userDescription.setText(requestedUser.getDescription());
            followersCountUser.setText(requestedUser.getFollowerCount() + " Follows");
            favoritesCountUser.setText(requestedUser.getFavoriteCount() + " Faves");
            statusesCountUser.setText(requestedUser.getStatusCount() + " Tweets");

            //TODO: find a way to only show the tweets of the user that this account belongs to.
            TweetListAdapter tweetListAdapter = new TweetListAdapter(rootView.getContext(), R.layout.tweet_list_item, SingletonTweets.getInstance().getTweetList());
            userTweetsListView.setAdapter(tweetListAdapter);
        }
        else {
            throw new RuntimeException("User not found or information not sent right!");
        }

        return rootView;
    }
}
