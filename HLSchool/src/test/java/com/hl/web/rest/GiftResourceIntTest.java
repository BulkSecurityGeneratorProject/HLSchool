package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.Gift;
import com.hl.repository.GiftRepository;
import com.hl.service.GiftService;
import com.hl.repository.search.GiftSearchRepository;
import com.hl.service.dto.GiftDTO;
import com.hl.service.mapper.GiftMapper;
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
 * Test class for the GiftResource REST controller.
 *
 * @see GiftResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class GiftResourceIntTest {

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CONTENTEN = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTEN = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTVI = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTVI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_RAW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RAW_DATA = "BBBBBBBBBB";

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private GiftService giftService;

    @Autowired
    private GiftSearchRepository giftSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGiftMockMvc;

    private Gift gift;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GiftResource giftResource = new GiftResource(giftService);
        this.restGiftMockMvc = MockMvcBuilders.standaloneSetup(giftResource)
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
    public static Gift createEntity(EntityManager em) {
        Gift gift = new Gift()
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .contenten(DEFAULT_CONTENTEN)
            .contentvi(DEFAULT_CONTENTVI)
            .createDate(DEFAULT_CREATE_DATE)
            .rawData(DEFAULT_RAW_DATA);
        return gift;
    }

    @Before
    public void initTest() {
        giftSearchRepository.deleteAll();
        gift = createEntity(em);
    }

    @Test
    @Transactional
    public void createGift() throws Exception {
        int databaseSizeBeforeCreate = giftRepository.findAll().size();

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);
        restGiftMockMvc.perform(post("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isCreated());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate + 1);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testGift.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testGift.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testGift.getContenten()).isEqualTo(DEFAULT_CONTENTEN);
        assertThat(testGift.getContentvi()).isEqualTo(DEFAULT_CONTENTVI);
        assertThat(testGift.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testGift.getRawData()).isEqualTo(DEFAULT_RAW_DATA);

        // Validate the Gift in Elasticsearch
        Gift giftEs = giftSearchRepository.findOne(testGift.getId());
        assertThat(testGift.getCreateDate()).isEqualTo(testGift.getCreateDate());
        assertThat(giftEs).isEqualToIgnoringGivenFields(testGift, "createDate");
    }

    @Test
    @Transactional
    public void createGiftWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = giftRepository.findAll().size();

        // Create the Gift with an existing ID
        gift.setId(1L);
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftMockMvc.perform(post("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftRepository.findAll().size();
        // set the field null
        gift.setPrice(null);

        // Create the Gift, which fails.
        GiftDTO giftDTO = giftMapper.toDto(gift);

        restGiftMockMvc.perform(post("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImageIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftRepository.findAll().size();
        // set the field null
        gift.setImage(null);

        // Create the Gift, which fails.
        GiftDTO giftDTO = giftMapper.toDto(gift);

        restGiftMockMvc.perform(post("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGifts() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get all the giftList
        restGiftMockMvc.perform(get("/api/gifts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].contenten").value(hasItem(DEFAULT_CONTENTEN.toString())))
            .andExpect(jsonPath("$.[*].contentvi").value(hasItem(DEFAULT_CONTENTVI.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void getGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get the gift
        restGiftMockMvc.perform(get("/api/gifts/{id}", gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gift.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.contenten").value(DEFAULT_CONTENTEN.toString()))
            .andExpect(jsonPath("$.contentvi").value(DEFAULT_CONTENTVI.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.rawData").value(DEFAULT_RAW_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGift() throws Exception {
        // Get the gift
        restGiftMockMvc.perform(get("/api/gifts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);
        giftSearchRepository.save(gift);
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift
        Gift updatedGift = giftRepository.findOne(gift.getId());
        // Disconnect from session so that the updates on updatedGift are not directly saved in db
        em.detach(updatedGift);
        updatedGift
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .contenten(UPDATED_CONTENTEN)
            .contentvi(UPDATED_CONTENTVI)
            .createDate(UPDATED_CREATE_DATE)
            .rawData(UPDATED_RAW_DATA);
        GiftDTO giftDTO = giftMapper.toDto(updatedGift);

        restGiftMockMvc.perform(put("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGift.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testGift.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testGift.getContenten()).isEqualTo(UPDATED_CONTENTEN);
        assertThat(testGift.getContentvi()).isEqualTo(UPDATED_CONTENTVI);
        assertThat(testGift.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testGift.getRawData()).isEqualTo(UPDATED_RAW_DATA);

        // Validate the Gift in Elasticsearch
        Gift giftEs = giftSearchRepository.findOne(testGift.getId());
        assertThat(testGift.getCreateDate()).isEqualTo(testGift.getCreateDate());
        assertThat(giftEs).isEqualToIgnoringGivenFields(testGift, "createDate");
    }

    @Test
    @Transactional
    public void updateNonExistingGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGiftMockMvc.perform(put("/api/gifts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isCreated());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);
        giftSearchRepository.save(gift);
        int databaseSizeBeforeDelete = giftRepository.findAll().size();

        // Get the gift
        restGiftMockMvc.perform(delete("/api/gifts/{id}", gift.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean giftExistsInEs = giftSearchRepository.exists(gift.getId());
        assertThat(giftExistsInEs).isFalse();

        // Validate the database is empty
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);
        giftSearchRepository.save(gift);

        // Search the gift
        restGiftMockMvc.perform(get("/api/_search/gifts?query=id:" + gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].contenten").value(hasItem(DEFAULT_CONTENTEN.toString())))
            .andExpect(jsonPath("$.[*].contentvi").value(hasItem(DEFAULT_CONTENTVI.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].rawData").value(hasItem(DEFAULT_RAW_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gift.class);
        Gift gift1 = new Gift();
        gift1.setId(1L);
        Gift gift2 = new Gift();
        gift2.setId(gift1.getId());
        assertThat(gift1).isEqualTo(gift2);
        gift2.setId(2L);
        assertThat(gift1).isNotEqualTo(gift2);
        gift1.setId(null);
        assertThat(gift1).isNotEqualTo(gift2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftDTO.class);
        GiftDTO giftDTO1 = new GiftDTO();
        giftDTO1.setId(1L);
        GiftDTO giftDTO2 = new GiftDTO();
        assertThat(giftDTO1).isNotEqualTo(giftDTO2);
        giftDTO2.setId(giftDTO1.getId());
        assertThat(giftDTO1).isEqualTo(giftDTO2);
        giftDTO2.setId(2L);
        assertThat(giftDTO1).isNotEqualTo(giftDTO2);
        giftDTO1.setId(null);
        assertThat(giftDTO1).isNotEqualTo(giftDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(giftMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(giftMapper.fromId(null)).isNull();
    }
}
