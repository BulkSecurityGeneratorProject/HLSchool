package com.hl.repository.search;

import com.hl.domain.SubLesson;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SubLesson entity.
 */
public interface SubLessonSearchRepository extends ElasticsearchRepository<SubLesson, Long> {
}
