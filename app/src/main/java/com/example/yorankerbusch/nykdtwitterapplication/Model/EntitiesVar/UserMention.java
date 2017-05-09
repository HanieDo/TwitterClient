package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class UserMention {
    private int id;
    private String screenName,name;
    private Integer[] indices=new Integer[2];

    public UserMention(int id,String screenName,String name,int firstInt, int secondInt){
        this.id=id;
        indices[1]=firstInt;
        indices[2]=secondInt;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
