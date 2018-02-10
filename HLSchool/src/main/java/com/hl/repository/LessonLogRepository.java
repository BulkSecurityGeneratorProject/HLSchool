package com.hl.repository;

import com.hl.domain.LessonLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the LessonLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {

    @Query("select lesson_log from LessonLog lesson_log where lesson_log.user.login = ?#{principal.username}")
    List<LessonLog> findByUserIsCurrentUser();

}
