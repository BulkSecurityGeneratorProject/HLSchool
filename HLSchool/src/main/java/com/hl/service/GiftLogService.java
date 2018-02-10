package com.hl.service;

import com.hl.service.dto.GiftLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing GiftLog.
 */
public interface GiftLogService {

    /**
     * Save a giftLog.
     *
     * @param giftLogDTO the entity to save
     * @return the persisted entity
     */
    GiftLogDTO save(GiftLogDTO giftLogDTO);

    /**
     * Get all the giftLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GiftLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" giftLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    GiftLogDTO findOne(Long id);

    /**
     * Delete the "id" giftLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the giftLog corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GiftLogDTO> search(String query, Pageable pageable);
}
