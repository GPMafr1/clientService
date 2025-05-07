package com.africom.client.service.mapper;


import com.africom.client.domain.*;
import com.africom.client.service.dto.SocieteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Societe} and its DTO {@link SocieteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SocieteMapper extends EntityMapper<SocieteDTO, Societe> {



    default Societe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Societe societe = new Societe();
        societe.setId(id);
        return societe;
    }
}
