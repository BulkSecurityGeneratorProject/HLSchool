package com.hl.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.hl.domain.enumeration.QuestionType;
import com.hl.domain.enumeration.QuestionSubType;

/**
 * A DTO for the Question entity.
 */
public class QuestionDTO implements Serializable {

    private Long id;

    private ZonedDateTime createDate;

    @NotNull
    private QuestionType questionType;

    @NotNull
    private QuestionSubType questionSubType;

    @NotNull
    private String contenten;

    @NotNull
    private String contentvi;

    @Lob
    private byte[] image;
    private String imageContentType;

    @Lob
    private byte[] resource;
    private String resourceContentType;

    @Lob
    private String rawData;

    private Long subLessonId;

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

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionSubType getQuestionSubType() {
        return questionSubType;
    }

    public void setQuestionSubType(QuestionSubType questionSubType) {
        this.questionSubType = questionSubType;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getResource() {
        return resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    public String getResourceContentType() {
        return resourceContentType;
    }

    public void setResourceContentType(String resourceContentType) {
        this.resourceContentType = resourceContentType;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Long getSubLessonId() {
        return subLessonId;
    }

    public void setSubLessonId(Long subLessonId) {
        this.subLessonId = subLessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuestionDTO questionDTO = (QuestionDTO) o;
        if(questionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), questionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", questionType='" + getQuestionType() + "'" +
            ", questionSubType='" + getQuestionSubType() + "'" +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", image='" + getImage() + "'" +
            ", resource='" + getResource() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
