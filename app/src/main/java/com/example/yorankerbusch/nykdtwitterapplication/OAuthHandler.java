package com.example.yorankerbusch.nykdtwitterapplication;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;

/**
 * Created by LENOVO on 5/18/2017.
 */

public class OAuthHandler {
    private OAuth1AccessToken auth1AccessToken;
    private OAuth1RequestToken auth1RequestToken;
    private OAuthRequest authRequest;
    private Response response;

    public OAuthHandler(){
    }

    public OAuthRequest getAuthRequest() {
        return authRequest;
    }
    public Response getResponse() {
        return response;
    }
    public OAuth1AccessToken getAuth1AccessToken() {
        return auth1AccessToken;
    }
    public OAuth1RequestToken getAuth1RequestToken() {
        return auth1RequestToken;
    }

    public void setAuthRequest(OAuthRequest authRequest) {
        this.authRequest = authRequest;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
    public void setAuth1AccessToken(OAuth1AccessToken auth1AccessToken) {
        this.auth1AccessToken = auth1AccessToken;
    }
    public void setAuth1RequestToken(OAuth1RequestToken auth1RequestToken) {
        this.auth1RequestToken = auth1RequestToken;
    }
}
