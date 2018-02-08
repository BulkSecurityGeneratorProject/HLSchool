package com.hl.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the GiftLog entity.
 */
public class GiftLogDTO implements Serializable {

    private Long id;

    private ZonedDateTime createDate;

    private Long userId;

    private String userLogin;

    private Long giftId;

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

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GiftLogDTO giftLogDTO = (GiftLogDTO) o;
        if(giftLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), giftLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GiftLogDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
