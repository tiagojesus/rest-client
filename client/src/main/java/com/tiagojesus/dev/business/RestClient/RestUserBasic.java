package com.tiagojesus.dev.business.RestClient;

public class RestUserBasic implements RestUser {
    private final String user;
    private final String password;
    private final String system;

    public RestUserBasic(String user, String password, String system) {

        this.user = user;
        this.password = password;
        this.system = system;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getSystem() {
        return system;
    }

    @Override
    public String getToken() {
        return String.format("Basic %s:%s:%s", system,user,password);
    }
}
