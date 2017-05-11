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
    private ArrayList<HashTag> hashTags=new ArrayList<>();
    private ArrayList<Symbol> symbols=new ArrayList<>();
    private ArrayList<Url> urls = new ArrayList<>();
    private ArrayList<UserMention> userMentions = new ArrayList<>();
    private ArrayList<Media> media; //Exist?

    public Entities(ArrayList<HashTag> hashTags, ArrayList<Symbol> symbols,ArrayList<Url> urls,ArrayList<UserMention> userMentions){
        this.hashTags=hashTags;
        this.symbols=symbols;
        this.urls=urls;
        this.userMentions=userMentions;
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
