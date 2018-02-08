package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.UserLogService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.UserLogDTO;
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

/**
 * REST controller for managing UserLog.
 */
@RestController
@RequestMapping("/api")
public class UserLogResource {

    private final Logger log = LoggerFactory.getLogger(UserLogResource.class);

    private static final String ENTITY_NAME = "userLog";

    private final UserLogService userLogService;

    public UserLogResource(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    /**
     * POST  /user-logs : Create a new userLog.
     *
     * @param userLogDTO the userLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userLogDTO, or with status 400 (Bad Request) if the userLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-logs")
    @Timed
    public ResponseEntity<UserLogDTO> createUserLog(@RequestBody UserLogDTO userLogDTO) throws URISyntaxException {
        log.debug("REST request to save UserLog : {}", userLogDTO);
        if (userLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new userLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserLogDTO result = userLogService.save(userLogDTO);
        return ResponseEntity.created(new URI("/api/user-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-logs : Updates an existing userLog.
     *
     * @param userLogDTO the userLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userLogDTO,
     * or with status 400 (Bad Request) if the userLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the userLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-logs")
    @Timed
    public ResponseEntity<UserLogDTO> updateUserLog(@RequestBody UserLogDTO userLogDTO) throws URISyntaxException {
        log.debug("REST request to update UserLog : {}", userLogDTO);
        if (userLogDTO.getId() == null) {
            return createUserLog(userLogDTO);
        }
        UserLogDTO result = userLogService.save(userLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-logs : get all the userLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userLogs in body
     */
    @GetMapping("/user-logs")
    @Timed
    public ResponseEntity<List<UserLogDTO>> getAllUserLogs(Pageable pageable) {
        log.debug("REST request to get a page of UserLogs");
        Page<UserLogDTO> page = userLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-logs/:id : get the "id" userLog.
     *
     * @param id the id of the userLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-logs/{id}")
    @Timed
    public ResponseEntity<UserLogDTO> getUserLog(@PathVariable Long id) {
        log.debug("REST request to get UserLog : {}", id);
        UserLogDTO userLogDTO = userLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userLogDTO));
    }

    /**
     * DELETE  /user-logs/:id : delete the "id" userLog.
     *
     * @param id the id of the userLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserLog(@PathVariable Long id) {
        log.debug("REST request to delete UserLog : {}", id);
        userLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
