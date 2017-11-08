package com.calendar.project.dao;

import com.calendar.project.model.Tag;

import java.util.List;

public interface TagDao {
    public void saveTag(Tag tag);

    public void updateTag(Tag tag);

    public void deleteTag(Tag tag);

    public List<Tag> getAllTags();

    public Tag getTagById(long EventTagId);

    public Tag getTagByName(String tag);

    List<Tag> getTagsByEvent(Long EventId);
}
