package com.calendar.project.dao;



import com.calendar.project.config.*;
import com.calendar.project.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class TagDaoTest {

    private Tag tag;

    @Resource
    private TagDao tagDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        tag = new Tag();
        tag.setTag(TagType.TESTING);
        //Set<Event> events = new HashSet<>();
        tag.setColor("VIOLET");
        entityManager.persist(tag);
    }

    @Test
    public void daoGetTagByNameTest() throws Exception {
        Tag tagFromDb = tagDao.getTagByName(TagType.TESTING.toString());
        Assert.assertEquals(tag, tagFromDb);
    }

    @Test
    public void daoPersistTagTest() throws Exception {
        tagDao.saveTag(tag);
        Tag tagFromDb = tagDao.getTagByName(TagType.TESTING.toString());
        Assert.assertEquals(tag, tagFromDb);
    }

    @Test
    public void daoUpdateTagTest() throws Exception {
        tag.setTag(TagType.DEVELOPMENT);
        tag.setColor("WHITE");
        tagDao.updateTag(tag);

        Tag tagTest = tagDao.getTagByName(TagType.DEVELOPMENT.toString());
        Assert.assertEquals(tag, tagTest);
    }
}
