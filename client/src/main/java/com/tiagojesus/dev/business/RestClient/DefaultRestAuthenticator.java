package com.tiagojesus.dev.business.RestClient;

import okhttp3.*;

import java.io.IOException;

public class DefaultRestAuthenticator implements Authenticator {

    private final RestUser user;

    public DefaultRestAuthenticator(RestUser user) {
        this.user = user;
    }

    @Override public Request authenticate(Route route, Response response) throws IOException {
        if (response.request().header("Authorization") != null) {
            return null; // Give up, we've already attempted to authenticate.
        }

        BusinessLogger.INFO(BusinessRestClient.class,
                "Authenticating for response: " + response,
                "Challenges: " + response.challenges());

        String credential = Credentials.basic(user.getUser(), user.getPassword());

        return response.request().newBuilder()
                .header("Authorization", credential)
                .build();
    }
}
