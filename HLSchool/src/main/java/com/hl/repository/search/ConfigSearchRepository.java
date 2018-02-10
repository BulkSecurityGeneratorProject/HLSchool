package com.hl.repository.search;

import com.hl.domain.Config;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Config entity.
 */
public interface ConfigSearchRepository extends ElasticsearchRepository<Config, Long> {
}
