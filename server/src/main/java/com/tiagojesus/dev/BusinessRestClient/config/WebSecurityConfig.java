package com.tiagojesus.dev.BusinessRestClient.config;

import com.tiagojesus.dev.BusinessRestClient.web.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    static final String[] PERMIT_ALL_RESOURCE = {"/", "/home", "/login", "/logout", "/accessDenied"};
    private static String REALM="MY_TEST_REALM";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("username").password("password").roles("USER", "API_BASIC")
                .and()
                .withUser("user").password("password").roles("USER", "API_FORM")
                .and()
                .withUser("admin").password("password") .roles("USER", "ADMIN");
    }

//    @Configuration
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers(PERMIT_ALL_RESOURCE).permitAll()
                    .antMatchers("/api/basic/**").hasRole("API_BASIC")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().realmName(REALM)
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }/* To allow Pre-flight [OPTIONS] request from browser */
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }
    }

    @Configuration
    @Order(1)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    //.cors().disable()
                    .authorizeRequests()

                    .antMatchers(PERMIT_ALL_RESOURCE).permitAll()
                    .antMatchers("/api/form/**").hasRole("API_FORM")
                    .anyRequest().authenticated()
                    .and()
                        .formLogin()

                    .and()
                        .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()

                    .and();
        }/* To allow Pre-flight [OPTIONS] request from browser */
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }
    }


    @Bean
    public PasswordEncoder encoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };
    }
}


