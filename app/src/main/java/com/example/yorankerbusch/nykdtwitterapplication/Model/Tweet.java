package com.example.yorankerbusch.nykdtwitterapplication.Model;

/**
 * Created by Yoran Kerbusch on 9-5-2017.
 */

public class Tweet {
    private String date, textContent;
    private int retweetCount, favouriteCount, inReplyToUserId, statusId;
    private boolean truncated;

    private TwitterUser user;
    private Metadata metadata;
    private Entities entities;

    //Constructor just for displayed information
    public Tweet(String textContent,String date,int retweetCount,int favouriteCount,TwitterUser user,Metadata metadata,Entities entities){
        this.textContent=textContent;
        this.date=date;
        this.retweetCount=retweetCount;
        this.favouriteCount=favouriteCount;
        this.user=user;
        this.metadata=metadata;
        this.entities=entities;
    }

    //Constructor for a fully Tweet
    public Tweet (String date, int statusId, String textContent, boolean truncated, Entities entities,
                  Metadata metadata, int inReplyToUserId, TwitterUser user, int retweetCount, int favouriteCount) {
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

    public Entities getEntities() {
        return entities;
    }

    public String getUserName() {
        return user.getName();
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
