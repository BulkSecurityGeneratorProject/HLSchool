package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.UserLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserLog and its DTO UserLogDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserLogMapper extends EntityMapper<UserLogDTO, UserLog> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    UserLogDTO toDto(UserLog userLog);

    @Mapping(source = "userId", target = "user")
    UserLog toEntity(UserLogDTO userLogDTO);

    default UserLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserLog userLog = new UserLog();
        userLog.setId(id);
        return userLog;
    }
}
