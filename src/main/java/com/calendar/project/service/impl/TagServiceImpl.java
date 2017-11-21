package com.calendar.project.service.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


}
