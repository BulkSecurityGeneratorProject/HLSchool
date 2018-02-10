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
 * A Gift.
 */
@Entity
@Table(name = "gift")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "gift")
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "image_content_type", nullable = false)
    private String imageContentType;

    @Lob
    @Column(name = "contenten")
    private String contenten;

    @Lob
    @Column(name = "contentvi")
    private String contentvi;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

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

    public Integer getPrice() {
        return price;
    }

    public Gift price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public Gift image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Gift imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getContenten() {
        return contenten;
    }

    public Gift contenten(String contenten) {
        this.contenten = contenten;
        return this;
    }

    public void setContenten(String contenten) {
        this.contenten = contenten;
    }

    public String getContentvi() {
        return contentvi;
    }

    public Gift contentvi(String contentvi) {
        this.contentvi = contentvi;
        return this;
    }

    public void setContentvi(String contentvi) {
        this.contentvi = contentvi;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Gift createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getRawData() {
        return rawData;
    }

    public Gift rawData(String rawData) {
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
        Gift gift = (Gift) o;
        if (gift.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gift.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gift{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", contenten='" + getContenten() + "'" +
            ", contentvi='" + getContentvi() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", rawData='" + getRawData() + "'" +
            "}";
    }
}
