package com.hl.repository;

import com.hl.domain.Vocabulary;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Vocabulary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

}
