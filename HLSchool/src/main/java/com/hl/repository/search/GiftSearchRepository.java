package com.hl.repository.search;

import com.hl.domain.Gift;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Gift entity.
 */
public interface GiftSearchRepository extends ElasticsearchRepository<Gift, Long> {
}
