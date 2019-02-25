package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import com.tiagojesus.dev.feigh.rest.client.ConfigRest;
import com.tiagojesus.dev.feigh.rest.client.FeignRestClient;
import feign.Response;

public class MainFeignApp {
    public static void main(String[] args) {
        ConfigRest config = ConfigRest.forBaseUrl("http://localhost:8080")
                .system("my-app-01")
                //.basicLogin("username", "password")
                .isToUseCookieInfo(true)
                .build();

        FeignRestClient main = new FeignRestClient(config);
        MensagemServiceFormApi app = main.target(MensagemServiceFormApi.class);


        Response resp = app.login(new MensagemServiceFormApi.LoginParam("username", "password"));

        if(resp.status() == 200 ){
            System.out.println( resp.headers());
        }
    }

    public static void basic() {
        ConfigRest config = ConfigRest.forBaseUrl("http://localhost:8080")
                .system("my-app-01")
                //.basicLogin("username", "password")
                .isToUseCookieInfo(true)
                .build();

        FeignRestClient main = new FeignRestClient(config);
        MensagemServiceBasicApi app = main.target(MensagemServiceBasicApi.class);
        BusinessNotification response = app.mensagemGet();

        System.out.println(app.mensagemGet());
    }
}
