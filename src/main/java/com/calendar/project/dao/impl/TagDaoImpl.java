package com.calendar.project.dao.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Tag;
import org.apache.log4j.Logger;
import com.calendar.project.model.enums.TagType;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(TagDaoImpl.class);

    @Override
    public void saveTag(Tag tag) {
        LOGGER.info("Atempt to save tag = \"" + tag + "\"");
        try {
            entityManager.persist(tag);
            LOGGER.info("Tag = \"" + tag + "\" successfully saved" );
        } catch (Exception ex){
            LOGGER.error("Tag = \"" + tag + "\" was not saved. " + ex);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        LOGGER.info("Atempt to update tag = \"" + tag + "\"");
        try{
            entityManager.merge(tag);
            LOGGER.info("Tag = \"" + tag + "\" successfully updated" );
        } catch(Exception ex){
            LOGGER.error("Tag = \"" + tag + "\" was not updated. " + ex);
        }

    }

    @Override
    public void deleteTag(Tag tag) {
        LOGGER.info("Atempt to delete tag = \"" + tag + "\"");
        try {
            entityManager.remove(entityManager.contains(tag) ? tag : entityManager.merge(tag));
            LOGGER.info("Tag = \"" + tag + "\" successfully deleted" );
        } catch(Exception ex){
            LOGGER.error("Tag = \"" + tag + "\" was not deleted. " + ex);
        }
    }

    @Override
    public List<Tag> getAllTags() {
        LOGGER.info("Return list of all tags");
        return entityManager.createQuery("select t from Tag t", Tag.class)
                .getResultList();
    }

    @Override
    public Tag getTagByName(TagType tag) {
        Tag eventTag = entityManager.createQuery("from Tag t where t.tag = :tag_name", Tag.class)
                .setParameter("tag_name", tag)
                .getSingleResult();

        Hibernate.initialize(eventTag.getEvents());  // TODO need to test

        LOGGER.info("Return tag = \"" + eventTag + "\" of type = " + tag);
        return eventTag;
    }

    @Override
    public Tag getTagById(Long tagId) {
        Tag tag = entityManager.createQuery("from Tag where id = :tag_id", Tag.class)
                .setParameter("ag_id", tagId)
                .getSingleResult();

        Hibernate.initialize(tag.getEvents());  // TODO need to test

        LOGGER.info("Return tag = \"" + tag + "\" with id = " + tagId);
        return tag;
    }
}
