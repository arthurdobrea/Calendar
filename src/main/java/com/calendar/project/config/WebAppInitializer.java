package com.calendar.project.config;

import com.calendar.project.config.xssfilters.XSSFilter;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(WebAppConfiguration.class);
        ctx.setServletContext(servletContext);

        final ServletRegistration.Dynamic servlet = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(ctx)
        );

        servlet.setLoadOnStartup(1);
        servlet.setMultipartConfig(new MultipartConfigElement("/tmp", 1024 * 1024 * 5,
                1024 * 1024 * 5 * 5, 1024 * 1024));
        servlet.addMapping("/");

        servletContext.addFilter("XSSFilter", XSSFilter.class).addMappingForServletNames(null, false, "dispatcher");
    }
}
