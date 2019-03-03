package com.tiagojesus.dev.feigh.rest.client.fluent;


public class UserRestClientFluent {
    public static UserRestClientFluent.Builder forSystem(String system) {
        return new Builder(system);
    }

    public final String system;
    public final String username;
    public final String password;

    private UserRestClientFluent(Builder builder){
        system = builder.system;
        username = builder.username;
        password = builder.password;
    }

    static public class Builder {
        private final String system;
        private String username;
        private String password;

        private Builder(String system) {
            this.system = system;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserRestClientFluent build(){
            return new UserRestClientFluent(this);
        }
    }
}
