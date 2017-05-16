package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

public class Media {
    private int id;
    private String url, displayUrl, expandedUrl, mediaURL, mediaURLHTTPS, type;
    private Integer[] indices;

    public Media() {
        //...
    }

    public Media(int id, Integer[] indices, String mediaURL, String mediaURLHTTPS, String url,
                 String displayUrl, String expandedUrl, String type) {
        this.id = id;
        this.indices = indices;
        this.mediaURL = mediaURL;
        this.mediaURLHTTPS = mediaURLHTTPS;
        this.url = url;
        this.displayUrl = displayUrl;
        this.expandedUrl = expandedUrl;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
