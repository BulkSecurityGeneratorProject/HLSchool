package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.VocabularyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vocabulary and its DTO VocabularyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VocabularyMapper extends EntityMapper<VocabularyDTO, Vocabulary> {



    default Vocabulary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setId(id);
        return vocabulary;
    }
}
