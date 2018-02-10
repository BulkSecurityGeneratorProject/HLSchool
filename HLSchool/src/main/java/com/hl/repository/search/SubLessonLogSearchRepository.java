package com.hl.repository.search;

import com.hl.domain.SubLessonLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SubLessonLog entity.
 */
public interface SubLessonLogSearchRepository extends ElasticsearchRepository<SubLessonLog, Long> {
}
