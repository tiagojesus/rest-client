package com.tiagojesus.dev.feigh.rest.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.Map;

public class ForwardedForInterceptor implements RequestInterceptor {
    private final FeignRestClient client;
    private String systemNameHeader = "X-SYSTEM";

    public ForwardedForInterceptor(FeignRestClient client) {
        this.client = client;
    }

    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();

        if(client.hasSystem()) template.header(systemNameHeader, client.system());
        if(client.isToUseCookieInfo()) template.header("set-cookie", client.cookieInfo().get());
    }
}