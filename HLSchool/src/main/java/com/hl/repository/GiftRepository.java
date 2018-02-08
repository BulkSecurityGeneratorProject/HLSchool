package com.hl.repository;

import com.hl.domain.Gift;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Gift entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

}
