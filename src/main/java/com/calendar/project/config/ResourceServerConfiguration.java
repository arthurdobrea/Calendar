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
        http
                .anonymous()
                .disable()
                .requestMatchers().antMatchers("/rest/**")
                .and()
                    .authorizeRequests()
                        .antMatchers("/rest/getEventsByType").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getEventsByTag").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getEventsByAuthor").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getEventsByUser").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/deleteUser").hasAnyRole(ADMIN, SUPREME_ADMIN, GUEST, USER)
                        .antMatchers("/rest/deleteEvent").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/editUser").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getUserByUsername").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getUserById").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/getEvent").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/allTypes").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/allTags").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/updateEvent").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/createEvent").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/countEventsByPeriod").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/period").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/date").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/allEvents").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                        .antMatchers("/rest/users").hasAnyRole(GUEST, USER, ADMIN, SUPREME_ADMIN)
                .and()
                    .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}