package com.matrixtech.ussd.ussd_analyzer.mapper;

import com.matrixtech.ussd.ussd_analyzer.dto.BlacklistDTO;
import com.matrixtech.ussd.ussd_analyzer.entity.BlacklistEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlacklistMapper {
    BlacklistDTO toDto(BlacklistEntry entry);
}
