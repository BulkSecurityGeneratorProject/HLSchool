package com.hl.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Gift entity.
 */
public class GiftDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer price;

    @NotNull
    @Lob
    private byte[] image;
    private String imageContentType;

    @NotNull
    private String content;

    private ZonedDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GiftDTO giftDTO = (GiftDTO) o;
        if(giftDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), giftDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GiftDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
