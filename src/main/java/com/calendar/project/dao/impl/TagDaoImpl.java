package com.calendar.project.dao.impl;

import com.calendar.project.dao.TagDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class TagDaoImpl implements TagDao{

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
        List<Tag> tags = entityManager.createQuery("select e from Tag e", Tag.class)
                .getResultList();
        return tags;
    }

    @Override
    public Tag getTagByName(String tag) {
        Tag eventTag = entityManager.createQuery("from Tag e where e.tag=:tag_name", Tag.class)
                .setParameter("tag_name", tag)
                .getSingleResult();
        Hibernate.initialize(eventTag.getEvents());  // TODO need to test
        return eventTag;
    }

    @Override
    public Tag getTagById(long tagId) {
        Tag tag = (Tag) entityManager.createQuery("FROM Tag e WHERE id=:tag_id")
                .setParameter("ag_id", tagId).getSingleResult();

        Hibernate.initialize(tag.getEvents());  // TODO need to test
        return tag;
    }

    @Override
    public List<Tag> getTagsByEvent(Long EventId) {
        return entityManager.createQuery("SELECT t FROM Tag t JOIN t.events e WHERE e.id = :EventId", Tag.class)
                .setParameter("EventId", EventId)
                .getResultList();
    }


}
