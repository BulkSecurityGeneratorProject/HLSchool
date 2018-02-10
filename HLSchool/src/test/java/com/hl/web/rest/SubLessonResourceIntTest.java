package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.SubLesson;
import com.hl.repository.SubLessonRepository;
import com.hl.service.SubLessonService;
import com.hl.repository.search.SubLessonSearchRepository;
import com.hl.service.dto.SubLessonDTO;
import com.hl.service.mapper.SubLessonMapper;
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
 * Test class for the SubLessonResource REST controller.
 *
 * @see SubLessonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class SubLessonResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTEN = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTEN = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTVI = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTVI = "BBBBBBBBBB";

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private SubLessonRepository subLessonRepository;

    @Autowired
    private SubLessonMapper subLessonMapper;

    @Autowired
    private SubLessonService subLessonService;

    @Autowired
    private SubLessonSearchRepository subLessonSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubLessonMockMvc;

    private SubLesson subLesson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubLessonResource subLessonResource = new SubLessonResource(subLessonService);
        this.restSubLessonMockMvc = MockMvcBuilders.standaloneSetup(subLessonResource)
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
    public static SubLesson createEntity(EntityManager em) {
        SubLesson subLesson = new SubLesson()
            .createDate(DEFAULT_CREATE_DATE)
            .title(DEFAULT_TITLE)
            .contenten(DEFAULT_CONTENTEN)
            .contentvi(DEFAULT_CONTENTVI)
            .rawData(DEFAULT_RAW_DATA);
        return subLesson;
    }

    @Before
    public void initTest() {
        subLessonSearchRepository.deleteAll();
        subLesson = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubLesson() throws Exception {
        int databaseSizeBeforeCreate = subLessonRepository.findAll().size();

        // Create the SubLesson
        SubLessonDTO subLessonDTO = subLessonMapper.toDto(subLesson);
        restSubLessonMockMvc.perform(post("/api/sub-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonDTO)))
            .andExpect(status().isCreated());

        // Validate the SubLesson in the database
        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeCreate + 1);
        SubLesson testSubLesson = subLessonList.get(subLessonList.size() - 1);
        assertThat(testSubLesson.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testSubLesson.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSubLesson.getContenten()).isEqualTo(DEFAULT_CONTENTEN);
        assertThat(testSubLesson.getContentvi()).isEqualTo(DEFAULT_CONTENTVI);
        assertThat(testSubLesson.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the SubLesson in Elasticsearch
        SubLesson subLessonEs = subLessonSearchRepository.findOne(testSubLesson.getId());
        assertThat(testSubLesson.getCreateDate()).isEqualTo(testSubLesson.getCreateDate());
        assertThat(subLessonEs).isEqualToIgnoringGivenFields(testSubLesson, "createDate");
    }

    @Test
    @Transactional
    public void createSubLessonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subLessonRepository.findAll().size();

        // Create the SubLesson with an existing ID
        subLesson.setId(1L);
        SubLessonDTO subLessonDTO = subLessonMapper.toDto(subLesson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubLessonMockMvc.perform(post("/api/sub-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubLesson in the database
        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = subLessonRepository.findAll().size();
        // set the field null
        subLesson.setTitle(null);

        // Create the SubLesson, which fails.
        SubLessonDTO subLessonDTO = subLessonMapper.toDto(subLesson);

        restSubLessonMockMvc.perform(post("/api/sub-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonDTO)))
            .andExpect(status().isBadRequest());

        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubLessons() throws Exception {
        // Initialize the database
        subLessonRepository.saveAndFlush(subLesson);

        // Get all the subLessonList
        restSubLessonMockMvc.perform(get("/api/sub-lessons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subLesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].contenten").value(hasItem(DEFAULT_CONTENTEN.toString())))
            .andExpect(jsonPath("$.[*].contentvi").value(hasItem(DEFAULT_CONTENTVI.toString())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getSubLesson() throws Exception {
        // Initialize the database
        subLessonRepository.saveAndFlush(subLesson);

        // Get the subLesson
        restSubLessonMockMvc.perform(get("/api/sub-lessons/{id}", subLesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subLesson.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.contenten").value(DEFAULT_CONTENTEN.toString()))
            .andExpect(jsonPath("$.contentvi").value(DEFAULT_CONTENTVI.toString()))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSubLesson() throws Exception {
        // Get the subLesson
        restSubLessonMockMvc.perform(get("/api/sub-lessons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubLesson() throws Exception {
        // Initialize the database
        subLessonRepository.saveAndFlush(subLesson);
        subLessonSearchRepository.save(subLesson);
        int databaseSizeBeforeUpdate = subLessonRepository.findAll().size();

        // Update the subLesson
        SubLesson updatedSubLesson = subLessonRepository.findOne(subLesson.getId());
        // Disconnect from session so that the updates on updatedSubLesson are not directly saved in db
        em.detach(updatedSubLesson);
        updatedSubLesson
            .createDate(UPDATED_CREATE_DATE)
            .title(UPDATED_TITLE)
            .contenten(UPDATED_CONTENTEN)
            .contentvi(UPDATED_CONTENTVI)
            .rawData(UPDATED_RAW_DATA);
        SubLessonDTO subLessonDTO = subLessonMapper.toDto(updatedSubLesson);

        restSubLessonMockMvc.perform(put("/api/sub-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonDTO)))
            .andExpect(status().isOk());

        // Validate the SubLesson in the database
        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeUpdate);
        SubLesson testSubLesson = subLessonList.get(subLessonList.size() - 1);
        assertThat(testSubLesson.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testSubLesson.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSubLesson.getContenten()).isEqualTo(UPDATED_CONTENTEN);
        assertThat(testSubLesson.getContentvi()).isEqualTo(UPDATED_CONTENTVI);
        assertThat(testSubLesson.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the SubLesson in Elasticsearch
        SubLesson subLessonEs = subLessonSearchRepository.findOne(testSubLesson.getId());
        assertThat(testSubLesson.getCreateDate()).isEqualTo(testSubLesson.getCreateDate());
        assertThat(subLessonEs).isEqualToIgnoringGivenFields(testSubLesson, "createDate");
    }

    @Test
    @Transactional
    public void updateNonExistingSubLesson() throws Exception {
        int databaseSizeBeforeUpdate = subLessonRepository.findAll().size();

        // Create the SubLesson
        SubLessonDTO subLessonDTO = subLessonMapper.toDto(subLesson);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSubLessonMockMvc.perform(put("/api/sub-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subLessonDTO)))
            .andExpect(status().isCreated());

        // Validate the SubLesson in the database
        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSubLesson() throws Exception {
        // Initialize the database
        subLessonRepository.saveAndFlush(subLesson);
        subLessonSearchRepository.save(subLesson);
        int databaseSizeBeforeDelete = subLessonRepository.findAll().size();

        // Get the subLesson
        restSubLessonMockMvc.perform(delete("/api/sub-lessons/{id}", subLesson.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean subLessonExistsInEs = subLessonSearchRepository.exists(subLesson.getId());
        assertThat(subLessonExistsInEs).isFalse();

        // Validate the database is empty
        List<SubLesson> subLessonList = subLessonRepository.findAll();
        assertThat(subLessonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSubLesson() throws Exception {
        // Initialize the database
        subLessonRepository.saveAndFlush(subLesson);
        subLessonSearchRepository.save(subLesson);

        // Search the subLesson
        restSubLessonMockMvc.perform(get("/api/_search/sub-lessons?query=id:" + subLesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subLesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].contenten").value(hasItem(DEFAULT_CONTENTEN.toString())))
            .andExpect(jsonPath("$.[*].contentvi").value(hasItem(DEFAULT_CONTENTVI.toString())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubLesson.class);
        SubLesson subLesson1 = new SubLesson();
        subLesson1.setId(1L);
        SubLesson subLesson2 = new SubLesson();
        subLesson2.setId(subLesson1.getId());
        assertThat(subLesson1).isEqualTo(subLesson2);
        subLesson2.setId(2L);
        assertThat(subLesson1).isNotEqualTo(subLesson2);
        subLesson1.setId(null);
        assertThat(subLesson1).isNotEqualTo(subLesson2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubLessonDTO.class);
        SubLessonDTO subLessonDTO1 = new SubLessonDTO();
        subLessonDTO1.setId(1L);
        SubLessonDTO subLessonDTO2 = new SubLessonDTO();
        assertThat(subLessonDTO1).isNotEqualTo(subLessonDTO2);
        subLessonDTO2.setId(subLessonDTO1.getId());
        assertThat(subLessonDTO1).isEqualTo(subLessonDTO2);
        subLessonDTO2.setId(2L);
        assertThat(subLessonDTO1).isNotEqualTo(subLessonDTO2);
        subLessonDTO1.setId(null);
        assertThat(subLessonDTO1).isNotEqualTo(subLessonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(subLessonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(subLessonMapper.fromId(null)).isNull();
    }
}
