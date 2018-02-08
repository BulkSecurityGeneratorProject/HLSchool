package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.AnswerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Answer and its DTO AnswerDTO.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class, VocabularyMapper.class})
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {

    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "vocabulary.id", target = "vocabularyId")
    AnswerDTO toDto(Answer answer);

    @Mapping(source = "questionId", target = "question")
    @Mapping(source = "vocabularyId", target = "vocabulary")
    Answer toEntity(AnswerDTO answerDTO);

    default Answer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setId(id);
        return answer;
    }
}
