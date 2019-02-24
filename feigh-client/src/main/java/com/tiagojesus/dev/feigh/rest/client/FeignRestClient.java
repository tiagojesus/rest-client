package com.tiagojesus.dev.feigh.rest.client;

import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;

import java.util.Optional;

/** More information //https://github.com/OpenFeign/feign*/

public class FeignRestClient {
    private Feign.Builder builderApi;
    private ConfigRest config;
    private Optional<String> cookieInfo = Optional.empty();

    public FeignRestClient(ConfigRest config){
        this.config = config;

        builderApi = Feign.builder()
                .client(new OkHttpClient())
                .contract(new JAXRSContract())
                .logger(new Logger.JavaLogger().appendToFile("kfkfk"))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(new ForwardedForInterceptor(this))
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        configBasicAuth();

    }

    private void configBasicAuth() {
        if(config.hasBasicLogin){
            BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor(
                    config.username.get(), config.password.get(), ConfigRest.CHARSET_DEFAULT);
            builderApi.requestInterceptor(basicAuthRequestInterceptor);
        }
    }

    public boolean hasSystem(){
        return config.system.isPresent();
    }

    public String system() {
        return config.system.get();
    }

    public boolean isToUseCookieInfo(){
        return config.isToUseCookieInfo && cookieInfo.isPresent();
    }

    public Optional<String> cookieInfo(){
        return cookieInfo;
    }

    public <T> T target(Class<T> tClass){
        return builderApi.target(tClass, config.urlServiceBase);
    }

    void loginForm(String username, String password){
/*
            Response response = LoginServer.login( username, password);
            response.headers();
*/
    }
}
