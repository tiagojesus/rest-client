package com.tiagojesus.dev.feigh.rest.example;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import com.tiagojesus.dev.feigh.rest.client.BasicServiceAPI;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

public interface MensagemServiceFormApi extends BasicServiceAPI {
    @RequestLine("POST /login")
    Response login(LoginParam param);

    @RequestLine("GET /api/form/")
    BusinessNotification mensagemGet();

    @RequestLine("POST /api/form/")
    BusinessNotification mensagemPOST();

    @RequestLine("GET /api/form/for/{owner}")
    List<BusinessNotification> notificationFor(@Param("owner") String owner);

    class LoginParam {
        private final String username;
        private final String password;

        public LoginParam(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
