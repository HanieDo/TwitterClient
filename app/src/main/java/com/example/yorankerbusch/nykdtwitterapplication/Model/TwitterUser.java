package com.example.yorankerbusch.nykdtwitterapplication.Model;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class TwitterUser {
    private int userId,followerCount,followingCount,favoriteCount,statusCount ;
    private String name,password,description,url;

    public TwitterUser(int userId,int followerCount,int followingCount,int favoriteCount,int statusCount,
                       String name,String password,String description,String url){
        this.userId=userId;
        this.followerCount=followerCount;
        this.followingCount=followingCount;
        this.favoriteCount=favoriteCount;
        this.statusCount=statusCount;
        this.name=name;
        this.password=password;
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
