package com.africom.client.web.rest;

import com.africom.client.service.SocieteService;
import com.africom.client.web.rest.errors.BadRequestAlertException;
import com.africom.client.service.dto.SocieteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.africom.client.domain.Societe}.
 */
@RestController
@RequestMapping("/api")
public class SocieteResource {

    private final Logger log = LoggerFactory.getLogger(SocieteResource.class);

    private static final String ENTITY_NAME = "clientServiceSociete";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocieteService societeService;

    public SocieteResource(SocieteService societeService) {
        this.societeService = societeService;
    }

    /**
     * {@code POST  /societes} : Create a new societe.
     *
     * @param societeDTO the societeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new societeDTO, or with status {@code 400 (Bad Request)} if the societe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/societes")
    public ResponseEntity<SocieteDTO> createSociete(@Valid @RequestBody SocieteDTO societeDTO) throws URISyntaxException {
        log.debug("REST request to save Societe : {}", societeDTO);
        if (societeDTO.getId() != null) {
            throw new BadRequestAlertException("A new societe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocieteDTO result = societeService.save(societeDTO);
        return ResponseEntity.created(new URI("/api/societes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /societes} : Updates an existing societe.
     *
     * @param societeDTO the societeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeDTO,
     * or with status {@code 400 (Bad Request)} if the societeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the societeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/societes")
    public ResponseEntity<SocieteDTO> updateSociete(@Valid @RequestBody SocieteDTO societeDTO) throws URISyntaxException {
        log.debug("REST request to update Societe : {}", societeDTO);
        if (societeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocieteDTO result = societeService.save(societeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, societeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /societes} : get all the societes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of societes in body.
     */
    @GetMapping("/societes")
    public ResponseEntity<List<SocieteDTO>> getAllSocietes(Pageable pageable) {
        log.debug("REST request to get a page of Societes");
        Page<SocieteDTO> page = societeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /societes/:id} : get the "id" societe.
     *
     * @param id the id of the societeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the societeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/societes/{id}")
    public ResponseEntity<SocieteDTO> getSociete(@PathVariable Long id) {
        log.debug("REST request to get Societe : {}", id);
        Optional<SocieteDTO> societeDTO = societeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(societeDTO);
    }

    /**
     * {@code DELETE  /societes/:id} : delete the "id" societe.
     *
     * @param id the id of the societeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/societes/{id}")
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        log.debug("REST request to delete Societe : {}", id);
        societeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
