package com.africom.client.service;

import com.africom.client.domain.Societe;
import com.africom.client.repository.SocieteRepository;
import com.africom.client.service.dto.SocieteDTO;
import com.africom.client.service.mapper.SocieteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Societe}.
 */
@Service
@Transactional
public class SocieteService {

    private final Logger log = LoggerFactory.getLogger(SocieteService.class);

    private final SocieteRepository societeRepository;

    private final SocieteMapper societeMapper;

    public SocieteService(SocieteRepository societeRepository, SocieteMapper societeMapper) {
        this.societeRepository = societeRepository;
        this.societeMapper = societeMapper;
    }

    /**
     * Save a societe.
     *
     * @param societeDTO the entity to save.
     * @return the persisted entity.
     */
    public SocieteDTO save(SocieteDTO societeDTO) {
        log.debug("Request to save Societe : {}", societeDTO);
        Societe societe = societeMapper.toEntity(societeDTO);
        societe = societeRepository.save(societe);
        return societeMapper.toDto(societe);
    }

    /**
     * Get all the societes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SocieteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Societes");
        return societeRepository.findAll(pageable)
            .map(societeMapper::toDto);
    }


    /**
     * Get one societe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SocieteDTO> findOne(Long id) {
        log.debug("Request to get Societe : {}", id);
        return societeRepository.findById(id)
            .map(societeMapper::toDto);
    }

    /**
     * Delete the societe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Societe : {}", id);
        societeRepository.deleteById(id);
    }
}
