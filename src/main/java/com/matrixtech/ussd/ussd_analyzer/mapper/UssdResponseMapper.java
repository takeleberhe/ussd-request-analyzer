package com.matrixtech.ussd.ussd_analyzer.mapper;

import com.matrixtech.ussd.ussd_analyzer.dto.UssdResponseDTO;
import com.matrixtech.ussd.ussd_analyzer.entity.UssdResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UssdResponseMapper {
    UssdResponseDTO toDto(UssdResponse response);
}
