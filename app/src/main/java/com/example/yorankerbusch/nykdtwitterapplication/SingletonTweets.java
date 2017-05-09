package com.example.yorankerbusch.nykdtwitterapplication;

import com.example.yorankerbusch.nykdtwitterapplication.Model.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton class represents....
 */

public class SingletonTweets {
    private static final SingletonTweets ourInstance = new SingletonTweets();
    private List<Tweet> tweetList;

    private SingletonTweets() {
        tweetList = new ArrayList<>();
        loadDummyData();
    }

    private void loadDummyData() {
        tweetList.add(new Tweet("Yoran", "9 May 2017", "Fucking normies, reeeeeee", 21, 69));
        tweetList.add(new Tweet("Nhi", "8 May 2017", "Hello people, it's ya boii AnomalyXD", 42, 1337));
        tweetList.add(new Tweet("Martin", "29 June 2016", "Wat is Netherlands", 1, 12));
        tweetList.add(new Tweet("Wouter", "9 May 2017", "Kek, such hate", 0, 9));
    }

    public static SingletonTweets getInstance() {
        return ourInstance;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }
}
