package com.hl.service.impl;

import com.hl.service.SubLessonService;
import com.hl.domain.SubLesson;
import com.hl.repository.SubLessonRepository;
import com.hl.repository.search.SubLessonSearchRepository;
import com.hl.service.dto.SubLessonDTO;
import com.hl.service.mapper.SubLessonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SubLesson.
 */
@Service
@Transactional
public class SubLessonServiceImpl implements SubLessonService {

    private final Logger log = LoggerFactory.getLogger(SubLessonServiceImpl.class);

    private final SubLessonRepository subLessonRepository;

    private final SubLessonMapper subLessonMapper;

    private final SubLessonSearchRepository subLessonSearchRepository;

    public SubLessonServiceImpl(SubLessonRepository subLessonRepository, SubLessonMapper subLessonMapper, SubLessonSearchRepository subLessonSearchRepository) {
        this.subLessonRepository = subLessonRepository;
        this.subLessonMapper = subLessonMapper;
        this.subLessonSearchRepository = subLessonSearchRepository;
    }

    /**
     * Save a subLesson.
     *
     * @param subLessonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SubLessonDTO save(SubLessonDTO subLessonDTO) {
        log.debug("Request to save SubLesson : {}", subLessonDTO);
        SubLesson subLesson = subLessonMapper.toEntity(subLessonDTO);
        subLesson = subLessonRepository.save(subLesson);
        SubLessonDTO result = subLessonMapper.toDto(subLesson);
        subLessonSearchRepository.save(subLesson);
        return result;
    }

    /**
     * Get all the subLessons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubLessonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubLessons");
        return subLessonRepository.findAll(pageable)
            .map(subLessonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubLessonDTO> findSubLessonsByLessonId(Long id, Pageable pageable) {
        log.debug("Request to get all SubLessons");
        return subLessonRepository.findSublessonsByLessonId(id, pageable)
            .map(subLessonMapper::toDto);
    }

    /**
     * Get one subLesson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SubLessonDTO findOne(Long id) {
        log.debug("Request to get SubLesson : {}", id);
        SubLesson subLesson = subLessonRepository.findOne(id);
        return subLessonMapper.toDto(subLesson);
    }

    /**
     * Delete the subLesson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubLesson : {}", id);
        subLessonRepository.delete(id);
        subLessonSearchRepository.delete(id);
    }

    /**
     * Search for the subLesson corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubLessonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SubLessons for query {}", query);
        Page<SubLesson> result = subLessonSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(subLessonMapper::toDto);
    }
}
