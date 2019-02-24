package com.tiagojesus.dev.business.RestClient;

import okhttp3.*;

import java.io.IOException;

public class AutorizationInterceptor implements Interceptor {
    private RestUser user;

    public AutorizationInterceptor(RestUser user) {
        this.user = user;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request newRequest = originalRequest.newBuilder()
                .removeHeader("Authorization")
                .removeHeader("User-Agent")
                .addHeader("Authorization", user.getToken())
                .addHeader("User-Agent", user.getSystem())
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(newRequest);
    }
}