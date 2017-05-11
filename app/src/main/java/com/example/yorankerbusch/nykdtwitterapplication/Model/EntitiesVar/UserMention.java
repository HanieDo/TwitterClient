package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class UserMention {
    private int id;
    private String screenName,name;
    private Integer[] indices;

    public UserMention(){
    }

    public UserMention(String screenName,String name,int id,Integer[] indices){
        this.id=id;
        this.indices=indices;
        this.screenName=screenName;
        this.name=name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
