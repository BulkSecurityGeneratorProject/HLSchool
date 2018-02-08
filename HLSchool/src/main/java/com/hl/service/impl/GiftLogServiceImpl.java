package com.hl.service.impl;

import com.hl.service.GiftLogService;
import com.hl.domain.GiftLog;
import com.hl.repository.GiftLogRepository;
import com.hl.service.dto.GiftLogDTO;
import com.hl.service.mapper.GiftLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing GiftLog.
 */
@Service
@Transactional
public class GiftLogServiceImpl implements GiftLogService {

    private final Logger log = LoggerFactory.getLogger(GiftLogServiceImpl.class);

    private final GiftLogRepository giftLogRepository;

    private final GiftLogMapper giftLogMapper;

    public GiftLogServiceImpl(GiftLogRepository giftLogRepository, GiftLogMapper giftLogMapper) {
        this.giftLogRepository = giftLogRepository;
        this.giftLogMapper = giftLogMapper;
    }

    /**
     * Save a giftLog.
     *
     * @param giftLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GiftLogDTO save(GiftLogDTO giftLogDTO) {
        log.debug("Request to save GiftLog : {}", giftLogDTO);
        GiftLog giftLog = giftLogMapper.toEntity(giftLogDTO);
        giftLog = giftLogRepository.save(giftLog);
        return giftLogMapper.toDto(giftLog);
    }

    /**
     * Get all the giftLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GiftLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GiftLogs");
        return giftLogRepository.findAll(pageable)
            .map(giftLogMapper::toDto);
    }

    /**
     * Get one giftLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GiftLogDTO findOne(Long id) {
        log.debug("Request to get GiftLog : {}", id);
        GiftLog giftLog = giftLogRepository.findOne(id);
        return giftLogMapper.toDto(giftLog);
    }

    /**
     * Delete the giftLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiftLog : {}", id);
        giftLogRepository.delete(id);
    }
}
