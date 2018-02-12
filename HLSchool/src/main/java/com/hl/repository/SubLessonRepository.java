package com.hl.repository;

import com.hl.domain.SubLesson;
import com.hl.domain.SubLessonLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the SubLesson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubLessonRepository extends JpaRepository<SubLesson, Long> {
    @Query("select sub_lesson from SubLesson sub_lesson where sub_lesson.lesson.id = ?1")
    Page<SubLesson> findSublessonsByLessonId(Long id, Pageable pageable);
}
