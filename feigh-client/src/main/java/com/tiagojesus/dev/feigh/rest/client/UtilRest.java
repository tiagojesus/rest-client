package com.tiagojesus.dev.feigh.rest.client;

import feign.Util;

import static java.lang.String.format;

public class UtilRest {
    private  UtilRest(){}

    public static String fomatErroString(String msg){
        return "REST_CLIENTE: "+ msg;
    }

    public static <T> T checkNotNull(T reference,
                                     String errorMessageTemplate,
                                     Object... errorMessageArgs) {
        return Util.checkNotNull(reference, errorMessageTemplate, errorMessageArgs);
    }
}
