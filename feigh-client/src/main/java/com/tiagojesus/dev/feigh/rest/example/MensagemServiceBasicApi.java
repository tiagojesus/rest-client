package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import com.tiagojesus.dev.feigh.rest.client.BasicServiceAPI;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface MensagemServiceBasicApi extends BasicServiceAPI {

    @RequestLine("GET /api/basic/")
    BusinessNotification mensagemGet();


    @RequestLine("POST /api/basic/")
    BusinessNotification mensagemPOST();

        @RequestLine("GET /api/basic/for/{owner}")
    List<BusinessNotification> notificationFor(@Param("owner") String owner);
}
