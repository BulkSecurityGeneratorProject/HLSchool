package com.hl.service;

import com.hl.service.dto.UserLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UserLog.
 */
public interface UserLogService {

    /**
     * Save a userLog.
     *
     * @param userLogDTO the entity to save
     * @return the persisted entity
     */
    UserLogDTO save(UserLogDTO userLogDTO);

    /**
     * Get all the userLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserLogDTO findOne(Long id);

    /**
     * Delete the "id" userLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the userLog corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserLogDTO> search(String query, Pageable pageable);
}
