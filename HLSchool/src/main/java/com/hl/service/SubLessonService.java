package com.hl.service;

import com.hl.domain.SubLesson;
import com.hl.service.dto.SubLessonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing SubLesson.
 */
public interface SubLessonService {

    /**
     * Save a subLesson.
     *
     * @param subLessonDTO the entity to save
     * @return the persisted entity
     */
    SubLessonDTO save(SubLessonDTO subLessonDTO);

    /**
     * Get all the subLessons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SubLessonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" subLesson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SubLessonDTO findOne(Long id);

    /**
     * Delete the "id" subLesson.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the subLesson corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SubLessonDTO> search(String query, Pageable pageable);
    List<SubLesson> findSublessonsByLessonId(Long id);
}
