package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.GiftLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GiftLog and its DTO GiftLogDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GiftMapper.class})
public interface GiftLogMapper extends EntityMapper<GiftLogDTO, GiftLog> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "gift.id", target = "giftId")
    GiftLogDTO toDto(GiftLog giftLog);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "giftId", target = "gift")
    GiftLog toEntity(GiftLogDTO giftLogDTO);

    default GiftLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        GiftLog giftLog = new GiftLog();
        giftLog.setId(id);
        return giftLog;
    }
}
