package com.calendar.project.dao.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    private static final Logger LOGGER = Logger.getLogger(TagDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTag(Tag tag) {
        LOGGER.info("Atempt to save tag = \"" + tag + "\"");

        try {
            entityManager.persist(tag);

            LOGGER.info("Tag = \"" + tag + "\" successfully saved");
        } catch (Exception ex) {
            LOGGER.error("Tag = \"" + tag + "\" was not saved. " + ex);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        LOGGER.info("Atempt to update tag = \"" + tag + "\"");

        try {
            entityManager.merge(tag);

            LOGGER.info("Tag = \"" + tag + "\" successfully updated");
        } catch (Exception ex) {
            LOGGER.error("Tag = \"" + tag + "\" was not updated. " + ex);
        }

    }

    @Override
    public void deleteTag(Tag tag) {
        LOGGER.info("Atempt to delete tag = \"" + tag + "\"");
        try {
            entityManager.remove(entityManager.contains(tag) ? tag : entityManager.merge(tag));

            LOGGER.info("Tag = \"" + tag + "\" successfully deleted");
        } catch (Exception ex) {
            LOGGER.error("Tag = \"" + tag + "\" was not deleted. " + ex);
        }
    }

    @Override
    public Tag getTagById(Long tagId) {
        Tag tag = entityManager.createQuery("select distinct t from Tag t join fetch t.events where t.id = :tag_id", Tag.class)
                .setParameter("tag_id", tagId)
                .getSingleResult();

        LOGGER.info("Return tag = \"" + tag + "\" with id = " + tagId);
        return tag;
    }

    @Override
    public Tag getTagByName(TagType tag) {
        Tag eventTag = entityManager.createQuery("select distinct t from Tag t join fetch t.events where t.tag = :tag_name", Tag.class)
                .setParameter("tag_name", tag)
                .getSingleResult();

        LOGGER.info("Return tag = \"" + eventTag + "\" of type = " + tag);
        return eventTag;
    }

    @Override
    public List<Tag> getTagsByEvent(int EventId) {
        return entityManager.createQuery("SELECT t FROM Tag t JOIN t.events e WHERE e.id = :EventId", Tag.class)
                .setParameter("EventId", EventId)
                .getResultList();
    }

    @Override
    public List<Tag> getAllTags() {
        return entityManager.createQuery("select distinct t from Tag t left JOIN FETCH t.events", Tag.class)
                .getResultList();
    }
}