package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.GiftLogService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.GiftLogDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing GiftLog.
 */
@RestController
@RequestMapping("/api")
public class GiftLogResource {

    private final Logger log = LoggerFactory.getLogger(GiftLogResource.class);

    private static final String ENTITY_NAME = "giftLog";

    private final GiftLogService giftLogService;

    public GiftLogResource(GiftLogService giftLogService) {
        this.giftLogService = giftLogService;
    }

    /**
     * POST  /gift-logs : Create a new giftLog.
     *
     * @param giftLogDTO the giftLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new giftLogDTO, or with status 400 (Bad Request) if the giftLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gift-logs")
    @Timed
    public ResponseEntity<GiftLogDTO> createGiftLog(@RequestBody GiftLogDTO giftLogDTO) throws URISyntaxException {
        log.debug("REST request to save GiftLog : {}", giftLogDTO);
        if (giftLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new giftLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiftLogDTO result = giftLogService.save(giftLogDTO);
        return ResponseEntity.created(new URI("/api/gift-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gift-logs : Updates an existing giftLog.
     *
     * @param giftLogDTO the giftLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated giftLogDTO,
     * or with status 400 (Bad Request) if the giftLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the giftLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gift-logs")
    @Timed
    public ResponseEntity<GiftLogDTO> updateGiftLog(@RequestBody GiftLogDTO giftLogDTO) throws URISyntaxException {
        log.debug("REST request to update GiftLog : {}", giftLogDTO);
        if (giftLogDTO.getId() == null) {
            return createGiftLog(giftLogDTO);
        }
        GiftLogDTO result = giftLogService.save(giftLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, giftLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gift-logs : get all the giftLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of giftLogs in body
     */
    @GetMapping("/gift-logs")
    @Timed
    public ResponseEntity<List<GiftLogDTO>> getAllGiftLogs(Pageable pageable) {
        log.debug("REST request to get a page of GiftLogs");
        Page<GiftLogDTO> page = giftLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gift-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gift-logs/:id : get the "id" giftLog.
     *
     * @param id the id of the giftLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the giftLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/gift-logs/{id}")
    @Timed
    public ResponseEntity<GiftLogDTO> getGiftLog(@PathVariable Long id) {
        log.debug("REST request to get GiftLog : {}", id);
        GiftLogDTO giftLogDTO = giftLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(giftLogDTO));
    }

    /**
     * DELETE  /gift-logs/:id : delete the "id" giftLog.
     *
     * @param id the id of the giftLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gift-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteGiftLog(@PathVariable Long id) {
        log.debug("REST request to delete GiftLog : {}", id);
        giftLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/gift-logs?query=:query : search for the giftLog corresponding
     * to the query.
     *
     * @param query the query of the giftLog search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/gift-logs")
    @Timed
    public ResponseEntity<List<GiftLogDTO>> searchGiftLogs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of GiftLogs for query {}", query);
        Page<GiftLogDTO> page = giftLogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/gift-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
