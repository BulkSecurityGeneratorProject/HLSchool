package com.hl.service;

import com.hl.service.dto.LessonLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing LessonLog.
 */
public interface LessonLogService {

    /**
     * Save a lessonLog.
     *
     * @param lessonLogDTO the entity to save
     * @return the persisted entity
     */
    LessonLogDTO save(LessonLogDTO lessonLogDTO);

    /**
     * Get all the lessonLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LessonLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" lessonLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    LessonLogDTO findOne(Long id);

    /**
     * Delete the "id" lessonLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the lessonLog corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LessonLogDTO> search(String query, Pageable pageable);
}
