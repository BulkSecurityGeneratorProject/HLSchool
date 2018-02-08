package com.hl.service.impl;

import com.hl.service.UserLogService;
import com.hl.domain.UserLog;
import com.hl.repository.UserLogRepository;
import com.hl.service.dto.UserLogDTO;
import com.hl.service.mapper.UserLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing UserLog.
 */
@Service
@Transactional
public class UserLogServiceImpl implements UserLogService {

    private final Logger log = LoggerFactory.getLogger(UserLogServiceImpl.class);

    private final UserLogRepository userLogRepository;

    private final UserLogMapper userLogMapper;

    public UserLogServiceImpl(UserLogRepository userLogRepository, UserLogMapper userLogMapper) {
        this.userLogRepository = userLogRepository;
        this.userLogMapper = userLogMapper;
    }

    /**
     * Save a userLog.
     *
     * @param userLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserLogDTO save(UserLogDTO userLogDTO) {
        log.debug("Request to save UserLog : {}", userLogDTO);
        UserLog userLog = userLogMapper.toEntity(userLogDTO);
        userLog = userLogRepository.save(userLog);
        return userLogMapper.toDto(userLog);
    }

    /**
     * Get all the userLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserLogs");
        return userLogRepository.findAll(pageable)
            .map(userLogMapper::toDto);
    }

    /**
     * Get one userLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserLogDTO findOne(Long id) {
        log.debug("Request to get UserLog : {}", id);
        UserLog userLog = userLogRepository.findOne(id);
        return userLogMapper.toDto(userLog);
    }

    /**
     * Delete the userLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserLog : {}", id);
        userLogRepository.delete(id);
    }
}
