package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.LessonLog;
import com.hl.repository.LessonLogRepository;
import com.hl.service.LessonLogService;
import com.hl.repository.search.LessonLogSearchRepository;
import com.hl.service.dto.LessonLogDTO;
import com.hl.service.mapper.LessonLogMapper;
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
 * Test class for the LessonLogResource REST controller.
 *
 * @see LessonLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class LessonLogResourceIntTest {

    private static final Boolean DEFAULT_COMPLETE = false;
    private static final Boolean UPDATED_COMPLETE = true;

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private LessonLogRepository lessonLogRepository;

    @Autowired
    private LessonLogMapper lessonLogMapper;

    @Autowired
    private LessonLogService lessonLogService;

    @Autowired
    private LessonLogSearchRepository lessonLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLessonLogMockMvc;

    private LessonLog lessonLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LessonLogResource lessonLogResource = new LessonLogResource(lessonLogService);
        this.restLessonLogMockMvc = MockMvcBuilders.standaloneSetup(lessonLogResource)
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
    public static LessonLog createEntity(EntityManager em) {
        LessonLog lessonLog = new LessonLog()
            .complete(DEFAULT_COMPLETE)
            .rawData(DEFAULT_RAW_DATA);
        return lessonLog;
    }

    @Before
    public void initTest() {
        lessonLogSearchRepository.deleteAll();
        lessonLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createLessonLog() throws Exception {
        int databaseSizeBeforeCreate = lessonLogRepository.findAll().size();

        // Create the LessonLog
        LessonLogDTO lessonLogDTO = lessonLogMapper.toDto(lessonLog);
        restLessonLogMockMvc.perform(post("/api/lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonLogDTO)))
            .andExpect(status().isCreated());

        // Validate the LessonLog in the database
        List<LessonLog> lessonLogList = lessonLogRepository.findAll();
        assertThat(lessonLogList).hasSize(databaseSizeBeforeCreate + 1);
        LessonLog testLessonLog = lessonLogList.get(lessonLogList.size() - 1);
        assertThat(testLessonLog.isComplete()).isEqualTo(DEFAULT_COMPLETE);
        assertThat(testLessonLog.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the LessonLog in Elasticsearch
        LessonLog lessonLogEs = lessonLogSearchRepository.findOne(testLessonLog.getId());
        assertThat(lessonLogEs).isEqualToIgnoringGivenFields(testLessonLog);
    }

    @Test
    @Transactional
    public void createLessonLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lessonLogRepository.findAll().size();

        // Create the LessonLog with an existing ID
        lessonLog.setId(1L);
        LessonLogDTO lessonLogDTO = lessonLogMapper.toDto(lessonLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLessonLogMockMvc.perform(post("/api/lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LessonLog in the database
        List<LessonLog> lessonLogList = lessonLogRepository.findAll();
        assertThat(lessonLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLessonLogs() throws Exception {
        // Initialize the database
        lessonLogRepository.saveAndFlush(lessonLog);

        // Get all the lessonLogList
        restLessonLogMockMvc.perform(get("/api/lesson-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lessonLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getLessonLog() throws Exception {
        // Initialize the database
        lessonLogRepository.saveAndFlush(lessonLog);

        // Get the lessonLog
        restLessonLogMockMvc.perform(get("/api/lesson-logs/{id}", lessonLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lessonLog.getId().intValue()))
            .andExpect(jsonPath("$.complete").value(DEFAULT_COMPLETE.booleanValue()))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLessonLog() throws Exception {
        // Get the lessonLog
        restLessonLogMockMvc.perform(get("/api/lesson-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLessonLog() throws Exception {
        // Initialize the database
        lessonLogRepository.saveAndFlush(lessonLog);
        lessonLogSearchRepository.save(lessonLog);
        int databaseSizeBeforeUpdate = lessonLogRepository.findAll().size();

        // Update the lessonLog
        LessonLog updatedLessonLog = lessonLogRepository.findOne(lessonLog.getId());
        // Disconnect from session so that the updates on updatedLessonLog are not directly saved in db
        em.detach(updatedLessonLog);
        updatedLessonLog
            .complete(UPDATED_COMPLETE)
            .rawData(UPDATED_RAW_DATA);
        LessonLogDTO lessonLogDTO = lessonLogMapper.toDto(updatedLessonLog);

        restLessonLogMockMvc.perform(put("/api/lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonLogDTO)))
            .andExpect(status().isOk());

        // Validate the LessonLog in the database
        List<LessonLog> lessonLogList = lessonLogRepository.findAll();
        assertThat(lessonLogList).hasSize(databaseSizeBeforeUpdate);
        LessonLog testLessonLog = lessonLogList.get(lessonLogList.size() - 1);
        assertThat(testLessonLog.isComplete()).isEqualTo(UPDATED_COMPLETE);
        assertThat(testLessonLog.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the LessonLog in Elasticsearch
        LessonLog lessonLogEs = lessonLogSearchRepository.findOne(testLessonLog.getId());
        assertThat(lessonLogEs).isEqualToIgnoringGivenFields(testLessonLog);
    }

    @Test
    @Transactional
    public void updateNonExistingLessonLog() throws Exception {
        int databaseSizeBeforeUpdate = lessonLogRepository.findAll().size();

        // Create the LessonLog
        LessonLogDTO lessonLogDTO = lessonLogMapper.toDto(lessonLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLessonLogMockMvc.perform(put("/api/lesson-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonLogDTO)))
            .andExpect(status().isCreated());

        // Validate the LessonLog in the database
        List<LessonLog> lessonLogList = lessonLogRepository.findAll();
        assertThat(lessonLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLessonLog() throws Exception {
        // Initialize the database
        lessonLogRepository.saveAndFlush(lessonLog);
        lessonLogSearchRepository.save(lessonLog);
        int databaseSizeBeforeDelete = lessonLogRepository.findAll().size();

        // Get the lessonLog
        restLessonLogMockMvc.perform(delete("/api/lesson-logs/{id}", lessonLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean lessonLogExistsInEs = lessonLogSearchRepository.exists(lessonLog.getId());
        assertThat(lessonLogExistsInEs).isFalse();

        // Validate the database is empty
        List<LessonLog> lessonLogList = lessonLogRepository.findAll();
        assertThat(lessonLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchLessonLog() throws Exception {
        // Initialize the database
        lessonLogRepository.saveAndFlush(lessonLog);
        lessonLogSearchRepository.save(lessonLog);

        // Search the lessonLog
        restLessonLogMockMvc.perform(get("/api/_search/lesson-logs?query=id:" + lessonLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lessonLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LessonLog.class);
        LessonLog lessonLog1 = new LessonLog();
        lessonLog1.setId(1L);
        LessonLog lessonLog2 = new LessonLog();
        lessonLog2.setId(lessonLog1.getId());
        assertThat(lessonLog1).isEqualTo(lessonLog2);
        lessonLog2.setId(2L);
        assertThat(lessonLog1).isNotEqualTo(lessonLog2);
        lessonLog1.setId(null);
        assertThat(lessonLog1).isNotEqualTo(lessonLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LessonLogDTO.class);
        LessonLogDTO lessonLogDTO1 = new LessonLogDTO();
        lessonLogDTO1.setId(1L);
        LessonLogDTO lessonLogDTO2 = new LessonLogDTO();
        assertThat(lessonLogDTO1).isNotEqualTo(lessonLogDTO2);
        lessonLogDTO2.setId(lessonLogDTO1.getId());
        assertThat(lessonLogDTO1).isEqualTo(lessonLogDTO2);
        lessonLogDTO2.setId(2L);
        assertThat(lessonLogDTO1).isNotEqualTo(lessonLogDTO2);
        lessonLogDTO1.setId(null);
        assertThat(lessonLogDTO1).isNotEqualTo(lessonLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lessonLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lessonLogMapper.fromId(null)).isNull();
    }
}
