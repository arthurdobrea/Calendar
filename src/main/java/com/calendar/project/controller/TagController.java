package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.TagService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    TagService tagService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    private static final Logger LOGGER = Logger.getLogger(TagController.class);

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        LOGGER.info("Request of \"/tags\" page GET");
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("UserEvents", userService.getUsersListBySubscriptionByEventType("MEETING"));
        model.addAttribute("UserTags", userService.getUsersListBySubscriptionByTagType("APPLICATION_MANAGEMENT"));
        model.addAttribute("evensByTag", eventService.getEventsByTag(TagType.APPLICATION_MANAGEMENT));
        model.addAttribute("fullname", userService.findLikeFullName("a"));
        LOGGER.info("Opening of \"/tags\" page");
        return "tags";
    }

    @RequestMapping(value = "/create-tag", method = RequestMethod.GET)
    public String createTag(Model model) {
        LOGGER.info("Request of \"/create-tag\" page GET");
        model.addAttribute("tagForm", new Tag());
        LOGGER.info("Opening of \"/create-tag\" page");
        return "createTag";
    }

    @RequestMapping(value = "/create-tag", method = RequestMethod.POST)
    public String createTag(@ModelAttribute("tagForm") Tag tagForm) {
        LOGGER.info("Request of \"/create-tag\" page POST");
        tagService.saveTag(tagForm);
        LOGGER.info("Redirect to \"/tags\" page");
        return "redirect:/tags";
    }

    @RequestMapping(value = "/delete/{tag.id}", method = RequestMethod.GET)
    public String deleteTag (@PathVariable("tag.id") String tagId, Model model)  {
        LOGGER.info("Request of \"/delete/{tag.id}\" page GET");
        tagService.deleteTag(tagService.getTag(new Long(tagId)));
        model.addAttribute("tags", tagService.getAllTags());
        LOGGER.info("Redirect to \"/tags\" page");
        return "redirect:/tags";
    }

    @RequestMapping(value = "/updateTag", method = RequestMethod.GET)
    public String updateTag(Long tagId, Model model) {
        LOGGER.info("Request of \"/updateTag\" page GET");
        model.addAttribute("tagForm", tagService.getTag(tagId));
        LOGGER.info("Opening of \"/updateTag\" page");
        return "updateTag";
    }

    @RequestMapping(value = "/updateTag", method = RequestMethod.POST)
    public String updateTag(@ModelAttribute("tagForm") Tag tagForm, Model model) {
        LOGGER.info("Request of \"/updateTag\" page POST");
        System.out.println("Tag= "+tagForm);
        model.addAttribute("tagForm", tagForm);
        tagService.updateTag(tagForm);
        LOGGER.info("Redirect to \"/tags\" page");
        return "redirect:/tags";
    }
    @RequestMapping(value = "/tagList", method = RequestMethod.GET)
    public String getTagList(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "TagList";
    }

    @RequestMapping(value = "/tagList", method = RequestMethod.POST)
    public String getTagList(Model model, @RequestParam("checkboxName")List<String> checkboxValue) {
        model.addAttribute("tagsList",  tagService.parseListOfStringToSetOfTag(checkboxValue));
        model.addAttribute("tags", tagService.getAllTags());
        return "TagList";
    }



}
