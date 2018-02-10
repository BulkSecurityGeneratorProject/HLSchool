package com.hl.service.impl;

import com.hl.service.GiftService;
import com.hl.domain.Gift;
import com.hl.repository.GiftRepository;
import com.hl.repository.search.GiftSearchRepository;
import com.hl.service.dto.GiftDTO;
import com.hl.service.mapper.GiftMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Gift.
 */
@Service
@Transactional
public class GiftServiceImpl implements GiftService {

    private final Logger log = LoggerFactory.getLogger(GiftServiceImpl.class);

    private final GiftRepository giftRepository;

    private final GiftMapper giftMapper;

    private final GiftSearchRepository giftSearchRepository;

    public GiftServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper, GiftSearchRepository giftSearchRepository) {
        this.giftRepository = giftRepository;
        this.giftMapper = giftMapper;
        this.giftSearchRepository = giftSearchRepository;
    }

    /**
     * Save a gift.
     *
     * @param giftDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GiftDTO save(GiftDTO giftDTO) {
        log.debug("Request to save Gift : {}", giftDTO);
        Gift gift = giftMapper.toEntity(giftDTO);
        gift = giftRepository.save(gift);
        GiftDTO result = giftMapper.toDto(gift);
        giftSearchRepository.save(gift);
        return result;
    }

    /**
     * Get all the gifts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GiftDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gifts");
        return giftRepository.findAll(pageable)
            .map(giftMapper::toDto);
    }

    /**
     * Get one gift by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GiftDTO findOne(Long id) {
        log.debug("Request to get Gift : {}", id);
        Gift gift = giftRepository.findOne(id);
        return giftMapper.toDto(gift);
    }

    /**
     * Delete the gift by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gift : {}", id);
        giftRepository.delete(id);
        giftSearchRepository.delete(id);
    }

    /**
     * Search for the gift corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GiftDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Gifts for query {}", query);
        Page<Gift> result = giftSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(giftMapper::toDto);
    }
}
