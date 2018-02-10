package com.hl.repository;

import com.hl.domain.SubLesson;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SubLesson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubLessonRepository extends JpaRepository<SubLesson, Long> {

}
