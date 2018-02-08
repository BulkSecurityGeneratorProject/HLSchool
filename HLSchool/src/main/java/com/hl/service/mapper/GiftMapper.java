package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.GiftDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Gift and its DTO GiftDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftMapper extends EntityMapper<GiftDTO, Gift> {



    default Gift fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gift gift = new Gift();
        gift.setId(id);
        return gift;
    }
}
