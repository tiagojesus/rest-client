package com.tiagojesus.dev.feigh.rest.client.fluent;

import com.tiagojesus.dev.feigh.rest.example.MensagemServiceFormApi;
import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.slf4j.Slf4jLogger;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.charset.Charset;

import static com.tiagojesus.dev.feigh.rest.client.fluent.UserRestClientFluent.forSystem;

public class BusinessRestClientFluent {

    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final Charset CHARSET_DEFAULT = CHARSET_UTF_8;
    public static final String SYTEM_NAME_ON_HTTP_HEADER = "X-SYSTEM";

    private final String baseUrl;
    private final UserRestClientFluent user;
    private final boolean useBasicAuthentication;
    private Feign.Builder builderApi;

    public static Builder clientRestWithBaseUrl(String baseUrl) {
        return new Builder(baseUrl);
    }

    private BusinessRestClientFluent(Builder builder){
        baseUrl = builder.baseUrl;
        user = builder.user;
        useBasicAuthentication = builder.useBasicAuthentication;

        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        builderApi = Feign.builder()
                .client(new ApacheHttpClient(httpclient))
                .logger(new Slf4jLogger())
                .requestInterceptor(new BasicRequestInterceptor(SYTEM_NAME_ON_HTTP_HEADER, user))
                .logLevel(Logger.Level.FULL)
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        configBasicAuth();
    }

    private void configBasicAuth() {
        if(useBasicAuthentication){
            BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor(
                    user.username, user.password, CHARSET_DEFAULT);
            builderApi.requestInterceptor(basicAuthRequestInterceptor);
        }
    }

    public <T> T target(Class<T> tClass){
        return builderApi.target(tClass, baseUrl);
    }

    public static class Builder {
        private final String baseUrl;
        private UserRestClientFluent user;
        private boolean useBasicAuthentication = false;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder user(UserRestClientFluent user) {
            this.user = user;
            return  this;
        }

        public Builder useBasicAuthentication() {
            this.useBasicAuthentication = true;
            return this;
        }

        public BusinessRestClientFluent build() {
            return new BusinessRestClientFluent(this);
        }
    }



}
