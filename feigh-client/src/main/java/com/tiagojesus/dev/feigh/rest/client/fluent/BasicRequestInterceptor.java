package com.tiagojesus.dev.feigh.rest.client.fluent;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BasicRequestInterceptor implements RequestInterceptor {
    private String headerSystem;
    private String system;

    public BasicRequestInterceptor(String headerSystem,  UserRestClientFluent user) {
        this.headerSystem = headerSystem;
        this.system = user.system;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(headerSystem, system);
    }
}
