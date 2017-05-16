package com.example.yorankerbusch.nykdtwitterapplication.Model;

/**
 * A helper class represents "SearchMetadata" tool for a user to...
 */

public class SearchMetadata {
    private double completedIn;
    private int count, maxId, sinceId;
    private String nextResults, query, refreshUrl;

    public SearchMetadata(double completedIn, int count, int maxId, int sinceId,
                          String nextResults, String query, String refreshUrl) {
        this.completedIn = completedIn;
        this.count = count;
        this.maxId = maxId;
        this.sinceId = sinceId;
        this.nextResults = nextResults;
        this.query = query;
        this.refreshUrl = refreshUrl;
    }

    public String getQuery() {
        return query;
    }
}
