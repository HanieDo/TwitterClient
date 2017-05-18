package com.example.yorankerbusch.nykdtwitterapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yorankerbusch.nykdtwitterapplication.Model.EntitiesVar.Media;
import com.example.yorankerbusch.nykdtwitterapplication.Model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter class for the lists of tweets within the app.
 */
public class TweetListAdapter extends ArrayAdapter<Tweet> {
    private String specifiedUserName = null;

    public TweetListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tweet> tweets) {
        super(context, resource, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        Tweet tweet = null;

        if (specifiedUserName == null) {
            //If the adapter IS NOT made for an user page, then just get the full tweet list.
            tweet = SingletonTweets.getInstance().getTweetList().get(position);
        } else if (specifiedUserName != null) {
            //If the adapter IS made for an user page, then get the filtered tweet list.
            tweet = SingletonTweets.getInstance().getFilteredTweetList().get(position);
        }

        if (convertView == null) {
            //Instantiate the view of the item, inflate it and set the ViewHolder's variables.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_list_item, parent, false);

            holder.userNameTV = (TextView) convertView.findViewById(R.id.userName_tv);
            holder.dateTV = (TextView) convertView.findViewById(R.id.date_tv);
            holder.contentTV = (TextView) convertView.findViewById(R.id.content_tv);
            holder.tweetIV = (ImageView) convertView.findViewById(R.id.tweet_imageView);
            holder.retweetButton = (Button) convertView.findViewById(R.id.retweet_bttn);
            holder.favouriteButton = (Button) convertView.findViewById(R.id.favourite_bttn);

            convertView.setTag(holder);
        } else {
            //However, if the view is not null, it's been made. To save strain, just call it and reuse it!
            holder = (ViewHolder) convertView.getTag();
        }

        //Then set all the viewHolder's design items to the data gotten from the current tweet.
        //Used for checking Entities value
//        if (position==2){
//            holder.userNameTV.setText(tweet.getEntities().getHashTagCheck());
//        } else {
        holder.userNameTV.setText(tweet.getUserName());
//    }
        holder.dateTV.setText(tweet.getDate());
        holder.contentTV.setText(tweet.getTextContent());
        holder.retweetButton.setText("" + tweet.getRetweetCount());
        holder.favouriteButton.setText("" + tweet.getFavouriteCount());

        if (tweet.getEntities().getMedia() != null) {
            for (Media media : tweet.getEntities().getMedia()) {
                Picasso.with(convertView.getContext()).load(media.getMediaURLHTTPS()).into(holder.tweetIV);
            }
        }
        else {
            holder.tweetIV.setImageResource(0);
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /**
     * Method to set the specific user the adapter should call the list of tweets for the user's page.
     *
     * @param givenUserName is the supplied userName that has been called, which could be used for later security checks.
     */
    public void setDataUserPageList(String givenUserName) {
        specifiedUserName = givenUserName;
    }

    /**
     * ViewHolder class to store data of an item in the list, which can be reused to save on performance strain.
     */
    public class ViewHolder {
        TextView userNameTV, dateTV, contentTV;
        ImageView tweetIV;
        Button retweetButton, favouriteButton;
    }
}
