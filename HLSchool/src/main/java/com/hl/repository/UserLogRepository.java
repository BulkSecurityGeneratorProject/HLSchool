package com.hl.repository;

import com.hl.domain.UserLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

}
