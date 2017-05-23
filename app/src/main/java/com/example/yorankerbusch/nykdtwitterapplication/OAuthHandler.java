package com.example.yorankerbusch.nykdtwitterapplication;

import android.os.AsyncTask;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;

/**
 * Created by LENOVO on 5/18/2017.
 */

public class OAuthHandler {
    private OAuth1AccessToken accessToken;
    private OAuth1RequestToken requestToken;
    private OAuthRequest authRequest;
    private Response response;
    private OAuth10aService service;
    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

    public OAuthHandler(BaseService baseService) {
        this.service = baseService.createObject();
    }

    public void signRequest() {
        try {
            //Get the access Token
            accessToken = service.getAccessToken(requestToken, "verifier you got from the user/callback");
            //Sign request
            authRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
            service.signRequest(accessToken, authRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        return response = authRequest.send();
    }

    public OAuth1RequestToken getRequestToken() {
        try {
            return requestToken = service.getRequestToken();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return service.getAuthorizationUrl(requestToken);
    }
}
