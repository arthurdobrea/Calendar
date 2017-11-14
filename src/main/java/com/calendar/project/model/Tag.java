package com.calendar.project.model;

import com.calendar.project.model.enums.TagType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_name")
    @Enumerated(EnumType.STRING)
    private TagType tag;

    @Column(name = "tag_color")
    private String color;

   @ManyToMany(fetch = FetchType.EAGER )
   @JsonBackReference(value = "child")
   @JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "tag_id"),
           inverseJoinColumns = @JoinColumn(name = "event_id"))
//    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Event> events;

    public Tag() {
    }

    public Tag(TagType tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagType getTag() {
        return tag;
    }

    public void setTag(TagType tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setColorByDefault() {
        this.color = tag.color();
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag1 = (Tag) o;

        if (!id.equals(tag1.id)) return false;
        return tag == tag1.tag;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + tag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag=" + tag +
                ", color='" + color + '\'' +
                '}';
    }
}
