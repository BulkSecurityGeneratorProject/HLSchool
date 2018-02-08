package com.hl.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserLog entity.
 */
public class UserLogDTO implements Serializable {

    private Long id;

    private ZonedDateTime createDate;

    private Boolean complete;

    private Integer point;

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

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserLogDTO userLogDTO = (UserLogDTO) o;
        if(userLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserLogDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", complete='" + isComplete() + "'" +
            ", point=" + getPoint() +
            "}";
    }
}
