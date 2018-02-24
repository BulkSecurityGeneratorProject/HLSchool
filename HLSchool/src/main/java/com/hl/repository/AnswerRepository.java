package com.hl.repository;

import com.hl.domain.Answer;
import com.hl.service.dto.AnswerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Answer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select answer from Answer answer where answer.question.id = ?1")
    Page<Answer> findAllAnswersByQuestion(Long id, Pageable pageable);
}
