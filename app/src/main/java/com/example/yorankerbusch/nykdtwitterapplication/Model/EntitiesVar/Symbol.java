package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

/**
 * Created by LENOVO on 5/9/2017.
 */

public class Symbol {
    private String text;

    private Integer[] indices;

    public Symbol(){
    }

    public Symbol(String text, Integer[] indices){
        this.text=text;
        this.indices=indices;
    }

    public String getText() {
        return text;
    }
}
