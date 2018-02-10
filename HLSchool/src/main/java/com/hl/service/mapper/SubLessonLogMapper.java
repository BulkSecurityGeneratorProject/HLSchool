package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.SubLessonLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SubLessonLog and its DTO SubLessonLogDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, SubLessonMapper.class})
public interface SubLessonLogMapper extends EntityMapper<SubLessonLogDTO, SubLessonLog> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "subLesson.id", target = "subLessonId")
    SubLessonLogDTO toDto(SubLessonLog subLessonLog);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "subLessonId", target = "subLesson")
    SubLessonLog toEntity(SubLessonLogDTO subLessonLogDTO);

    default SubLessonLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubLessonLog subLessonLog = new SubLessonLog();
        subLessonLog.setId(id);
        return subLessonLog;
    }
}
