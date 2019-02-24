package com.tiagojesus.dev;

import com.tiagojesus.dev.business.RestClient.RestUser;
import com.tiagojesus.dev.business.RestClient.RestUserBasic;
import com.tiagojesus.dev.business.RestClient.BusinessRestClient;
import com.tiagojesus.dev.business.core.model.BusinessNotification;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static final String GITHUB_OKHTTL_URL = "https://api.github.com/repos/square/okhttp/issues";
    public static final String LOCALHOST_URL = "http://localhost:8080/";


    public static void main(String[] args){
        final String url = LOCALHOST_URL;

        RestUser user = new RestUserBasic("user", "password", "App1");

        BusinessRestClient cliente = new BusinessRestClient(user);
        String texto = cliente.GET(url);
        System.out.println(texto);

        Map<String, String> map = new HashMap<>();
        map.put("param1", "1");
        map.put("param2", "2");

        texto = cliente.GET(cliente.buildUrl(url, map));
        System.out.println(texto);

        texto = cliente.POST(url);
        System.out.println(texto);

        BusinessNotification param = new BusinessNotification("new code", "new description");


        texto = cliente.POST(url, param.toString());
        System.out.println(texto);
    }
}