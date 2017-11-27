package com.calendar.project.dao;

import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;

import java.util.List;

public interface TagDao {

    void saveTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Tag tag);

    List<Tag> getAllTags();

    Tag getTagById(Long EventTagId);

    Tag getTagByName(TagType tag);

    List<Tag> getTagsByEvent(int EventId);

}
