package com.hl.service;

import com.hl.service.dto.SubLessonLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing SubLessonLog.
 */
public interface SubLessonLogService {

    /**
     * Save a subLessonLog.
     *
     * @param subLessonLogDTO the entity to save
     * @return the persisted entity
     */
    SubLessonLogDTO save(SubLessonLogDTO subLessonLogDTO);

    /**
     * Get all the subLessonLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SubLessonLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" subLessonLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SubLessonLogDTO findOne(Long id);

    /**
     * Delete the "id" subLessonLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the subLessonLog corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SubLessonLogDTO> search(String query, Pageable pageable);
}
