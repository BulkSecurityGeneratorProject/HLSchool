package com.hl.repository.search;

import com.hl.domain.LessonLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LessonLog entity.
 */
public interface LessonLogSearchRepository extends ElasticsearchRepository<LessonLog, Long> {
}
