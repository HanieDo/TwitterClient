package com.example.yorankerbusch.nykdtwitterapplication.Model;

/**
 * Created by Yoran Kerbusch on 9-5-2017.
 */

public class Tweet {
    private String userName, date, textContent;
    private int retweetCount, favouriteCount, inReplyToUserId, statusId;
    private boolean truncated;

    private TwitterUser user;
    private Metadata metadata;
    private Entities entities;

    public Tweet(String userName, String date, String textContent, int retweetCount, int favouriteCount){
        this.userName = userName;
        this.date = date;
        this.textContent = textContent;
        this.retweetCount = retweetCount;
        this.favouriteCount = favouriteCount;
    }

    public Tweet (String date, int statusId, String textContent, boolean truncated, Entities entities,
                  Metadata metadata, int inReplyToUserId, TwitterUser user, int retweetCount, int favouriteCount) {
        this.userName = user.getName();
        this.date = date;
        this.statusId = statusId;
        this.textContent = textContent;
        this.truncated = truncated;
        this.entities = entities;
        this.metadata = metadata;
        this.inReplyToUserId = inReplyToUserId;
        this.user = user;
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
