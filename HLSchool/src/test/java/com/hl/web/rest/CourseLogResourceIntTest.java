package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.CourseLog;
import com.hl.repository.CourseLogRepository;
import com.hl.repository.UserRepository;
import com.hl.service.CourseLogService;
import com.hl.repository.search.CourseLogSearchRepository;
import com.hl.service.CourseService;
import com.hl.service.UserService;
import com.hl.service.dto.CourseLogDTO;
import com.hl.service.mapper.CourseLogMapper;
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
 * Test class for the CourseLogResource REST controller.
 *
 * @see CourseLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class CourseLogResourceIntTest {

    private static final Boolean DEFAULT_COMPLETE = false;
    private static final Boolean UPDATED_COMPLETE = true;

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private CourseLogRepository courseLogRepository;

    @Autowired
    private CourseLogMapper courseLogMapper;

    @Autowired
    private CourseLogService courseLogService;

    @Autowired
    private CourseLogSearchRepository courseLogSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    private MockMvc restCourseLogMockMvc;

    private CourseLog courseLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseLogResource courseLogResource = new CourseLogResource(courseLogService, userRepository, courseService, userService);
        this.restCourseLogMockMvc = MockMvcBuilders.standaloneSetup(courseLogResource)
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
    public static CourseLog createEntity(EntityManager em) {
        CourseLog courseLog = new CourseLog()
            .complete(DEFAULT_COMPLETE)
            .rawData(DEFAULT_RAW_DATA);
        return courseLog;
    }

    @Before
    public void initTest() {
        courseLogSearchRepository.deleteAll();
        courseLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLog() throws Exception {
        int databaseSizeBeforeCreate = courseLogRepository.findAll().size();

        // Create the CourseLog
        CourseLogDTO courseLogDTO = courseLogMapper.toDto(courseLog);
        restCourseLogMockMvc.perform(post("/api/course-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseLog in the database
        List<CourseLog> courseLogList = courseLogRepository.findAll();
        assertThat(courseLogList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLog testCourseLog = courseLogList.get(courseLogList.size() - 1);
        assertThat(testCourseLog.isComplete()).isEqualTo(DEFAULT_COMPLETE);
        assertThat(testCourseLog.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the CourseLog in Elasticsearch
        CourseLog courseLogEs = courseLogSearchRepository.findOne(testCourseLog.getId());
        assertThat(courseLogEs).isEqualToIgnoringGivenFields(testCourseLog);
    }

    @Test
    @Transactional
    public void createCourseLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLogRepository.findAll().size();

        // Create the CourseLog with an existing ID
        courseLog.setId(1L);
        CourseLogDTO courseLogDTO = courseLogMapper.toDto(courseLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLogMockMvc.perform(post("/api/course-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseLog in the database
        List<CourseLog> courseLogList = courseLogRepository.findAll();
        assertThat(courseLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCourseLogs() throws Exception {
        // Initialize the database
        courseLogRepository.saveAndFlush(courseLog);

        // Get all the courseLogList
        restCourseLogMockMvc.perform(get("/api/course-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getCourseLog() throws Exception {
        // Initialize the database
        courseLogRepository.saveAndFlush(courseLog);

        // Get the courseLog
        restCourseLogMockMvc.perform(get("/api/course-logs/{id}", courseLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLog.getId().intValue()))
            .andExpect(jsonPath("$.complete").value(DEFAULT_COMPLETE.booleanValue()))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLog() throws Exception {
        // Get the courseLog
        restCourseLogMockMvc.perform(get("/api/course-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLog() throws Exception {
        // Initialize the database
        courseLogRepository.saveAndFlush(courseLog);
        courseLogSearchRepository.save(courseLog);
        int databaseSizeBeforeUpdate = courseLogRepository.findAll().size();

        // Update the courseLog
        CourseLog updatedCourseLog = courseLogRepository.findOne(courseLog.getId());
        // Disconnect from session so that the updates on updatedCourseLog are not directly saved in db
        em.detach(updatedCourseLog);
        updatedCourseLog
            .complete(UPDATED_COMPLETE)
            .rawData(UPDATED_RAW_DATA);
        CourseLogDTO courseLogDTO = courseLogMapper.toDto(updatedCourseLog);

        restCourseLogMockMvc.perform(put("/api/course-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLogDTO)))
            .andExpect(status().isOk());

        // Validate the CourseLog in the database
        List<CourseLog> courseLogList = courseLogRepository.findAll();
        assertThat(courseLogList).hasSize(databaseSizeBeforeUpdate);
        CourseLog testCourseLog = courseLogList.get(courseLogList.size() - 1);
        assertThat(testCourseLog.isComplete()).isEqualTo(UPDATED_COMPLETE);
        assertThat(testCourseLog.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the CourseLog in Elasticsearch
        CourseLog courseLogEs = courseLogSearchRepository.findOne(testCourseLog.getId());
        assertThat(courseLogEs).isEqualToIgnoringGivenFields(testCourseLog);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLog() throws Exception {
        int databaseSizeBeforeUpdate = courseLogRepository.findAll().size();

        // Create the CourseLog
        CourseLogDTO courseLogDTO = courseLogMapper.toDto(courseLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLogMockMvc.perform(put("/api/course-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLogDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseLog in the database
        List<CourseLog> courseLogList = courseLogRepository.findAll();
        assertThat(courseLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLog() throws Exception {
        // Initialize the database
        courseLogRepository.saveAndFlush(courseLog);
        courseLogSearchRepository.save(courseLog);
        int databaseSizeBeforeDelete = courseLogRepository.findAll().size();

        // Get the courseLog
        restCourseLogMockMvc.perform(delete("/api/course-logs/{id}", courseLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean courseLogExistsInEs = courseLogSearchRepository.exists(courseLog.getId());
        assertThat(courseLogExistsInEs).isFalse();

        // Validate the database is empty
        List<CourseLog> courseLogList = courseLogRepository.findAll();
        assertThat(courseLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCourseLog() throws Exception {
        // Initialize the database
        courseLogRepository.saveAndFlush(courseLog);
        courseLogSearchRepository.save(courseLog);

        // Search the courseLog
        restCourseLogMockMvc.perform(get("/api/_search/course-logs?query=id:" + courseLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].complete").value(hasItem(DEFAULT_COMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLog.class);
        CourseLog courseLog1 = new CourseLog();
        courseLog1.setId(1L);
        CourseLog courseLog2 = new CourseLog();
        courseLog2.setId(courseLog1.getId());
        assertThat(courseLog1).isEqualTo(courseLog2);
        courseLog2.setId(2L);
        assertThat(courseLog1).isNotEqualTo(courseLog2);
        courseLog1.setId(null);
        assertThat(courseLog1).isNotEqualTo(courseLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLogDTO.class);
        CourseLogDTO courseLogDTO1 = new CourseLogDTO();
        courseLogDTO1.setId(1L);
        CourseLogDTO courseLogDTO2 = new CourseLogDTO();
        assertThat(courseLogDTO1).isNotEqualTo(courseLogDTO2);
        courseLogDTO2.setId(courseLogDTO1.getId());
        assertThat(courseLogDTO1).isEqualTo(courseLogDTO2);
        courseLogDTO2.setId(2L);
        assertThat(courseLogDTO1).isNotEqualTo(courseLogDTO2);
        courseLogDTO1.setId(null);
        assertThat(courseLogDTO1).isNotEqualTo(courseLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(courseLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(courseLogMapper.fromId(null)).isNull();
    }
}
