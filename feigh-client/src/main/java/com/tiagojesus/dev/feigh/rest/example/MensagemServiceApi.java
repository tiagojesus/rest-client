package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import com.tiagojesus.dev.feigh.rest.client.BasicServiceAPI;
import feign.Response;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface MensagemServiceApi extends BasicServiceAPI {
    @GET
    @Path("/")
    Response mensagemGet();

    @POST
    @Path("/")
    BusinessNotification mensagemPOST();

    @GET
    @Path("/for/{owner}")
    List<BusinessNotification> notificationFor(@PathParam("owner") String owner);
}
