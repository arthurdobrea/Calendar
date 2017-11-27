package com.calendar.project.service;

import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TagService {

    public void saveTag(Tag tag);

    public void updateTag(Tag tag);

    public void deleteTag(Tag tag);

    public Tag getTagByName(TagType tagName);

    public Tag getTagById(Long tagId);

    public Tag getTag(Long id);

    public List<Tag> getAllTags();

    public List<TagType> getTagsTypeList();

    String getTagsJson(List<Tag> tags) throws IOException;

    List<Tag> getTagsByEvent(int EventId);

    Set<Tag> parseListOfStringToSetOfTag(List<String> listOfString);

}
