package com.hl.repository.search;

import com.hl.domain.CourseLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CourseLog entity.
 */
public interface CourseLogSearchRepository extends ElasticsearchRepository<CourseLog, Long> {
}
