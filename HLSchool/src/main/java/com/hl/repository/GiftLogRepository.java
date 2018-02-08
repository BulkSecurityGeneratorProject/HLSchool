package com.hl.repository;

import com.hl.domain.GiftLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the GiftLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiftLogRepository extends JpaRepository<GiftLog, Long> {

    @Query("select gift_log from GiftLog gift_log where gift_log.user.login = ?#{principal.username}")
    List<GiftLog> findByUserIsCurrentUser();

}
