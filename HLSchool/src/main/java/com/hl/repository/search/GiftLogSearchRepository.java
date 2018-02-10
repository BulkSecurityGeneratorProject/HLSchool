package com.hl.repository.search;

import com.hl.domain.GiftLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the GiftLog entity.
 */
public interface GiftLogSearchRepository extends ElasticsearchRepository<GiftLog, Long> {
}
