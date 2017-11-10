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


    @RequestMapping(value = "/basicWebsockets", method = RequestMethod.GET )
    public String basicWebsocketsPage(Model model, Principal principal, Locale locale) {
        String formattedDate = Util.getSimpleDate(locale);
        String userName = principal.getName();
        model.addAttribute("username", userName);
        model.addAttribute("time", formattedDate);
        return "basicWebsockets";
    }

}