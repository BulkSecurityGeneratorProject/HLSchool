package com.hl.repository.search;

import com.hl.domain.Vocabulary;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Vocabulary entity.
 */
public interface VocabularySearchRepository extends ElasticsearchRepository<Vocabulary, Long> {
}
