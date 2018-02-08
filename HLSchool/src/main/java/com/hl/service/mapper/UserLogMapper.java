package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.UserLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserLog and its DTO UserLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserLogMapper extends EntityMapper<UserLogDTO, UserLog> {



    default UserLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserLog userLog = new UserLog();
        userLog.setId(id);
        return userLog;
    }
}
