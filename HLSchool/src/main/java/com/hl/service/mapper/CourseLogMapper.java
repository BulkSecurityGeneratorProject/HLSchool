package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.CourseLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLog and its DTO CourseLogDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface CourseLogMapper extends EntityMapper<CourseLogDTO, CourseLog> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "course.id", target = "courseId")
    CourseLogDTO toDto(CourseLog courseLog);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "courseId", target = "course")
    CourseLog toEntity(CourseLogDTO courseLogDTO);

    default CourseLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLog courseLog = new CourseLog();
        courseLog.setId(id);
        return courseLog;
    }
}
