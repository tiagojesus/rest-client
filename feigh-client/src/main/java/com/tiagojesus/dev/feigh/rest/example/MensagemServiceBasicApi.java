package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import com.tiagojesus.dev.feigh.rest.client.BasicServiceAPI;
import feign.Response;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface MensagemServiceBasicApi extends BasicServiceAPI {
    @GET
    @Path("/api/basic/")
    BusinessNotification mensagemGet();

    @POST
    @Path("/api/basic/")
    BusinessNotification mensagemPOST();

    @GET
    @Path("/api/basic/for/{owner}")
    List<BusinessNotification> notificationFor(@PathParam("owner") String owner);
}
