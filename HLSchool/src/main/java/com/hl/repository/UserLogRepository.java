package com.hl.repository;

import com.hl.domain.UserLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the UserLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

    @Query("select user_log from UserLog user_log where user_log.user.login = ?#{principal.username}")
    List<UserLog> findByUserIsCurrentUser();

}
