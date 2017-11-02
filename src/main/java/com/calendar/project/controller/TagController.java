package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.TagService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagController {

    @Autowired
    TagService tagService;
    @Autowired
    EventService eventServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags";
    }

    @RequestMapping(value = "/create-tag", method = RequestMethod.GET)
    public String createTag(Model model) {
        model.addAttribute("tagForm", new Tag());
        return "createTag";
    }

    @RequestMapping(value = "/create-tag", method = RequestMethod.POST)
    public String createTag(@ModelAttribute("tagForm") Tag tagForm) {
        tagService.saveTag(tagForm);
        return "redirect:/tags";
    }
    @RequestMapping(value = "/delete/{tag.id}", method = RequestMethod.GET)
    public String deleteTag (@PathVariable("tag.id") String tagId, ModelMap model)  {
        tagService.deleteTag(tagService.getTag(new Long(tagId)));
        model.addAttribute("tags", tagService.getAllTags());
        return "redirect:/tags";
    }
}
