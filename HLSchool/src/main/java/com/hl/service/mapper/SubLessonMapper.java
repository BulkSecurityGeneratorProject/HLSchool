package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.SubLessonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SubLesson and its DTO SubLessonDTO.
 */
@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface SubLessonMapper extends EntityMapper<SubLessonDTO, SubLesson> {

    @Mapping(source = "lesson.id", target = "lessonId")
    SubLessonDTO toDto(SubLesson subLesson);

    @Mapping(source = "lessonId", target = "lesson")
    SubLesson toEntity(SubLessonDTO subLessonDTO);

    default SubLesson fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubLesson subLesson = new SubLesson();
        subLesson.setId(id);
        return subLesson;
    }
}
