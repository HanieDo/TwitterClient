package com.example.yorankerbusch.nykdtwitterapplication.Model;

import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.HashTag;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Media;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Symbol;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Url;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.UserMention;

import java.util.ArrayList;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class Entities {
    private ArrayList<HashTag> hashTags;
    private ArrayList<Symbol> symbols;
    private ArrayList<Url> urls;
    private ArrayList<UserMention> userMentions;
    private ArrayList<Media> media;

    public Entities(){
        hashTags=new ArrayList<>();
        symbols=new ArrayList<>();
        urls=new ArrayList<>();
        userMentions=new ArrayList<>();
        media=new ArrayList<>();
    }

    public ArrayList<HashTag> getHashTags() {
        return hashTags;
    }
    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }
    public ArrayList<Url> getUrls() {
        return urls;
    }
    public ArrayList<UserMention> getUserMentions() {
        return userMentions;
    }
    public ArrayList<Media> getMedia() {
        return media;
    }
}
