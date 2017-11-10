package com.calendar.project.dao.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Tag;
import com.calendar.project.model.TagType;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTag(Tag tag) {
        entityManager.persist(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        entityManager.merge(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        entityManager.remove(entityManager.contains(tag) ? tag : entityManager.merge(tag));
    }

    @Override
    public List<Tag> getAllTags() {
        return entityManager.createQuery("select t from Tag t", Tag.class)
                .getResultList();
    }

    @Override
    public Tag getTagByName(TagType tag) {
        Tag eventTag = entityManager.createQuery("from Tag t where t.tag = :tag_name", Tag.class)
                .setParameter("tag_name", tag)
                .getSingleResult();

        Hibernate.initialize(eventTag.getEvents());  // TODO need to test

        return eventTag;
    }

    @Override
    public Tag getTagById(Long tagId) {
        Tag tag = entityManager.createQuery("from Tag where id = :tag_id", Tag.class)
                .setParameter("ag_id", tagId)
                .getSingleResult();

        Hibernate.initialize(tag.getEvents());  // TODO need to test

        return tag;
    }
}
