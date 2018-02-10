package com.hl.repository;

import com.hl.domain.SubLessonLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the SubLessonLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubLessonLogRepository extends JpaRepository<SubLessonLog, Long> {

    @Query("select sub_lesson_log from SubLessonLog sub_lesson_log where sub_lesson_log.user.login = ?#{principal.username}")
    List<SubLessonLog> findByUserIsCurrentUser();

}
