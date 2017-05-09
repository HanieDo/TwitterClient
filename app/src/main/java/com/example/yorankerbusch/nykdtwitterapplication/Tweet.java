package com.example.yorankerbusch.nykdtwitterapplication;

/**
 * Created by Yoran Kerbusch on 9-5-2017.
 */

public class Tweet {
    private String userName, date, textContent;
    private int retweetCount, favouriteCount;

    public Tweet (String userName, String date, String textContent, int retweetCount, int favouriteCount) {
        this.userName = userName;
        this.date = date;
        this.textContent = textContent;
        this.retweetCount = retweetCount;
        this.favouriteCount = favouriteCount;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getTextContent() {
        return textContent;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }
}
