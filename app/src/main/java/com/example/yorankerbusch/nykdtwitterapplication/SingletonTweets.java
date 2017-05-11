package com.example.yorankerbusch.nykdtwitterapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.example.yorankerbusch.nykdtwitterapplication.Model.Entities;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.HashTag;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Media;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Symbol;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Url;
import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.UserMention;
import com.example.yorankerbusch.nykdtwitterapplication.Model.Metadata;
import com.example.yorankerbusch.nykdtwitterapplication.Model.Tweet;
import com.example.yorankerbusch.nykdtwitterapplication.Model.TwitterUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton class represents....
 */

public class SingletonTweets {
    private static final SingletonTweets ourInstance = new SingletonTweets();
    private List<Tweet> tweetList;

    private SingletonTweets(Context context) {
        tweetList = new ArrayList<>();
        loadDummyData();

        //Pass a context to getAssets()
        AssetManager assetManager=context.getAssets();
        try {
            InputStream inputStream=assetManager.open("output.json");
            loadJSONFromAsset(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDummyData() {
        tweetList.add(new Tweet("Yoran", "9 May 2017", "Fucking normies, reeeeeee", 21, 69));
        tweetList.add(new Tweet("Nhi", "8 May 2017", "Hello people, it's ya boii AnomalyXD", 42, 1337));
        tweetList.add(new Tweet("Martin", "29 June 2016", "Wat is Netherlands", 1, 12));
        tweetList.add(new Tweet("Wouter", "9 May 2017", "Kek, such hate", 0, 9));
    }


    public void loadJSONFromAsset(InputStream is) {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray tweetJsonArray = obj.getJSONArray("statuses");
            for (int countTweet=0;countTweet<tweetJsonArray.length();countTweet++){
                //Get tweet Object
                JSONObject tweetJson=tweetJsonArray.getJSONObject(countTweet);
                //Get entities JSON object
                JSONObject entitiesJson=tweetJson.getJSONObject("entities");

                //Get entities components
                //Hashtag
                ArrayList<HashTag> hashTags=new ArrayList<>();
                JSONArray hashTagJsonArray=entitiesJson.getJSONArray("hashtags");
                //If no hashtag
                if (hashTagJsonArray.length()==0){
                    hashTags.add(new HashTag());
                } else {
                    for (int countHashTag = 0; countHashTag < hashTagJsonArray.length(); countHashTag++) {
                        JSONObject hashTagJson = hashTagJsonArray.getJSONObject(countHashTag);
                        JSONArray indicesJsonArray = hashTagJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];
                        readIndices(indicesJsonArray,indices);
                        HashTag hashTag = new HashTag(hashTagJson.getString("text"), indices);
                        hashTags.add(hashTag);
                    }
                }
                //Symbol
                ArrayList<Symbol> symbols=new ArrayList<>();
                JSONArray symbolJsonArray=entitiesJson.getJSONArray("symbols");
                if (symbolJsonArray.length()==0){
                    symbols.add(new Symbol());
                } else {
                    for (int countSymbol = 0; countSymbol < hashTagJsonArray.length(); countSymbol++) {
                        JSONObject symbolJson = symbolJsonArray.getJSONObject(countSymbol);
                        JSONArray indicesJsonArray = symbolJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];
                        readIndices(indicesJsonArray,indices);
                        Symbol symbol = new Symbol(symbolJson.getString("text"), indices);
                        symbols.add(symbol);
                    }
                }
                //UserMention
                ArrayList<UserMention> userMentions=new ArrayList<>();
                JSONArray userMentionJsonArray=entitiesJson.getJSONArray("user_mentions");
                if (userMentionJsonArray.length()==0){
                    userMentions.add(new UserMention());
                } else {
                    for (int countUserMention = 0; countUserMention < userMentionJsonArray.length(); countUserMention++) {
                        JSONObject userMentionJson = userMentionJsonArray.getJSONObject(countUserMention);
                        JSONArray indicesJsonArray = userMentionJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];
                        readIndices(indicesJsonArray,indices);
                        UserMention userMention= new UserMention(userMentionJson.getString("screen_name"),
                                userMentionJson.getString("name"),userMentionJson.getInt("id"),indices);
                        userMentions.add(userMention);
                    }
                }
                //URL
                ArrayList<Url> urls=new ArrayList<>();
                JSONArray urlJsonArray=entitiesJson.getJSONArray("urls");
                if (urlJsonArray.length()==0){
                    urls.add(new Url());
                }else {
                    for (int countURL = 0; countURL < urlJsonArray.length(); countURL++) {
                        JSONObject urlJson = urlJsonArray.getJSONObject(countURL);
                        JSONArray indicesJsonArray = urlJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];
                        readIndices(indicesJsonArray,indices);
                        Url url=new Url(urlJson.getString("url"),urlJson.getString("display_url"),
                                urlJson.getString("expanded_url"),indices);
                        urls.add(url);
                    }
                }
                //Media ---> Not always have (check 3nd status)
                ArrayList<Media> mediaList=new ArrayList<>();
                JSONArray mediaJsonArray=entitiesJson.getJSONArray("media");
                if (mediaJsonArray.length()==0){
                    mediaList.add(new Media());
                } else {
                    for (int countMedia = 0; countMedia < mediaJsonArray.length(); countMedia++) {
                        JSONObject mediaJson = mediaJsonArray.getJSONObject(countMedia);
                        JSONArray indicesJsonArray = mediaJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];
                        readIndices(indicesJsonArray,indices);
                        Media media=new Media(mediaJson.getInt("id"),indices,mediaJson.getString("media_url"),
                                mediaJson.getString("media_url_https"),mediaJson.getString("url"),mediaJson.getString("display_url"),
                                mediaJson.getString("expanded_url"),mediaJson.getString("type"));
                        mediaList.add(media);
                    }
                }

                //Create Entities Object
                Entities entities=new Entities(hashTags,symbols,urls,userMentions);

                //Get Metadata JSON object
                JSONObject metadataJson=tweetJson.getJSONObject("metadata");
                //Create Metadata Object
                Metadata metadata=new Metadata(metadataJson.getString("iso_language_code"),metadataJson.getString("result_type"));

                //Get User JSON object
                JSONObject userJson=tweetJson.getJSONObject("user");
                TwitterUser user=new TwitterUser(userJson.getInt("id"),userJson.getString("name"),
                        userJson.getString("description"),userJson.getString("url"),userJson.getInt("followers_count"),
                        userJson.getInt("favourites_count"),userJson.getInt("statuses_count"));

                Tweet tweet=new Tweet(tweetJson.getString("created_at"),tweetJson.getInt("id"),
                        tweetJson.getString("text"),tweetJson.getBoolean("truncated"),entities,metadata,
                        tweetJson.getInt("in_reply_to_user_id"),user,tweetJson.getInt("retweet_count"),tweetJson.getInt("favorite_count"));
                tweetList.add(tweet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to read the object's indices
     * @param indicesJsonArray A JSON array consists of the indices values
     * @param indices An indices array with two integers representing two positions
     */
    private void readIndices(JSONArray indicesJsonArray,Integer[] indices){
        for (int countIndices = 0; countIndices < indicesJsonArray.length(); countIndices++) {
            JSONObject indicesJson = null;
            try {
                indicesJson = indicesJsonArray.getJSONObject(countIndices);
                indices[countIndices] = indicesJson.getInt("1"); //????
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static SingletonTweets getInstance() {
        return ourInstance;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }
}
