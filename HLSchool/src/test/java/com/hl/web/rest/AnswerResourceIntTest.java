package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.Answer;
import com.hl.repository.AnswerRepository;
import com.hl.service.AnswerService;
import com.hl.repository.search.AnswerSearchRepository;
import com.hl.service.dto.AnswerDTO;
import com.hl.service.mapper.AnswerMapper;
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
 * Test class for the AnswerResource REST controller.
 *
 * @see AnswerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class AnswerResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_RESULT = false;
    private static final Boolean UPDATED_RESULT = true;

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerSearchRepository answerSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAnswerMockMvc;

    private Answer answer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnswerResource answerResource = new AnswerResource(answerService);
        this.restAnswerMockMvc = MockMvcBuilders.standaloneSetup(answerResource)
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
    public static Answer createEntity(EntityManager em) {
        Answer answer = new Answer()
            .createDate(DEFAULT_CREATE_DATE)
            .result(DEFAULT_RESULT)
            .rawData(DEFAULT_RAW_DATA);
        return answer;
    }

    @Before
    public void initTest() {
        answerSearchRepository.deleteAll();
        answer = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnswer() throws Exception {
        int databaseSizeBeforeCreate = answerRepository.findAll().size();

        // Create the Answer
        AnswerDTO answerDTO = answerMapper.toDto(answer);
        restAnswerMockMvc.perform(post("/api/answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isCreated());

        // Validate the Answer in the database
        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeCreate + 1);
        Answer testAnswer = answerList.get(answerList.size() - 1);
        assertThat(testAnswer.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAnswer.isResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testAnswer.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the Answer in Elasticsearch
        Answer answerEs = answerSearchRepository.findOne(testAnswer.getId());
        assertThat(testAnswer.getCreateDate()).isEqualTo(testAnswer.getCreateDate());
        assertThat(answerEs).isEqualToIgnoringGivenFields(testAnswer, "createDate");
    }

    @Test
    @Transactional
    public void createAnswerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = answerRepository.findAll().size();

        // Create the Answer with an existing ID
        answer.setId(1L);
        AnswerDTO answerDTO = answerMapper.toDto(answer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerMockMvc.perform(post("/api/answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Answer in the database
        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerRepository.findAll().size();
        // set the field null
        answer.setResult(null);

        // Create the Answer, which fails.
        AnswerDTO answerDTO = answerMapper.toDto(answer);

        restAnswerMockMvc.perform(post("/api/answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isBadRequest());

        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnswers() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        // Get all the answerList
        restAnswerMockMvc.perform(get("/api/answers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answer.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getAnswer() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        // Get the answer
        restAnswerMockMvc.perform(get("/api/answers/{id}", answer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(answer.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.booleanValue()))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAnswer() throws Exception {
        // Get the answer
        restAnswerMockMvc.perform(get("/api/answers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnswer() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);
        answerSearchRepository.save(answer);
        int databaseSizeBeforeUpdate = answerRepository.findAll().size();

        // Update the answer
        Answer updatedAnswer = answerRepository.findOne(answer.getId());
        // Disconnect from session so that the updates on updatedAnswer are not directly saved in db
        em.detach(updatedAnswer);
        updatedAnswer
            .createDate(UPDATED_CREATE_DATE)
            .result(UPDATED_RESULT)
            .rawData(UPDATED_RAW_DATA);
        AnswerDTO answerDTO = answerMapper.toDto(updatedAnswer);

        restAnswerMockMvc.perform(put("/api/answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isOk());

        // Validate the Answer in the database
        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeUpdate);
        Answer testAnswer = answerList.get(answerList.size() - 1);
        assertThat(testAnswer.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAnswer.isResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testAnswer.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the Answer in Elasticsearch
        Answer answerEs = answerSearchRepository.findOne(testAnswer.getId());
        assertThat(testAnswer.getCreateDate()).isEqualTo(testAnswer.getCreateDate());
        assertThat(answerEs).isEqualToIgnoringGivenFields(testAnswer, "createDate");
    }

    @Test
    @Transactional
    public void updateNonExistingAnswer() throws Exception {
        int databaseSizeBeforeUpdate = answerRepository.findAll().size();

        // Create the Answer
        AnswerDTO answerDTO = answerMapper.toDto(answer);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAnswerMockMvc.perform(put("/api/answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isCreated());

        // Validate the Answer in the database
        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAnswer() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);
        answerSearchRepository.save(answer);
        int databaseSizeBeforeDelete = answerRepository.findAll().size();

        // Get the answer
        restAnswerMockMvc.perform(delete("/api/answers/{id}", answer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean answerExistsInEs = answerSearchRepository.exists(answer.getId());
        assertThat(answerExistsInEs).isFalse();

        // Validate the database is empty
        List<Answer> answerList = answerRepository.findAll();
        assertThat(answerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAnswer() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);
        answerSearchRepository.save(answer);

        // Search the answer
        restAnswerMockMvc.perform(get("/api/_search/answers?query=id:" + answer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answer.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.booleanValue())))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Answer.class);
        Answer answer1 = new Answer();
        answer1.setId(1L);
        Answer answer2 = new Answer();
        answer2.setId(answer1.getId());
        assertThat(answer1).isEqualTo(answer2);
        answer2.setId(2L);
        assertThat(answer1).isNotEqualTo(answer2);
        answer1.setId(null);
        assertThat(answer1).isNotEqualTo(answer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerDTO.class);
        AnswerDTO answerDTO1 = new AnswerDTO();
        answerDTO1.setId(1L);
        AnswerDTO answerDTO2 = new AnswerDTO();
        assertThat(answerDTO1).isNotEqualTo(answerDTO2);
        answerDTO2.setId(answerDTO1.getId());
        assertThat(answerDTO1).isEqualTo(answerDTO2);
        answerDTO2.setId(2L);
        assertThat(answerDTO1).isNotEqualTo(answerDTO2);
        answerDTO1.setId(null);
        assertThat(answerDTO1).isNotEqualTo(answerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(answerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(answerMapper.fromId(null)).isNull();
    }
}
