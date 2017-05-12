package com.example.yorankerbusch.nykdtwitterapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Activity that shows a list of tweets (in portrait orientation) or a list of tweets side-by-side
 * with the profile of the tweep-twitter user (in landscape orientation).
 */
public class MainListActivity extends AppCompatActivity {
    private ListView tweetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        SingletonTweets.getInstance().loadJSONFromAsset(getApplicationContext());

        tweetListView = (ListView) findViewById(R.id.lv_tweets);

        TweetListAdapter tweetListAdapter = new TweetListAdapter(this, R.layout.tweet_list_item, SingletonTweets.getInstance().getTweetList());
        tweetListView.setAdapter(tweetListAdapter);

        //Other code....
    }

    //Methods
}
