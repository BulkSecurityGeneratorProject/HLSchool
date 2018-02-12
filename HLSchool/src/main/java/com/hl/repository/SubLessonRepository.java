package com.hl.repository;

import com.hl.domain.Lesson;
import com.hl.domain.SubLesson;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the SubLesson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubLessonRepository extends JpaRepository<SubLesson, Long> {

    @Query("select sl from SubLesson sl where sl.rawData = ?1")
    List<SubLesson> findSublessonsByLessonId(Long id);
}
