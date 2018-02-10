package com.hl.repository.search;

import com.hl.domain.UserLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserLog entity.
 */
public interface UserLogSearchRepository extends ElasticsearchRepository<UserLog, Long> {
}
