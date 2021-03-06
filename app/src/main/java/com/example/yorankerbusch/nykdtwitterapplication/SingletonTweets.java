package com.example.yorankerbusch.nykdtwitterapplication;

import android.content.Context;

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
import java.util.Scanner;

/**
 * Singleton/model to keep all the data of the app stored during runtime, regardless of changes in activity.
 */
public class SingletonTweets {
    private static final SingletonTweets ourInstance = new SingletonTweets();
    private List<Tweet> tweetList;
    private List<TwitterUser> twitterUserList;
    private List<Tweet> filteredTweetList;


    private SingletonTweets() {
        tweetList = new ArrayList<>();
        twitterUserList = new ArrayList<>();
        filteredTweetList = new ArrayList<>();
    }

    public static SingletonTweets getInstance() {
        return ourInstance;
    }

    /**
     * Method to read the JSON file in the assets folder to get all the tweets, users and the rest.
     *
     * @param context is the context of the main activity, to retrieve the JSON file's contents.
     */
    public void loadJSONFromAsset(Context context) {
        try {
            InputStream is = context.getAssets().open("output.json");
            Scanner scanner = new Scanner(is);
            StringBuilder builder = new StringBuilder();

            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }

            JSONObject obj = new JSONObject(builder.toString());
            JSONArray tweetJsonArray = obj.getJSONArray("statuses");

            for (int countTweet = 0; countTweet < tweetJsonArray.length(); countTweet++) {
                //Get Tweet Object
                JSONObject tweetJson = tweetJsonArray.getJSONObject(countTweet);
                String status = tweetJson.getString("text");
                String date = tweetJson.getString("created_at");
                int retweet = tweetJson.getInt("retweet_count");
                int favorite = tweetJson.getInt("favorite_count");

                //Get User JSON object
                JSONObject userJson = tweetJson.getJSONObject("user");
                String userName = userJson.getString("name");
                int id = userJson.getInt("id");
                String description = userJson.getString("description");
                String urlUser = userJson.getString("url");
                int followersCount = userJson.getInt("followers_count");
                int favoritesCount = userJson.getInt("favourites_count");
                int statusCount = userJson.getInt("statuses_count");
                TwitterUser user = new TwitterUser(id, userName, description, urlUser, followersCount, favoritesCount, statusCount);

                //Get entities JSON object
                JSONObject entitiesJson = tweetJson.getJSONObject("entities");

                //Get entities components
                //Hashtag
                ArrayList<HashTag> hashTags = new ArrayList<>();
                JSONArray hashTagJsonArray = entitiesJson.getJSONArray("hashtags");

                //If no hashtag
                if (hashTagJsonArray.length() == 0) {
                    hashTags.add(new HashTag());
                } else {
                    for (int countHashTag = 0; countHashTag < hashTagJsonArray.length(); countHashTag++) {
                        JSONObject hashTagJson = hashTagJsonArray.getJSONObject(countHashTag);
                        JSONArray indicesJsonArray = hashTagJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];

                        readIndices(indicesJsonArray, indices);

                        HashTag hashTag = new HashTag(hashTagJson.getString("text"), indices);
                        hashTags.add(hashTag);
                    }
                }

                //Symbol
                ArrayList<Symbol> symbols = new ArrayList<>();
                JSONArray symbolJsonArray = entitiesJson.getJSONArray("symbols");

                if (symbolJsonArray.length() == 0) {
                    symbols.add(new Symbol());
                } else {
                    for (int countSymbol = 0; countSymbol < hashTagJsonArray.length(); countSymbol++) {
                        JSONObject symbolJson = symbolJsonArray.getJSONObject(countSymbol);
                        JSONArray indicesJsonArray = symbolJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];

                        readIndices(indicesJsonArray, indices);

                        Symbol symbol = new Symbol(symbolJson.getString("text"), indices);
                        symbols.add(symbol);
                    }
                }

                //UserMention
                ArrayList<UserMention> userMentions = new ArrayList<>();
                JSONArray userMentionJsonArray = entitiesJson.getJSONArray("user_mentions");

