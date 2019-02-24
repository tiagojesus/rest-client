package com.tiagojesus.dev.feigh.rest.client;

import feign.Util;

import java.nio.charset.Charset;
import java.util.Optional;

import static com.tiagojesus.dev.feigh.rest.client.UtilRest.fomatErroString;

public class ConfigRest {
    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final Charset CHARSET_DEFAULT = CHARSET_UTF_8;
    public static final String SYTEM_NAME_ON_HTTP_HEADER = "X-SYSTEM";

    public final Optional<String> system;
    public final Charset charset;
    public final String systemNameOnHttpHeader;
    public final Optional<String> username;
    public final Optional<String> password;
    public final boolean hasBasicLogin;
    public final boolean isToUseCookieInfo;
    public final String urlServiceBase;

    public static ConfigRestBuilder forBaseUrl(String urlServiceBase){
        return new ConfigRestBuilder(urlServiceBase);
    }

    private ConfigRest(String urlServiceBase, Optional<String> system, Optional<Charset> charset, Optional<String> systemNameOnHttpHeader,
                      Optional<String> username, Optional<String> password, boolean hasBasicLogin, boolean isToUseCookieInfo) {
        this.urlServiceBase = urlServiceBase;
        this.system = system;
        this.charset = charset.isPresent() ? charset.get() : CHARSET_UTF_8;
        this.systemNameOnHttpHeader = systemNameOnHttpHeader.isPresent() ? systemNameOnHttpHeader.get() : SYTEM_NAME_ON_HTTP_HEADER;
        this.username = username;
        this.password = password;
        this.hasBasicLogin = hasBasicLogin;
        this.isToUseCookieInfo = isToUseCookieInfo;
    }


    public static class ConfigRestBuilder {
        private String urlServiceBase;
        private Optional<String> system = Optional.of( "none");
        private Optional<Charset> charset = Optional.of(ConfigRest.CHARSET_DEFAULT);
        private Optional<String> systemNameOnHttpHeader = Optional.of(ConfigRest.SYTEM_NAME_ON_HTTP_HEADER);
        private Optional<String> username;
        private Optional<String> password;
        private boolean isToUseBasicLogin = false;
        private boolean isToUseCookieInfo = false;

        private ConfigRestBuilder(String urlServiceBase){
            this.urlServiceBase = Optional.of(urlServiceBase).get() ;
        }

        public ConfigRestBuilder system(String system) {
            this.system = Optional.ofNullable(system);
            return this;
        }

        public ConfigRestBuilder charset(Charset charset) {
            this.charset = Optional.ofNullable(charset);
            return this;
        }

        public ConfigRestBuilder systemNameOnHttpHeader(String systemNameOnHttpHeader) {
            this.systemNameOnHttpHeader = Optional.ofNullable(systemNameOnHttpHeader);
            return this;
        }

        public ConfigRestBuilder basicLogin(String username, String password) {
            UtilRest.checkNotNull(username, "Usernamer cant be null");
            UtilRest.checkNotNull(username, "Password cant be null");

            this.username = Optional.of(username);
            this.password = Optional.of(password);
            this.isToUseBasicLogin = true;
            return this;
        }

        public ConfigRestBuilder isToUseBasicLogin(boolean isToUseBasicLogin) {
            this.isToUseBasicLogin = isToUseBasicLogin;
            return this;
        }

        public ConfigRestBuilder isToUseCookieInfo(boolean isToUseCookieInfo) {
            this.isToUseCookieInfo = isToUseCookieInfo;
            return this;
        }

        public ConfigRest build() {
            if(isToUseBasicLogin && (!username.isPresent() || !password.isPresent())){
                String msg = "For using http Basic Login, you need inform the username and password ";
                throw new RestClientException(fomatErroString(msg));
            }
            return new ConfigRest(urlServiceBase, system, charset, systemNameOnHttpHeader, username, password, isToUseBasicLogin, isToUseCookieInfo);
        }
    }
}
