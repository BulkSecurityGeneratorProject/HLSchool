package com.hl.service.impl;

import com.hl.service.CourseLogService;
import com.hl.domain.CourseLog;
import com.hl.repository.CourseLogRepository;
import com.hl.repository.search.CourseLogSearchRepository;
import com.hl.service.UserService;
import com.hl.service.dto.CourseLogDTO;
import com.hl.service.mapper.CourseLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CourseLog.
 */
@Service
@Transactional
public class CourseLogServiceImpl implements CourseLogService {

    private final Logger log = LoggerFactory.getLogger(CourseLogServiceImpl.class);

    private final CourseLogRepository courseLogRepository;

    private final CourseLogMapper courseLogMapper;

    private final CourseLogSearchRepository courseLogSearchRepository;


    public CourseLogServiceImpl(CourseLogRepository courseLogRepository, CourseLogMapper courseLogMapper, CourseLogSearchRepository courseLogSearchRepository) {
        this.courseLogRepository = courseLogRepository;
        this.courseLogMapper = courseLogMapper;
        this.courseLogSearchRepository = courseLogSearchRepository;
    }

    /**
     * Save a courseLog.
     *
     * @param courseLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseLogDTO save(CourseLogDTO courseLogDTO) {
        log.debug("Request to save CourseLog : {}", courseLogDTO);
        CourseLog courseLog = courseLogMapper.toEntity(courseLogDTO);
        courseLog = courseLogRepository.save(courseLog);
        CourseLogDTO result = courseLogMapper.toDto(courseLog);
        courseLogSearchRepository.save(courseLog);
        return result;
    }

    /**
     * Get all the courseLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLogs");
        return courseLogRepository.findAll(pageable)
            .map(courseLogMapper::toDto);
    }

    /**
     * Get one courseLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CourseLogDTO findOne(Long id) {
        log.debug("Request to get CourseLog : {}", id);
        CourseLog courseLog = courseLogRepository.findOne(id);
        return courseLogMapper.toDto(courseLog);
    }

    /**
     * Delete the courseLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseLog : {}", id);
        courseLogRepository.delete(id);
        courseLogSearchRepository.delete(id);
    }

    /**
     * Search for the courseLog corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseLogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CourseLogs for query {}", query);
        Page<CourseLog> result = courseLogSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(courseLogMapper::toDto);
    }
}
