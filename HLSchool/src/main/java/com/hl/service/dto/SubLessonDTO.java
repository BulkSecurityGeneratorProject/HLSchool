package com.hl.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SubLesson entity.
 */
public class SubLessonDTO implements Serializable {

    private Long id;

    private ZonedDateTime createDate;

    @NotNull
    @Size(min = 5)
    private String title;

    @Lob
    private String contenten;

    @Lob
    private String contentvi;

    @Lob
    private String rawData;

    private Long lessonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenten() {
        return contenten;
    }

    public void setContenten(String contenten) {
        this.contenten = contenten;
    }

    public String getContentvi() {
        return contentvi;
    }

    public void setContentvi(String contentvi) {
        this.contentvi = contentvi;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubLessonDTO subLessonDTO = (SubLessonDTO) o;
        if(subLessonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subLessonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubLessonDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", title='" + getTitle() + "'" +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
