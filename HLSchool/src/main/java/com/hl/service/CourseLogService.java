package com.hl.service;

import com.hl.service.dto.CourseLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CourseLog.
 */
public interface CourseLogService {

    /**
     * Save a courseLog.
     *
     * @param courseLogDTO the entity to save
     * @return the persisted entity
     */
    CourseLogDTO save(CourseLogDTO courseLogDTO);

    /**
     * Get all the courseLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" courseLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CourseLogDTO findOne(Long id);

    /**
     * Delete the "id" courseLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the courseLog corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseLogDTO> search(String query, Pageable pageable);
}
