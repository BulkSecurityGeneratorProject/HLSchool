package com.hl.repository;

import com.hl.domain.Lesson;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Lesson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select l from Lesson l where l.course.id = ?1")
    List<Lesson> findLessonsByCourseId(Long id);
}
