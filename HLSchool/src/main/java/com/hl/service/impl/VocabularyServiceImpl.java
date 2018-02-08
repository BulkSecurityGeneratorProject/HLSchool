package com.hl.service.impl;

import com.hl.service.VocabularyService;
import com.hl.domain.Vocabulary;
import com.hl.repository.VocabularyRepository;
import com.hl.service.dto.VocabularyDTO;
import com.hl.service.mapper.VocabularyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Vocabulary.
 */
@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {

    private final Logger log = LoggerFactory.getLogger(VocabularyServiceImpl.class);

    private final VocabularyRepository vocabularyRepository;

    private final VocabularyMapper vocabularyMapper;

    public VocabularyServiceImpl(VocabularyRepository vocabularyRepository, VocabularyMapper vocabularyMapper) {
        this.vocabularyRepository = vocabularyRepository;
        this.vocabularyMapper = vocabularyMapper;
    }

    /**
     * Save a vocabulary.
     *
     * @param vocabularyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VocabularyDTO save(VocabularyDTO vocabularyDTO) {
        log.debug("Request to save Vocabulary : {}", vocabularyDTO);
        Vocabulary vocabulary = vocabularyMapper.toEntity(vocabularyDTO);
        vocabulary = vocabularyRepository.save(vocabulary);
        return vocabularyMapper.toDto(vocabulary);
    }

    /**
     * Get all the vocabularies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vocabularies");
        return vocabularyRepository.findAll(pageable)
            .map(vocabularyMapper::toDto);
    }

    /**
     * Get one vocabulary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VocabularyDTO findOne(Long id) {
        log.debug("Request to get Vocabulary : {}", id);
        Vocabulary vocabulary = vocabularyRepository.findOne(id);
        return vocabularyMapper.toDto(vocabulary);
    }

    /**
     * Delete the vocabulary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vocabulary : {}", id);
        vocabularyRepository.delete(id);
    }
}
