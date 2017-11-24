package com.calendar.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

    @Configuration
    @EnableResourceServer
    public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        private static final String RESOURCE_ID = "my_rest_api";
        private static final String GUEST = "GUEST";
        private static final String USER = "USER";
        private static final String ADMIN = "ADMIN";
        private static final String SUPREME_ADMIN ="SUPREME_ADMIN";

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.
                    anonymous().disable()
                    .requestMatchers().antMatchers("/json/**")
                    .and().authorizeRequests()
                    .antMatchers("/json/deleteUserJson").hasAnyRole(ADMIN,SUPREME_ADMIN)
                    .antMatchers("/json/updateUserJson").hasAnyRole(USER,ADMIN,SUPREME_ADMIN)
                    .antMatchers("/json/createEventJson").hasAnyRole(USER,ADMIN,SUPREME_ADMIN)
                    .antMatchers("/json/deleteEventJson").hasAnyRole(USER,ADMIN,SUPREME_ADMIN)
                    .antMatchers("/json/updateEventJson").hasAnyRole(USER,ADMIN,SUPREME_ADMIN)
                    .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }

    }