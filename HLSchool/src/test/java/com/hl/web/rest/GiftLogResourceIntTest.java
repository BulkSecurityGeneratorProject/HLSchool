package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.GiftLog;
import com.hl.repository.GiftLogRepository;
import com.hl.service.GiftLogService;
import com.hl.repository.search.GiftLogSearchRepository;
import com.hl.service.dto.GiftLogDTO;
import com.hl.service.mapper.GiftLogMapper;
import com.hl.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.hl.web.rest.TestUtil.sameInstant;
import static com.hl.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GiftLogResource REST controller.
 *
 * @see GiftLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class GiftLogResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private GiftLogRepository giftLogRepository;

    @Autowired
    private GiftLogMapper giftLogMapper;

    @Autowired
    private GiftLogService giftLogService;

    @Autowired
    private GiftLogSearchRepository giftLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGiftLogMockMvc;

    private GiftLog giftLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GiftLogResource giftLogResource = new GiftLogResource(giftLogService);
        this.restGiftLogMockMvc = MockMvcBuilders.standaloneSetup(giftLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftLog createEntity(EntityManager em) {
        GiftLog giftLog = new GiftLog()
            .createDate(DEFAULT_CREATE_DATE)
            .rawData(DEFAULT_RAW_DATA);
        return giftLog;
    }

    @Before
    public void initTest() {
        giftLogSearchRepository.deleteAll();
        giftLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createGiftLog() throws Exception {
        int databaseSizeBeforeCreate = giftLogRepository.findAll().size();

        // Create the GiftLog
        GiftLogDTO giftLogDTO = giftLogMapper.toDto(giftLog);
        restGiftLogMockMvc.perform(post("/api/gift-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftLogDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftLog in the database
        List<GiftLog> giftLogList = giftLogRepository.findAll();
        assertThat(giftLogList).hasSize(databaseSizeBeforeCreate + 1);
        GiftLog testGiftLog = giftLogList.get(giftLogList.size() - 1);
        assertThat(testGiftLog.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testGiftLog.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the GiftLog in Elasticsearch
        GiftLog giftLogEs = giftLogSearchRepository.findOne(testGiftLog.getId());
        assertThat(testGiftLog.getCreateDate()).isEqualTo(testGiftLog.getCreateDate());
        assertThat(giftLogEs).isEqualToIgnoringGivenFields(testGiftLog, "createDate");
    }

    @Test
    @Transactional
    public void createGiftLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = giftLogRepository.findAll().size();

        // Create the GiftLog with an existing ID
        giftLog.setId(1L);
        GiftLogDTO giftLogDTO = giftLogMapper.toDto(giftLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftLogMockMvc.perform(post("/api/gift-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GiftLog in the database
        List<GiftLog> giftLogList = giftLogRepository.findAll();
        assertThat(giftLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGiftLogs() throws Exception {
        // Initialize the database
        giftLogRepository.saveAndFlush(giftLog);

        // Get all the giftLogList
        restGiftLogMockMvc.perform(get("/api/gift-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getGiftLog() throws Exception {
        // Initialize the database
        giftLogRepository.saveAndFlush(giftLog);

        // Get the giftLog
        restGiftLogMockMvc.perform(get("/api/gift-logs/{id}", giftLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(giftLog.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGiftLog() throws Exception {
        // Get the giftLog
        restGiftLogMockMvc.perform(get("/api/gift-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGiftLog() throws Exception {
        // Initialize the database
        giftLogRepository.saveAndFlush(giftLog);
        giftLogSearchRepository.save(giftLog);
        int databaseSizeBeforeUpdate = giftLogRepository.findAll().size();

        // Update the giftLog
        GiftLog updatedGiftLog = giftLogRepository.findOne(giftLog.getId());
        // Disconnect from session so that the updates on updatedGiftLog are not directly saved in db
        em.detach(updatedGiftLog);
        updatedGiftLog
            .createDate(UPDATED_CREATE_DATE)
            .rawData(UPDATED_RAW_DATA);
        GiftLogDTO giftLogDTO = giftLogMapper.toDto(updatedGiftLog);

        restGiftLogMockMvc.perform(put("/api/gift-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftLogDTO)))
            .andExpect(status().isOk());

        // Validate the GiftLog in the database
        List<GiftLog> giftLogList = giftLogRepository.findAll();
        assertThat(giftLogList).hasSize(databaseSizeBeforeUpdate);
        GiftLog testGiftLog = giftLogList.get(giftLogList.size() - 1);
        assertThat(testGiftLog.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testGiftLog.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the GiftLog in Elasticsearch
        GiftLog giftLogEs = giftLogSearchRepository.findOne(testGiftLog.getId());
        assertThat(testGiftLog.getCreateDate()).isEqualTo(testGiftLog.getCreateDate());
        assertThat(giftLogEs).isEqualToIgnoringGivenFields(testGiftLog, "createDate");
    }

    @Test
    @Transactional
    public void updateNonExistingGiftLog() throws Exception {
        int databaseSizeBeforeUpdate = giftLogRepository.findAll().size();

        // Create the GiftLog
        GiftLogDTO giftLogDTO = giftLogMapper.toDto(giftLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGiftLogMockMvc.perform(put("/api/gift-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftLogDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftLog in the database
        List<GiftLog> giftLogList = giftLogRepository.findAll();
        assertThat(giftLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGiftLog() throws Exception {
        // Initialize the database
        giftLogRepository.saveAndFlush(giftLog);
        giftLogSearchRepository.save(giftLog);
        int databaseSizeBeforeDelete = giftLogRepository.findAll().size();

        // Get the giftLog
        restGiftLogMockMvc.perform(delete("/api/gift-logs/{id}", giftLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean giftLogExistsInEs = giftLogSearchRepository.exists(giftLog.getId());
        assertThat(giftLogExistsInEs).isFalse();

        // Validate the database is empty
        List<GiftLog> giftLogList = giftLogRepository.findAll();
        assertThat(giftLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchGiftLog() throws Exception {
        // Initialize the database
        giftLogRepository.saveAndFlush(giftLog);
        giftLogSearchRepository.save(giftLog);

        // Search the giftLog
        restGiftLogMockMvc.perform(get("/api/_search/gift-logs?query=id:" + giftLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftLog.class);
        GiftLog giftLog1 = new GiftLog();
        giftLog1.setId(1L);
        GiftLog giftLog2 = new GiftLog();
        giftLog2.setId(giftLog1.getId());
        assertThat(giftLog1).isEqualTo(giftLog2);
        giftLog2.setId(2L);
        assertThat(giftLog1).isNotEqualTo(giftLog2);
        giftLog1.setId(null);
        assertThat(giftLog1).isNotEqualTo(giftLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftLogDTO.class);
        GiftLogDTO giftLogDTO1 = new GiftLogDTO();
        giftLogDTO1.setId(1L);
        GiftLogDTO giftLogDTO2 = new GiftLogDTO();
        assertThat(giftLogDTO1).isNotEqualTo(giftLogDTO2);
        giftLogDTO2.setId(giftLogDTO1.getId());
        assertThat(giftLogDTO1).isEqualTo(giftLogDTO2);
        giftLogDTO2.setId(2L);
        assertThat(giftLogDTO1).isNotEqualTo(giftLogDTO2);
        giftLogDTO1.setId(null);
        assertThat(giftLogDTO1).isNotEqualTo(giftLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(giftLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(giftLogMapper.fromId(null)).isNull();
    }
}
