package com.hl.service.impl;

import com.hl.service.ConfigService;
import com.hl.domain.Config;
import com.hl.repository.ConfigRepository;
import com.hl.service.dto.ConfigDTO;
import com.hl.service.mapper.ConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Config.
 */
@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    private final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private final ConfigRepository configRepository;

    private final ConfigMapper configMapper;

    public ConfigServiceImpl(ConfigRepository configRepository, ConfigMapper configMapper) {
        this.configRepository = configRepository;
        this.configMapper = configMapper;
    }

    /**
     * Save a config.
     *
     * @param configDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConfigDTO save(ConfigDTO configDTO) {
        log.debug("Request to save Config : {}", configDTO);
        Config config = configMapper.toEntity(configDTO);
        config = configRepository.save(config);
        return configMapper.toDto(config);
    }

    /**
     * Get all the configs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Configs");
        return configRepository.findAll(pageable)
            .map(configMapper::toDto);
    }

    /**
     * Get one config by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConfigDTO findOne(Long id) {
        log.debug("Request to get Config : {}", id);
        Config config = configRepository.findOne(id);
        return configMapper.toDto(config);
    }

    /**
     * Delete the config by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Config : {}", id);
        configRepository.delete(id);
    }
}
