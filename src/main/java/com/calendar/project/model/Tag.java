package com.calendar.project.model;

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
    private String tag;

   @ManyToMany(fetch = FetchType.EAGER )
   @JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "tag_id"),
           inverseJoinColumns = @JoinColumn(name = "event_id"))
//    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Event> events;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;
        if (!this.tag.equals(tag.tag)) return false;
        return events != null ? events.equals(tag.events) : tag.events == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + tag.hashCode();
        result = 31 * result + (events != null ? events.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", events=" + events +
                '}';
    }
}
