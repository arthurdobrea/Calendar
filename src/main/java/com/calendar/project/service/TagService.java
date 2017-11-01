package com.calendar.project.service;

import com.calendar.project.model.Tag;

import java.util.List;

public interface TagService {

    public void saveTag(Tag tag);

    public void updateTag(Tag tag);

    public void deleteTag(Tag tag);

    public Tag getTagByName(String tagName);

    public Tag getTagById(Long tagId);

    public Tag getTag(Long id);

    List<Tag> getAllTags();

}
