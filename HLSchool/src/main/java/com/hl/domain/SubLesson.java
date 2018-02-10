package com.hl.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A SubLesson.
 */
@Entity
@Table(name = "sub_lesson")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sublesson")
public class SubLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @NotNull
    @Size(min = 5)
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "contenten")
    private String contenten;

    @Lob
    @Column(name = "contentvi")
    private String contentvi;

    @Lob
    @Column(name = "raw_data")
    private String rawData;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public SubLesson createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public SubLesson title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenten() {
        return contenten;
    }

    public SubLesson contenten(String contenten) {
        this.contenten = contenten;
        return this;
    }

    public void setContenten(String contenten) {
        this.contenten = contenten;
    }

    public String getContentvi() {
        return contentvi;
    }

    public SubLesson contentvi(String contentvi) {
        this.contentvi = contentvi;
        return this;
    }

    public void setContentvi(String contentvi) {
        this.contentvi = contentvi;
    }

    public String getRawData() {
        return rawData;
    }

    public SubLesson rawData(String rawData) {
        this.rawData = rawData;
        return this;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
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
        SubLesson subLesson = (SubLesson) o;
        if (subLesson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subLesson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubLesson{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", title='" + getTitle() + "'" +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
