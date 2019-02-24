package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.feigh.rest.client.ConfigRest;
import com.tiagojesus.dev.feigh.rest.client.FeignRestClient;
import feign.Response;

public class MainFeignApp {
    public static void main(String[] args) {
        ConfigRest config = ConfigRest.forBaseUrl("http://localhost:8080")
                .system("my-app-01")
                //.basicLogin("username", "password")
                .build();

        FeignRestClient main = new FeignRestClient(config);
        MensagemServiceApi app = main.target(MensagemServiceApi.class);
        Response response = app.mensagemGet();

        System.out.println(app.notificationFor("username"));
    }
}
