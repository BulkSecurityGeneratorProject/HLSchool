package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.LessonLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LessonLog and its DTO LessonLogDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, LessonMapper.class})
public interface LessonLogMapper extends EntityMapper<LessonLogDTO, LessonLog> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "lesson.id", target = "lessonId")
    LessonLogDTO toDto(LessonLog lessonLog);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "lessonId", target = "lesson")
    LessonLog toEntity(LessonLogDTO lessonLogDTO);

    default LessonLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        LessonLog lessonLog = new LessonLog();
        lessonLog.setId(id);
        return lessonLog;
    }
}
