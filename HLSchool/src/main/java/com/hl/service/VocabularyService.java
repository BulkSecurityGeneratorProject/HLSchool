package com.hl.service;

import com.hl.service.dto.VocabularyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Vocabulary.
 */
public interface VocabularyService {

    /**
     * Save a vocabulary.
     *
     * @param vocabularyDTO the entity to save
     * @return the persisted entity
     */
    VocabularyDTO save(VocabularyDTO vocabularyDTO);

    /**
     * Get all the vocabularies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VocabularyDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vocabulary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    VocabularyDTO findOne(Long id);

    /**
     * Delete the "id" vocabulary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
