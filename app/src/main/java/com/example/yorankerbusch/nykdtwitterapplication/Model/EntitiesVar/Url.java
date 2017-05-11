package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class Url {
    private String url,displayUrl,expandedUrl;
    private Integer[] indices;

    public Url(){
    }

    public Url(String url,String displayUrl,String expandedUrl, Integer[] indices){
        this.url=url;
        this.displayUrl=displayUrl;
        this.expandedUrl=expandedUrl;
        this.indices=indices;
    }

    public String getUrl() {
        return url;
    }
}
