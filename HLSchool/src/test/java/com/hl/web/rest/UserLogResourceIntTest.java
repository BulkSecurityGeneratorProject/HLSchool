package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.UserLog;
import com.hl.repository.UserLogRepository;
import com.hl.service.UserLogService;
import com.hl.repository.search.UserLogSearchRepository;
import com.hl.service.dto.UserLogDTO;
import com.hl.service.mapper.UserLogMapper;
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
 * Test class for the UserLogResource REST controller.
 *
 * @see UserLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class UserLogResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_COMPLETE = false;
    private static final Boolean UPDATED_COMPLETE = true;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private UserLogMapper userLogMapper;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private UserLogSearchRepository userLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserLogMockMvc;

    private UserLog userLog;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final UserLogResource userLogResource = new UserLogResource(userLogService);
//        this.restUserLogMockMvc = MockMvcBuilders.standaloneSetup(userLogResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserLog createEntity(EntityManager em) {
        UserLog userLog = new UserLog()
            .createDate(DEFAULT_CREATE_DATE)
            .complete(DEFAULT_COMPLETE)
            .point(DEFAULT_POINT)
            .rawData(DEFAULT_RAW_DATA);
        return userLog;
    }

    @Before
    public void initTest() {
        userLogSearchRepository.deleteAll();
        userLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserLog() throws Exception {
        int databaseSizeBeforeCreate = userLogRepository.findAll().size();

        // Create the UserLog
        UserLogDTO userLogDTO = userLogMapper.toDto(userLog);
        restUserLogMockMvc.perform(post("/api/user-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLogDTO)))
            .andExpect(status().isCreated());

        // Validate the UserLog in the database
        List<UserLog> userLogList = userLogRepository.findAll();
        assertThat(userLogList).hasSize(databaseSizeBeforeCreate + 1);
        UserLog testUserLog = userLogList.get(userLogList.size() - 1);
        assertThat(testUserLog.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testUserLog.isComplete()).isEqualTo(DEFAULT_COMPLETE);
        assertThat(testUserLog.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testUserLog.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the UserLog in Elasticsearch
        UserLog userLogEs = userLogSearchRepository.findOne(testUserLog.getId());
        assertThat(testUserLog.getCreateDate()).isEqualTo(testUserLog.getCreateDate());
        assertThat(userLogEs).isEqualToIgnoringGivenFields(testUserLog, "createDate");
    }

    @Test
    @Transactional
    public void createUserLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userLogRepository.findAll().size();

        // Create the UserLog with an existing ID
        userLog.setId(1L);
        UserLogDTO userLogDTO = userLogMapper.toDto(userLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserLogMockMvc.perform(post("/api/user-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserLog in the database
        List<UserLog> userLogList = userLogRepository.findAll();
        assertThat(userLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserLogs() throws Exception {
        // Initialize the database
        userLogRepository.saveAndFlush(userLog);

        // Get all the userLogList
        restUserLogMockMvc.perform(get("/api/user-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getUserLog() throws Exception {
        // Initialize the database
        userLogRepository.saveAndFlush(userLog);

        // Get the userLog
        restUserLogMockMvc.perform(get("/api/user-logs/{id}", userLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userLog.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.complete").value(DEFAULT_COMPLETE.booleanValue()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserLog() throws Exception {
        // Get the userLog
        restUserLogMockMvc.perform(get("/api/user-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserLog() throws Exception {
        // Initialize the database
        userLogRepository.saveAndFlush(userLog);
        userLogSearchRepository.save(userLog);
        int databaseSizeBeforeUpdate = userLogRepository.findAll().size();

        // Update the userLog
        UserLog updatedUserLog = userLogRepository.findOne(userLog.getId());
        // Disconnect from session so that the updates on updatedUserLog are not directly saved in db
        em.detach(updatedUserLog);
        updatedUserLog
            .createDate(UPDATED_CREATE_DATE)
            .complete(UPDATED_COMPLETE)
            .point(UPDATED_POINT)
            .rawData(UPDATED_RAW_DATA);
        UserLogDTO userLogDTO = userLogMapper.toDto(updatedUserLog);

        restUserLogMockMvc.perform(put("/api/user-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLogDTO)))
            .andExpect(status().isOk());

        // Validate the UserLog in the database
        List<UserLog> userLogList = userLogRepository.findAll();
        assertThat(userLogList).hasSize(databaseSizeBeforeUpdate);
        UserLog testUserLog = userLogList.get(userLogList.size() - 1);
        assertThat(testUserLog.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testUserLog.isComplete()).isEqualTo(UPDATED_COMPLETE);
        assertThat(testUserLog.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testUserLog.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the UserLog in Elasticsearch
        UserLog userLogEs = userLogSearchRepository.findOne(testUserLog.getId());
        assertThat(testUserLog.getCreateDate()).isEqualTo(testUserLog.getCreateDate());
        assertThat(userLogEs).isEqualToIgnoringGivenFields(testUserLog, "createDate");
    }

    @Test
    @Transactional
    public void updateNonExistingUserLog() throws Exception {
        int databaseSizeBeforeUpdate = userLogRepository.findAll().size();

        // Create the UserLog
        UserLogDTO userLogDTO = userLogMapper.toDto(userLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserLogMockMvc.perform(put("/api/user-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLogDTO)))
            .andExpect(status().isCreated());

        // Validate the UserLog in the database
        List<UserLog> userLogList = userLogRepository.findAll();
        assertThat(userLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserLog() throws Exception {
        // Initialize the database
        userLogRepository.saveAndFlush(userLog);
        userLogSearchRepository.save(userLog);
        int databaseSizeBeforeDelete = userLogRepository.findAll().size();

        // Get the userLog
        restUserLogMockMvc.perform(delete("/api/user-logs/{id}", userLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean userLogExistsInEs = userLogSearchRepository.exists(userLog.getId());
        assertThat(userLogExistsInEs).isFalse();

        // Validate the database is empty
        List<UserLog> userLogList = userLogRepository.findAll();
        assertThat(userLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchUserLog() throws Exception {
        // Initialize the database
        userLogRepository.saveAndFlush(userLog);
        userLogSearchRepository.save(userLog);

        // Search the userLog
        restUserLogMockMvc.perform(get("/api/_search/user-logs?query=id:" + userLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLog.class);
        UserLog userLog1 = new UserLog();
        userLog1.setId(1L);
        UserLog userLog2 = new UserLog();
        userLog2.setId(userLog1.getId());
        assertThat(userLog1).isEqualTo(userLog2);
        userLog2.setId(2L);
        assertThat(userLog1).isNotEqualTo(userLog2);
        userLog1.setId(null);
        assertThat(userLog1).isNotEqualTo(userLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLogDTO.class);
        UserLogDTO userLogDTO1 = new UserLogDTO();
        userLogDTO1.setId(1L);
        UserLogDTO userLogDTO2 = new UserLogDTO();
        assertThat(userLogDTO1).isNotEqualTo(userLogDTO2);
        userLogDTO2.setId(userLogDTO1.getId());
        assertThat(userLogDTO1).isEqualTo(userLogDTO2);
        userLogDTO2.setId(2L);
        assertThat(userLogDTO1).isNotEqualTo(userLogDTO2);
        userLogDTO1.setId(null);
        assertThat(userLogDTO1).isNotEqualTo(userLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userLogMapper.fromId(null)).isNull();
    }
}
