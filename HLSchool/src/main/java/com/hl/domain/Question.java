package com.hl.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.hl.domain.enumeration.QuestionType;

import com.hl.domain.enumeration.QuestionSubType;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "question_sub_type", nullable = false)
    private QuestionSubType questionSubType;

    @NotNull
    @Column(name = "contenten", nullable = false)
    private String contenten;

    @NotNull
    @Column(name = "contentvi", nullable = false)
    private String contentvi;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Lob
    @Column(name = "jhi_resource")
    private byte[] resource;

    @Column(name = "jhi_resource_content_type")
    private String resourceContentType;

    @Lob
    @Column(name = "raw_data")
    private String rawData;

    @ManyToOne
    private SubLesson subLesson;

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

    public Question createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public Question questionType(QuestionType questionType) {
        this.questionType = questionType;
        return this;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionSubType getQuestionSubType() {
        return questionSubType;
    }

    public Question questionSubType(QuestionSubType questionSubType) {
        this.questionSubType = questionSubType;
        return this;
    }

    public void setQuestionSubType(QuestionSubType questionSubType) {
        this.questionSubType = questionSubType;
    }

    public String getContenten() {
        return contenten;
    }

    public Question contenten(String contenten) {
        this.contenten = contenten;
        return this;
    }

    public void setContenten(String contenten) {
        this.contenten = contenten;
    }

    public String getContentvi() {
        return contentvi;
    }

    public Question contentvi(String contentvi) {
        this.contentvi = contentvi;
        return this;
    }

    public void setContentvi(String contentvi) {
        this.contentvi = contentvi;
    }

    public byte[] getImage() {
        return image;
    }

    public Question image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Question imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getResource() {
        return resource;
    }

    public Question resource(byte[] resource) {
        this.resource = resource;
        return this;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    public String getResourceContentType() {
        return resourceContentType;
    }

    public Question resourceContentType(String resourceContentType) {
        this.resourceContentType = resourceContentType;
        return this;
    }

    public void setResourceContentType(String resourceContentType) {
        this.resourceContentType = resourceContentType;
    }

    public String getRawData() {
        return rawData;
    }

    public Question rawData(String rawData) {
        this.rawData = rawData;
        return this;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public SubLesson getSubLesson() {
        return subLesson;
    }

    public Question subLesson(SubLesson subLesson) {
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
        Question question = (Question) o;
        if (question.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", questionType='" + getQuestionType() + "'" +
            ", questionSubType='" + getQuestionSubType() + "'" +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", resource='" + getResource() + "'" +
            ", resourceContentType='" + getResourceContentType() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
