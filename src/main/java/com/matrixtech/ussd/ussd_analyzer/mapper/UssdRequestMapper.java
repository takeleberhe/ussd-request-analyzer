package com.matrixtech.ussd.ussd_analyzer.mapper;

import com.matrixtech.ussd.ussd_analyzer.dto.UssdRequestDTO;
import com.matrixtech.ussd.ussd_analyzer.entity.UssdRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UssdRequestMapper {
    UssdRequestDTO toDto(UssdRequest request);
}
