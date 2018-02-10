package com.hl.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SubLessonLog.
 */
@Entity
@Table(name = "sub_lesson_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sublessonlog")
public class SubLessonLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_complete")
    private Boolean complete;

    @Lob
    @Column(name = "raw_data")
    private String rawData;

    @ManyToOne
    private User user;

    @ManyToOne
    private SubLesson subLesson;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isComplete() {
        return complete;
    }

    public SubLessonLog complete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getRawData() {
        return rawData;
    }

    public SubLessonLog rawData(String rawData) {
        this.rawData = rawData;
        return this;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public User getUser() {
        return user;
    }

    public SubLessonLog user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubLesson getSubLesson() {
        return subLesson;
    }

    public SubLessonLog subLesson(SubLesson subLesson) {
        this.subLesson = subLesson;
        return this;
    }

    public void setSubLesson(SubLesson subLesson) {
        this.subLesson = subLesson;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubLessonLog subLessonLog = (SubLessonLog) o;
        if (subLessonLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subLessonLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubLessonLog{" +
            "id=" + getId() +
            ", complete='" + isComplete() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
