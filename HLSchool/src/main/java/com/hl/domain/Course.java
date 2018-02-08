package com.hl.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @NotNull
    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @NotNull
    @Size(min = 5)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private Integer level;

    @Lob
    @Column(name = "contenten")
    private String contenten;

    @Lob
    @Column(name = "contentvi")
    private String contentvi;

    @NotNull
    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "image_content_type", nullable = false)
    private String imageContentType;

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

    public Course createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Boolean isActivated() {
        return activated;
    }

    public Course activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getTitle() {
        return title;
    }

    public Course title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public Course level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getContenten() {
        return contenten;
    }

    public Course contenten(String contenten) {
        this.contenten = contenten;
        return this;
    }

    public void setContenten(String contenten) {
        this.contenten = contenten;
    }

    public String getContentvi() {
        return contentvi;
    }

    public Course contentvi(String contentvi) {
        this.contentvi = contentvi;
        return this;
    }

    public void setContentvi(String contentvi) {
        this.contentvi = contentvi;
    }

    public byte[] getImage() {
        return image;
    }

    public Course image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Course imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
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
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", activated='" + isActivated() + "'" +
            ", title='" + getTitle() + "'" +
            ", level=" + getLevel() +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
