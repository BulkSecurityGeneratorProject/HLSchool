package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.SubLessonLogService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.SubLessonLogDTO;
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
 * REST controller for managing SubLessonLog.
 */
@RestController
@RequestMapping("/api")
public class SubLessonLogResource {

    private final Logger log = LoggerFactory.getLogger(SubLessonLogResource.class);

    private static final String ENTITY_NAME = "subLessonLog";

    private final SubLessonLogService subLessonLogService;

    public SubLessonLogResource(SubLessonLogService subLessonLogService) {
        this.subLessonLogService = subLessonLogService;
    }

    /**
     * POST  /sub-lesson-logs : Create a new subLessonLog.
     *
     * @param subLessonLogDTO the subLessonLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subLessonLogDTO, or with status 400 (Bad Request) if the subLessonLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-lesson-logs")
    @Timed
    public ResponseEntity<SubLessonLogDTO> createSubLessonLog(@RequestBody SubLessonLogDTO subLessonLogDTO) throws URISyntaxException {
        log.debug("REST request to save SubLessonLog : {}", subLessonLogDTO);
        if (subLessonLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new subLessonLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubLessonLogDTO result = subLessonLogService.save(subLessonLogDTO);
        return ResponseEntity.created(new URI("/api/sub-lesson-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-lesson-logs : Updates an existing subLessonLog.
     *
     * @param subLessonLogDTO the subLessonLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subLessonLogDTO,
     * or with status 400 (Bad Request) if the subLessonLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the subLessonLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-lesson-logs")
    @Timed
    public ResponseEntity<SubLessonLogDTO> updateSubLessonLog(@RequestBody SubLessonLogDTO subLessonLogDTO) throws URISyntaxException {
        log.debug("REST request to update SubLessonLog : {}", subLessonLogDTO);
        if (subLessonLogDTO.getId() == null) {
            return createSubLessonLog(subLessonLogDTO);
        }
        SubLessonLogDTO result = subLessonLogService.save(subLessonLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subLessonLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-lesson-logs : get all the subLessonLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of subLessonLogs in body
     */
    @GetMapping("/sub-lesson-logs")
    @Timed
    public ResponseEntity<List<SubLessonLogDTO>> getAllSubLessonLogs(Pageable pageable) {
        log.debug("REST request to get a page of SubLessonLogs");
        Page<SubLessonLogDTO> page = subLessonLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-lesson-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sub-lesson-logs/:id : get the "id" subLessonLog.
     *
     * @param id the id of the subLessonLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subLessonLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sub-lesson-logs/{id}")
    @Timed
    public ResponseEntity<SubLessonLogDTO> getSubLessonLog(@PathVariable Long id) {
        log.debug("REST request to get SubLessonLog : {}", id);
        SubLessonLogDTO subLessonLogDTO = subLessonLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subLessonLogDTO));
    }

    /**
     * DELETE  /sub-lesson-logs/:id : delete the "id" subLessonLog.
     *
     * @param id the id of the subLessonLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-lesson-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubLessonLog(@PathVariable Long id) {
        log.debug("REST request to delete SubLessonLog : {}", id);
        subLessonLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-lesson-logs?query=:query : search for the subLessonLog corresponding
     * to the query.
     *
     * @param query the query of the subLessonLog search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sub-lesson-logs")
    @Timed
    public ResponseEntity<List<SubLessonLogDTO>> searchSubLessonLogs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SubLessonLogs for query {}", query);
        Page<SubLessonLogDTO> page = subLessonLogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sub-lesson-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
