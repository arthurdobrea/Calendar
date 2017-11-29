package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mhristiniuc on 11/29/2017.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class TagDaoTest {

    private Tag tag;
    private Event event;

    @Resource
    private TagDao tagDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        tag = new Tag();
        tag.setId(1L);
        tag.setTag(TagType.APPLICATION_MANAGEMENT);
        tag.setColor("Red");
        event = new Event();
        event.setId(1);
        event.setTitle("Spring Security");
        Set<Event> events = new HashSet<>();
        events.add(event);
        tag.setEvents(events);
    }

    @Test
    public void testSaveTag() throws Exception {
        tagDao.saveTag(tag);
        Tag tagFromDb = tagDao.getTagById(tag.getId());
        Assert.assertEquals(tag, tagFromDb);
    }


    @Test
    public void testUpdateTag() throws Exception {
        tag.setColor("Green");
        tagDao.updateTag(tag);
        Tag tagFromDb = tagDao.getTagById(tag.getId());
        Assert.assertEquals(tag, tagFromDb);
    }

    @Test
    public void testDeleteTag() throws Exception {
        tagDao.saveTag(tag);
        tagDao.deleteTag(tag);
        Tag deletedTag = tagDao.getTagById(tag.getId());
        Assert.assertNull(deletedTag);
    }

    @Test
    public void testGetAllTags() throws Exception {
        List<Tag> allTags = tagDao.getAllTags();
        Assert.assertNotNull(allTags);
    }

    @Test
    public void testGetTagById() throws Exception {
        Assert.assertEquals(tag, tagDao.getTagById(tag.getId()));
    }

    @Test
    public void testGetTagByName() throws Exception {
        Assert.assertEquals(tag, tagDao.getTagByName(tag.getTag()));
    }

    @Test
    public void testGetTagsByEvent() throws Exception {
        List<Tag> tags = tagDao.getTagsByEvent(event.getId());
        Assert.assertNotNull(tags);
    }
}