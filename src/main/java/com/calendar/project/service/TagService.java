package com.calendar.project.service;

import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;

import java.io.IOException;

import java.util.List;
import java.util.Set;

public interface TagService {

    void saveTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagById(Long tagId);

    Tag getTagByName(TagType tagName);

    List<Tag> getTagsByEvent(int EventId);

    List<TagType> getTagsTypeList();

    String getTagsJson(List<Tag> tags) throws IOException;

    List<Tag> getAllTags();

    Set<Tag> parseListOfStringToSetOfTag(List<String> listOfString);

    Set<Tag> parseIntegerListToTagList(List<Long> intList);

}
