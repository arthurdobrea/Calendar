package com.calendar.project.service.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    private static final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);

    @Override
    public void saveTag(Tag tag) {
        tagDao.saveTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Tag tag) {
        tagDao.deleteTag(tag);
    }

    @Override
    public Tag getTagByName(TagType tagName) {
        return tagDao.getTagByName(tagName);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagDao.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    public Tag getTag(Long id){
        for (Tag tag:getAllTags())
            if (tag.getId().equals(id)) return tag;
        return null;
    }

    @Override
    public List<TagType> getTagsTypeList(){
        List<TagType> tagsTypeList = new ArrayList<>();
        for(TagType tagType : TagType.values()) {
            tagsTypeList.add(tagType);
        }
        return tagsTypeList;
    }

    @Override
    public String getTagsJson(List<Tag> tags) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray tagJsonArr = new JsonArray();

        for (Tag tag : tags) {
            JsonObject tagAsJson = new JsonObject();
            tagAsJson.addProperty("id", tag.getId());
            tagAsJson.addProperty("tag", tag.getTag().toString());
            tagAsJson.addProperty("color", tag.getColor());
            tagAsJson.addProperty("events", tag.getEvents().stream().map(Event::getTitleAndId).collect(Collectors.toList()).toString());
            tagJsonArr.add(tagAsJson);
        }
        String tagsString = tagJsonArr.toString();
        Object json = mapper.readValue(tagsString, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public List<Tag> getTagsByEvent(int EventId){
        return tagDao.getTagsByEvent(EventId);
    };

    @Override
    public Set<Tag> parseListOfStringToSetOfTag(List<String> listOfString){
        Set<Tag> tagList=new HashSet<>();
        for(String tag: listOfString)
            for (Tag tagDBO : getAllTags())
                if (tagDBO.getTag().toString().equals(tag))
                    tagList.add((tagDBO));
        return tagList;
    }


}
