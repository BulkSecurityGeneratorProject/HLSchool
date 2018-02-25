package com.hl.repository;

import com.hl.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Course entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select course from Course course where course.id in (select log.course.id from CourseLog log where log.user.id = ?1)")
    Page<Course> findAllInLog(Long userID, Pageable pageable);
    @Query("select course from Course course where course.id not in (select log.course.id from CourseLog log where log.user.id = ?1)")
    Page<Course> findAllNotInLog(Long userID, Pageable pageable);
}
