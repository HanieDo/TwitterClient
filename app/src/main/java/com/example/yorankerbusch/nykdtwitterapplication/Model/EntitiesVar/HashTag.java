package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class HashTag {
    private String text;
    private Integer[] indices=new Integer[2];

    public HashTag(String text, int firstInt, int secondInt){
        this.text=text;
        indices[1]=firstInt;
        indices[2]=secondInt;
    }

    public String getText() {
        return text;
    }
    public int getHashTagLength(){
        return indices[2]-indices[1];
    }
}
