package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class HashTag {
    private String text;
    private Integer[] indices;

    public HashTag(){
    }

    public HashTag(String text, Integer[] indices){
        this.text=text;
        this.indices=indices;
    }

    public String getText() {
        return text;
    }
    public int getHashTagLength(){
        return indices[1]-indices[0]-1;//minus the "#" sign.
    }
}
