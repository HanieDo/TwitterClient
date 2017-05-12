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
import android.widget.TextView;
import android.widget.Toast;

import com.example.yorankerbusch.nykdtwitterapplication.Model.Tweet;

import java.util.List;

/**
 * Adapter for a list of tweets, inflating views for use within a ListView. The views consist of ....?
 */

public class TweetListAdapter extends ArrayAdapter<Tweet> {
    public TweetListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tweet> tweets) {
        super(context, resource, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        Tweet tweet = SingletonTweets.getInstance().getTweetList().get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_list_item, parent, false);

            holder.userNameTV = (TextView) convertView.findViewById(R.id.userName_tv);
            holder.dateTV = (TextView) convertView.findViewById(R.id.date_tv);
            holder.contentTV = (TextView) convertView.findViewById(R.id.content_tv);
            holder.retweetButton = (Button) convertView.findViewById(R.id.retweet_bttn);
            holder.favouriteButton = (Button) convertView.findViewById(R.id.favourite_bttn);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.userNameTV.setText(tweet.getUserName());
        holder.dateTV.setText(tweet.getDate());
        holder.contentTV.setText(tweet.getTextContent());
        holder.retweetButton.setText("" + tweet.getRetweetCount());
        holder.favouriteButton.setText("" + tweet.getFavouriteCount());

        holder.userNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "It's working!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView userNameTV, dateTV, contentTV;
        Button retweetButton, favouriteButton;
    }
}
