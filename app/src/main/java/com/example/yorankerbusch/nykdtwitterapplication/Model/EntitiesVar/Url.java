package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class Url {
    private String url,displayUrl,expandedUrl;
    private Integer[] indices=new Integer[2];

    public Url(String url,String displayUrl,String expandedUrl, int firstInt, int secondInt){
        this.url=url;
        this.displayUrl=displayUrl;
        this.expandedUrl=expandedUrl;
        indices[1]=firstInt;
        indices[2]=secondInt;
    }

    public String getUrl() {
        return url;
    }
}
