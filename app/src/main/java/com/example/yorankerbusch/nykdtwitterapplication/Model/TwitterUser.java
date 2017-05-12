package com.example.yorankerbusch.nykdtwitterapplication.Model;

/**
 * A class represents a Twitter user. A user has an id, a name, description ...
 */

public class TwitterUser {
    private int userId,followerCount,favoriteCount,statusCount ;
    private String name,description,url;
    private String password,followingCount;//exist??

    public TwitterUser(String name){
        this.name=name;
    }

    public TwitterUser(int userId,String name,String description,String url,
                       int followerCount,int favoriteCount,int statusCount){
        this.userId=userId;
        this.followerCount=followerCount;
        this.favoriteCount=favoriteCount;
        this.statusCount=statusCount;
        this.name=name;
        this.description=description;
        this.url=url;
    }

    public int getId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getDescription() {
        return description;
    }

    public boolean checkUserName(String anotherName){
        return anotherName.equals(this.name);
    }
    public boolean checkPassword(String anotherPass){
        return anotherPass.equals(this.password);
    }
}
