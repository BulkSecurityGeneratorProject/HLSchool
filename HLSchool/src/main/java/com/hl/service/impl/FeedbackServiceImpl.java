package com.hl.service.impl;

import com.hl.service.FeedbackService;
import com.hl.domain.Feedback;
import com.hl.repository.FeedbackRepository;
import com.hl.service.dto.FeedbackDTO;
import com.hl.service.mapper.FeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Feedback.
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * Save a feedback.
     *
     * @param feedbackDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        log.debug("Request to save Feedback : {}", feedbackDTO);
        Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
        feedback = feedbackRepository.save(feedback);
        return feedbackMapper.toDto(feedback);
    }

    /**
     * Get all the feedbacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeedbackDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Feedbacks");
        return feedbackRepository.findAll(pageable)
            .map(feedbackMapper::toDto);
    }

    /**
     * Get one feedback by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FeedbackDTO findOne(Long id) {
        log.debug("Request to get Feedback : {}", id);
        Feedback feedback = feedbackRepository.findOne(id);
        return feedbackMapper.toDto(feedback);
    }

    /**
     * Delete the feedback by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Feedback : {}", id);
        feedbackRepository.delete(id);
    }
}
