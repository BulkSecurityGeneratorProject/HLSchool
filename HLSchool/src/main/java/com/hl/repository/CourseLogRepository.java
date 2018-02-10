package com.hl.repository;

import com.hl.domain.CourseLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the CourseLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLogRepository extends JpaRepository<CourseLog, Long> {

    @Query("select course_log from CourseLog course_log where course_log.user.login = ?#{principal.username}")
    List<CourseLog> findByUserIsCurrentUser();

}
