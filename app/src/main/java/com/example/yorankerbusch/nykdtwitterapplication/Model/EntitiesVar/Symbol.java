package com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar;

public class Symbol {
    private String text;
    private Integer[] indices;

    public Symbol() {
        //...
    }

    public Symbol(String text, Integer[] indices) {
        this.text = text;
        this.indices = indices;
    }

    public String getText() {
        return text;
    }
}
