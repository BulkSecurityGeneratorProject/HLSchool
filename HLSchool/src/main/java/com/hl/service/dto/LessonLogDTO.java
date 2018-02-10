package com.hl.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the LessonLog entity.
 */
public class LessonLogDTO implements Serializable {

    private Long id;

    private Boolean complete;

    @Lob
    private String rawData;

    private Long userId;

    private String userLogin;

    private Long lessonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

        LessonLogDTO lessonLogDTO = (LessonLogDTO) o;
        if(lessonLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lessonLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LessonLogDTO{" +
            "id=" + getId() +
            ", complete='" + isComplete() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
