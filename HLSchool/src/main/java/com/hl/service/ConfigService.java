package com.hl.service;

import com.hl.service.dto.ConfigDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Config.
 */
public interface ConfigService {

    /**
     * Save a config.
     *
     * @param configDTO the entity to save
     * @return the persisted entity
     */
    ConfigDTO save(ConfigDTO configDTO);

    /**
     * Get all the configs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConfigDTO> findAll(Pageable pageable);

    /**
     * Get the "id" config.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConfigDTO findOne(Long id);

    /**
     * Delete the "id" config.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the config corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConfigDTO> search(String query, Pageable pageable);
}
