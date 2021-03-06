package com.hl.service;

import com.hl.domain.Question;
import com.hl.service.dto.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Question.
 */
public interface QuestionService {

    /**
     * Save a question.
     *
     * @param questionDTO the entity to save
     * @return the persisted entity
     */
    QuestionDTO save(QuestionDTO questionDTO);

    /**
     * Get all the questions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuestionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" question.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuestionDTO findOne(Long id);

    /**
     * Delete the "id" question.
     *
     * @param id the id of the entity
     */

    Question findFullInfoOne(Long id);

    void delete(Long id);

    /**
     * Search for the question corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuestionDTO> search(String query, Pageable pageable);

    Page<Question> findAllFullInfoBySubLessonId(Long id, Pageable pageable);
}
