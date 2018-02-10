package com.hl.service.impl;

import com.hl.service.SubLessonLogService;
import com.hl.domain.SubLessonLog;
import com.hl.repository.SubLessonLogRepository;
import com.hl.repository.search.SubLessonLogSearchRepository;
import com.hl.service.dto.SubLessonLogDTO;
import com.hl.service.mapper.SubLessonLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SubLessonLog.
 */
@Service
@Transactional
public class SubLessonLogServiceImpl implements SubLessonLogService {

    private final Logger log = LoggerFactory.getLogger(SubLessonLogServiceImpl.class);

    private final SubLessonLogRepository subLessonLogRepository;

    private final SubLessonLogMapper subLessonLogMapper;

    private final SubLessonLogSearchRepository subLessonLogSearchRepository;

    public SubLessonLogServiceImpl(SubLessonLogRepository subLessonLogRepository, SubLessonLogMapper subLessonLogMapper, SubLessonLogSearchRepository subLessonLogSearchRepository) {
        this.subLessonLogRepository = subLessonLogRepository;
        this.subLessonLogMapper = subLessonLogMapper;
        this.subLessonLogSearchRepository = subLessonLogSearchRepository;
    }

    /**
     * Save a subLessonLog.
     *
     * @param subLessonLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SubLessonLogDTO save(SubLessonLogDTO subLessonLogDTO) {
        log.debug("Request to save SubLessonLog : {}", subLessonLogDTO);
        SubLessonLog subLessonLog = subLessonLogMapper.toEntity(subLessonLogDTO);
        subLessonLog = subLessonLogRepository.save(subLessonLog);
        SubLessonLogDTO result = subLessonLogMapper.toDto(subLessonLog);
        subLessonLogSearchRepository.save(subLessonLog);
        return result;
    }

    /**
     * Get all the subLessonLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubLessonLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubLessonLogs");
        return subLessonLogRepository.findAll(pageable)
            .map(subLessonLogMapper::toDto);
    }

    /**
     * Get one subLessonLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SubLessonLogDTO findOne(Long id) {
        log.debug("Request to get SubLessonLog : {}", id);
        SubLessonLog subLessonLog = subLessonLogRepository.findOne(id);
        return subLessonLogMapper.toDto(subLessonLog);
    }

    /**
     * Delete the subLessonLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubLessonLog : {}", id);
        subLessonLogRepository.delete(id);
        subLessonLogSearchRepository.delete(id);
    }

    /**
     * Search for the subLessonLog corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubLessonLogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SubLessonLogs for query {}", query);
        Page<SubLessonLog> result = subLessonLogSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(subLessonLogMapper::toDto);
    }
}
