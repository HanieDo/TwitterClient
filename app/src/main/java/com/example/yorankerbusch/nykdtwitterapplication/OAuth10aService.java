package com.example.yorankerbusch.nykdtwitterapplication;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by LENOVO on 5/18/2017.
 */

public class OAuth10aService extends com.github.scribejava.core.oauth.OAuth10aService {
    private static OAuth10aService singleton=null;
    public static OAuth10aService getInstance(){
        if (singleton==null){
            singleton=new OAuth10aService();
        }
        return singleton;
    }
    /**
     * Default constructor
     *
     * @param api    OAuth1.0a api information
     * @param config OAuth 1.0a configuration param object
     */
    private OAuth10aService(DefaultApi10a api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    public OAuth1RequestToken getRequestToken() {
        return super.getRequestToken();
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return super.getAuthorizationUrl(requestToken);
    }
}