                if (userMentionJsonArray.length() == 0) {
                    userMentions.add(new UserMention());
                } else {
                    for (int countUserMention = 0; countUserMention < userMentionJsonArray.length(); countUserMention++) {
                        JSONObject userMentionJson = userMentionJsonArray.getJSONObject(countUserMention);
                        JSONArray indicesJsonArray = userMentionJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];

                        readIndices(indicesJsonArray, indices);

                        UserMention userMention = new UserMention(userMentionJson.getString("screen_name"),
                                userMentionJson.getString("name"), userMentionJson.getInt("id"), indices);

                        userMentions.add(userMention);
                    }
                }

                //URL
                ArrayList<Url> urls = new ArrayList<>();
                JSONArray urlJsonArray = entitiesJson.getJSONArray("urls");

                if (urlJsonArray.length() == 0) {
                    urls.add(new Url());
                } else {
                    for (int countURL = 0; countURL < urlJsonArray.length(); countURL++) {
                        JSONObject urlJson = urlJsonArray.getJSONObject(countURL);
                        JSONArray indicesJsonArray = urlJson.getJSONArray("indices");
                        Integer[] indices = new Integer[2];

                        readIndices(indicesJsonArray, indices);

                        Url url = new Url(urlJson.getString("url"), urlJson.getString("display_url"),
                                urlJson.getString("expanded_url"), indices);

                        urls.add(url);
                    }
                }

                Entities entities;
                //A JSON's Entities does not always has Media key value. First check if the key exists...
                if (entitiesJson.has("media")) {
                    ArrayList<Media> mediaList = new ArrayList<>();
                    JSONArray mediaJsonArray = entitiesJson.getJSONArray("media");

                    if (mediaJsonArray.length() == 0) {
                        mediaList.add(new Media());
                    } else {
                        for (int countMedia = 0; countMedia < mediaJsonArray.length(); countMedia++) {
                            JSONObject mediaJson = mediaJsonArray.getJSONObject(countMedia);
                            JSONArray indicesJsonArray = mediaJson.getJSONArray("indices");
                            Integer[] indices = new Integer[2];

                            readIndices(indicesJsonArray, indices);

                            Media media = new Media(mediaJson.getInt("id"), indices, mediaJson.getString("media_url"),
                                    mediaJson.getString("media_url_https"), mediaJson.getString("url"), mediaJson.getString("display_url"),
                                    mediaJson.getString("expanded_url"), mediaJson.getString("type"));

                            mediaList.add(media);
                        }
                    }
                    //Create Entities Object
                    entities = new Entities(hashTags, symbols, urls, userMentions, mediaList);
                } else {
                    //Create Entities Object (without Media)
                    entities = new Entities(hashTags, symbols, urls, userMentions);
                }

                //Get Metadata JSON object
                JSONObject metadataJson = tweetJson.getJSONObject("metadata");
                //Create Metadata Object
                Metadata metadata = new Metadata(metadataJson.getString("iso_language_code"), metadataJson.getString("result_type"));

                //Update the lists
                twitterUserList.add(user);
                tweetList.add(new Tweet(status, date, retweet, favorite, user, metadata, entities));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to read the object's indices
     *
     * @param indicesJsonArray A JSON array consists of the indices values
     * @param indices          An indices array with two integers representing two positions
     */
    private void readIndices(JSONArray indicesJsonArray, Integer[] indices) {
        for (int countIndices = 0; countIndices < indicesJsonArray.length(); countIndices++) {
            indices[countIndices] = indicesJsonArray.optInt(countIndices);
        }
    }

    /**
     * Method to get the full tweet arrayList.
     *
     * @return the arrayList with all the tweets
     */
    public List<Tweet> getTweetList() {
        return tweetList;
    }

    /**
     * Method to make a list of tweets filtered on a given user's name.
     *
     * @param userName is the name of the user the filtered list was requested for.
     */
    public void setFilteredTweetList(String userName) {
        //To be sure, clear the last list, since we only want the given user's tweets.
        filteredTweetList.clear();

        TwitterUser tempUser = getSpecificTwitterUser(userName);

        for (Tweet tweet : tweetList) {
            if (tweet.getUserName().equals(tempUser.getName())) {
                filteredTweetList.add(tweet);
            }
        }
    }

    /**
     * Method to get the filtered tweet list.
     *
     * @return an arrayList of filtered tweets (filtered on a prior given username), either null or an arrayList.
     */
    public List<Tweet> getFilteredTweetList() {
        return filteredTweetList;
    }

    public TwitterUser getSpecificTwitterUser(String userName) {
        for (TwitterUser user : twitterUserList) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }

        throw new RuntimeException("User not found - User with username does not exist!");
    }

    /**
     * Method to retrieve a full list of all users in the app.
     *
     * @return
     */
    public List<TwitterUser> getTwitterUserList() {
        return twitterUserList;
    }
}
