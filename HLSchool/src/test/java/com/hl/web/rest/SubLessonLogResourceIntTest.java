package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.SubLessonLog;
import com.hl.repository.SubLessonLogRepository;
import com.hl.service.SubLessonLogService;
import com.hl.repository.search.SubLessonLogSearchRepository;
import com.hl.service.dto.SubLessonLogDTO;
import com.hl.service.mapper.SubLessonLogMapper;
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
import java.util.List;

import static com.hl.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SubLessonLogResource REST controller.
 *
 * @see SubLessonLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class SubLessonLogResourceIntTest {

    private static final Boolean DEFAULT_COMPLETE = false;
    private static final Boolean UPDATED_COMPLETE = true;

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private SubLessonLogRepository subLessonLogRepository;

    @Autowired
    private SubLessonLogMapper subLessonLogMapper;

    @Autowired
    private SubLessonLogService subLessonLogService;

    @Autowired
    private SubLessonLogSearchRepository subLessonLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubLessonLogMockMvc;

    private SubLessonLog subLessonLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubLessonLogResource subLessonLogResource = new SubLessonLogResource(subLessonLogService);
        this.restSubLessonLogMockMvc = MockMvcBuilders.standaloneSetup(subLessonLogResource)
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
    public static SubLessonLog createEntity(EntityManager em) {
        SubLessonLog subLessonLog = new SubLessonLog()
            .complete(DEFAULT_COMPLETE)
            .rawData(DEFAULT_RAW_DATA);
        return subLessonLog;
    }

    @Before
    public void initTest() {
        subLessonLogSearchRepository.deleteAll();
        subLessonLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubLessonLog() throws Exception {
        int databaseSizeBeforeCreate = subLessonLogRepository.findAll().size();

        // Create the SubLessonLog
        SubLessonLogDTO subLessonLogDTO = subLessonLogMapper.toDto(subLessonLog);
        restSubLessonLogMockMvc.perform(post("/api/sub-lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SubLessonLog in the database
        List<SubLessonLog> subLessonLogList = subLessonLogRepository.findAll();
        assertThat(subLessonLogList).hasSize(databaseSizeBeforeCreate + 1);
        SubLessonLog testSubLessonLog = subLessonLogList.get(subLessonLogList.size() - 1);
        assertThat(testSubLessonLog.isComplete()).isEqualTo(DEFAULT_COMPLETE);
        assertThat(testSubLessonLog.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the SubLessonLog in Elasticsearch
        SubLessonLog subLessonLogEs = subLessonLogSearchRepository.findOne(testSubLessonLog.getId());
        assertThat(subLessonLogEs).isEqualToIgnoringGivenFields(testSubLessonLog);
    }

    @Test
    @Transactional
    public void createSubLessonLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subLessonLogRepository.findAll().size();

        // Create the SubLessonLog with an existing ID
        subLessonLog.setId(1L);
        SubLessonLogDTO subLessonLogDTO = subLessonLogMapper.toDto(subLessonLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubLessonLogMockMvc.perform(post("/api/sub-lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubLessonLog in the database
        List<SubLessonLog> subLessonLogList = subLessonLogRepository.findAll();
        assertThat(subLessonLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSubLessonLogs() throws Exception {
        // Initialize the database
        subLessonLogRepository.saveAndFlush(subLessonLog);

        // Get all the subLessonLogList
        restSubLessonLogMockMvc.perform(get("/api/sub-lesson-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subLessonLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getSubLessonLog() throws Exception {
        // Initialize the database
        subLessonLogRepository.saveAndFlush(subLessonLog);

        // Get the subLessonLog
        restSubLessonLogMockMvc.perform(get("/api/sub-lesson-logs/{id}", subLessonLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subLessonLog.getId().intValue()))
            .andExpect(jsonPath("$.complete").value(DEFAULT_COMPLETE.booleanValue()))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSubLessonLog() throws Exception {
        // Get the subLessonLog
        restSubLessonLogMockMvc.perform(get("/api/sub-lesson-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubLessonLog() throws Exception {
        // Initialize the database
        subLessonLogRepository.saveAndFlush(subLessonLog);
        subLessonLogSearchRepository.save(subLessonLog);
        int databaseSizeBeforeUpdate = subLessonLogRepository.findAll().size();

        // Update the subLessonLog
        SubLessonLog updatedSubLessonLog = subLessonLogRepository.findOne(subLessonLog.getId());
        // Disconnect from session so that the updates on updatedSubLessonLog are not directly saved in db
        em.detach(updatedSubLessonLog);
        updatedSubLessonLog
            .complete(UPDATED_COMPLETE)
            .rawData(UPDATED_RAW_DATA);
        SubLessonLogDTO subLessonLogDTO = subLessonLogMapper.toDto(updatedSubLessonLog);

        restSubLessonLogMockMvc.perform(put("/api/sub-lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonLogDTO)))
            .andExpect(status().isOk());

        // Validate the SubLessonLog in the database
        List<SubLessonLog> subLessonLogList = subLessonLogRepository.findAll();
        assertThat(subLessonLogList).hasSize(databaseSizeBeforeUpdate);
        SubLessonLog testSubLessonLog = subLessonLogList.get(subLessonLogList.size() - 1);
        assertThat(testSubLessonLog.isComplete()).isEqualTo(UPDATED_COMPLETE);
        assertThat(testSubLessonLog.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the SubLessonLog in Elasticsearch
        SubLessonLog subLessonLogEs = subLessonLogSearchRepository.findOne(testSubLessonLog.getId());
        assertThat(subLessonLogEs).isEqualToIgnoringGivenFields(testSubLessonLog);
    }

    @Test
    @Transactional
    public void updateNonExistingSubLessonLog() throws Exception {
        int databaseSizeBeforeUpdate = subLessonLogRepository.findAll().size();

        // Create the SubLessonLog
        SubLessonLogDTO subLessonLogDTO = subLessonLogMapper.toDto(subLessonLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSubLessonLogMockMvc.perform(put("/api/sub-lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SubLessonLog in the database
        List<SubLessonLog> subLessonLogList = subLessonLogRepository.findAll();
        assertThat(subLessonLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSubLessonLog() throws Exception {
        // Initialize the database
        subLessonLogRepository.saveAndFlush(subLessonLog);
        subLessonLogSearchRepository.save(subLessonLog);
        int databaseSizeBeforeDelete = subLessonLogRepository.findAll().size();

        // Get the subLessonLog
        restSubLessonLogMockMvc.perform(delete("/api/sub-lesson-logs/{id}", subLessonLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean subLessonLogExistsInEs = subLessonLogSearchRepository.exists(subLessonLog.getId());
        assertThat(subLessonLogExistsInEs).isFalse();

        // Validate the database is empty
        List<SubLessonLog> subLessonLogList = subLessonLogRepository.findAll();
        assertThat(subLessonLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSubLessonLog() throws Exception {
        // Initialize the database
        subLessonLogRepository.saveAndFlush(subLessonLog);
        subLessonLogSearchRepository.save(subLessonLog);

        // Search the subLessonLog
        restSubLessonLogMockMvc.perform(get("/api/_search/sub-lesson-logs?query=id:" + subLessonLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subLessonLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubLessonLog.class);
        SubLessonLog subLessonLog1 = new SubLessonLog();
        subLessonLog1.setId(1L);
        SubLessonLog subLessonLog2 = new SubLessonLog();
        subLessonLog2.setId(subLessonLog1.getId());
        assertThat(subLessonLog1).isEqualTo(subLessonLog2);
        subLessonLog2.setId(2L);
        assertThat(subLessonLog1).isNotEqualTo(subLessonLog2);
        subLessonLog1.setId(null);
        assertThat(subLessonLog1).isNotEqualTo(subLessonLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubLessonLogDTO.class);
        SubLessonLogDTO subLessonLogDTO1 = new SubLessonLogDTO();
        subLessonLogDTO1.setId(1L);
        SubLessonLogDTO subLessonLogDTO2 = new SubLessonLogDTO();
        assertThat(subLessonLogDTO1).isNotEqualTo(subLessonLogDTO2);
        subLessonLogDTO2.setId(subLessonLogDTO1.getId());
        assertThat(subLessonLogDTO1).isEqualTo(subLessonLogDTO2);
        subLessonLogDTO2.setId(2L);
        assertThat(subLessonLogDTO1).isNotEqualTo(subLessonLogDTO2);
        subLessonLogDTO1.setId(null);
        assertThat(subLessonLogDTO1).isNotEqualTo(subLessonLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(subLessonLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(subLessonLogMapper.fromId(null)).isNull();
    }
}
