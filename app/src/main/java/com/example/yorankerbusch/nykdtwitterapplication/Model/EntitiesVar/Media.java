package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class Media extends Url {
    private int id;
    private String mediaURL, mediaURLHTTPS, type;

    public Media(String url,String displayUrl,String expandedUrl, int firstInt, int secondInt,
                 int id, String mediaURL, String mediaURLHTTPS, String type){
        super(url, displayUrl, expandedUrl, firstInt, secondInt);
        this.id=id;
        this.mediaURL=mediaURL;
        this.mediaURLHTTPS=mediaURLHTTPS;
        this.type=type;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
}
