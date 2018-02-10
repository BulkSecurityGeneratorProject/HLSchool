package com.hl.service.impl;

import com.hl.service.LessonLogService;
import com.hl.domain.LessonLog;
import com.hl.repository.LessonLogRepository;
import com.hl.repository.search.LessonLogSearchRepository;
import com.hl.service.dto.LessonLogDTO;
import com.hl.service.mapper.LessonLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing LessonLog.
 */
@Service
@Transactional
public class LessonLogServiceImpl implements LessonLogService {

    private final Logger log = LoggerFactory.getLogger(LessonLogServiceImpl.class);

    private final LessonLogRepository lessonLogRepository;

    private final LessonLogMapper lessonLogMapper;

    private final LessonLogSearchRepository lessonLogSearchRepository;

    public LessonLogServiceImpl(LessonLogRepository lessonLogRepository, LessonLogMapper lessonLogMapper, LessonLogSearchRepository lessonLogSearchRepository) {
        this.lessonLogRepository = lessonLogRepository;
        this.lessonLogMapper = lessonLogMapper;
        this.lessonLogSearchRepository = lessonLogSearchRepository;
    }

    /**
     * Save a lessonLog.
     *
     * @param lessonLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LessonLogDTO save(LessonLogDTO lessonLogDTO) {
        log.debug("Request to save LessonLog : {}", lessonLogDTO);
        LessonLog lessonLog = lessonLogMapper.toEntity(lessonLogDTO);
        lessonLog = lessonLogRepository.save(lessonLog);
        LessonLogDTO result = lessonLogMapper.toDto(lessonLog);
        lessonLogSearchRepository.save(lessonLog);
        return result;
    }

    /**
     * Get all the lessonLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LessonLogs");
        return lessonLogRepository.findAll(pageable)
            .map(lessonLogMapper::toDto);
    }

    /**
     * Get one lessonLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LessonLogDTO findOne(Long id) {
        log.debug("Request to get LessonLog : {}", id);
        LessonLog lessonLog = lessonLogRepository.findOne(id);
        return lessonLogMapper.toDto(lessonLog);
    }

    /**
     * Delete the lessonLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LessonLog : {}", id);
        lessonLogRepository.delete(id);
        lessonLogSearchRepository.delete(id);
    }

    /**
     * Search for the lessonLog corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonLogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of LessonLogs for query {}", query);
        Page<LessonLog> result = lessonLogSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(lessonLogMapper::toDto);
    }
}
