package com.hl.repository;

import com.hl.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Question entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select question from Question question where question.subLesson.id = ?1")
    Page<Question> findAllFullInfoBySubLessonId(Long id, Pageable pageable);
}
