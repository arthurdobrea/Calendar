package com.calendar.project.controller;

import com.calendar.project.model.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Locale;

@Controller
public class LearnWebSocketsMVCController {

    private static final Logger LOG = LoggerFactory
            .getLogger(LearnWebSocketsMVCController.class);


    @RequestMapping(value = "/basicWebsockets", method = RequestMethod.GET )
    public String basicWebsocketsPage(Model model, Principal principal,
                                      Locale locale) {

        // Get a simple human readable date and time
        String formattedDate = Util.getSimpleDate(locale);

        // Get the logged in user's name
        String userName = principal.getName();

        // Set some sample messages to show on the landing 'basicWebsockets.jsp'
        // page.
        model.addAttribute("username", userName);
        model.addAttribute("time", formattedDate);

        LOG.info(
                "Request from user:{} for /secured/basicWebsockets url processed at time:{}",
                userName, formattedDate);

        return "basicWebsockets";
    }

}