package com.hl.service.impl;

import com.hl.service.AnswerService;
import com.hl.domain.Answer;
import com.hl.repository.AnswerRepository;
import com.hl.repository.search.AnswerSearchRepository;
import com.hl.service.dto.AnswerDTO;
import com.hl.service.mapper.AnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Answer.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    private final AnswerSearchRepository answerSearchRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerMapper answerMapper, AnswerSearchRepository answerSearchRepository) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.answerSearchRepository = answerSearchRepository;
    }

    /**
     * Save a answer.
     *
     * @param answerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AnswerDTO save(AnswerDTO answerDTO) {
        log.debug("Request to save Answer : {}", answerDTO);
        Answer answer = answerMapper.toEntity(answerDTO);
        answer = answerRepository.save(answer);
        AnswerDTO result = answerMapper.toDto(answer);
        answerSearchRepository.save(answer);
        return result;
    }

    /**
     * Get all the answers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AnswerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Answers");
        return answerRepository.findAll(pageable)
            .map(answerMapper::toDto);
    }

    /**
     * Get one answer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AnswerDTO findOne(Long id) {
        log.debug("Request to get Answer : {}", id);
        Answer answer = answerRepository.findOne(id);
        return answerMapper.toDto(answer);
    }

    /**
     * Delete the answer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Answer : {}", id);
        answerRepository.delete(id);
        answerSearchRepository.delete(id);
    }

    /**
     * Search for the answer corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AnswerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Answers for query {}", query);
        Page<Answer> result = answerSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(answerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnswerDTO> findAllAnswersByQuestion(Long id, Pageable pageable){
        log.debug("Request to get all Answers");
        return answerRepository.findAllAnswersByQuestion(id, pageable)
            .map(answerMapper::toDto);
    }

    public Page<Answer> findAllAnswersFullInfoByQuestion(Long id, Pageable pageable){
        return answerRepository.findAllAnswersByQuestion(id, pageable);
    }
}
