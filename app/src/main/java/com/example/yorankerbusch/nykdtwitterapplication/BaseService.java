package com.example.yorankerbusch.nykdtwitterapplication;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * Created by LENOVO on 5/18/2017.
 */

public class BaseService {
    public OAuth10aService createObject(){
        //Create the OAuthService object
        return new ServiceBuilder()
                .apiKey(StaticKey.API_key)
                .apiSecret(StaticKey.API_secret)
                //pass a callbackUrl
                .callback(StaticKey.API_callBack)
                .build(TwitterApi.instance());
    }
}
