package com.matrixtech.ussd.ussd_analyzer.mapper;

import com.matrixtech.ussd.ussd_analyzer.dto.RequestResponsePairDTO;
import com.matrixtech.ussd.ussd_analyzer.dto.UssdRequestDTO;
import com.matrixtech.ussd.ussd_analyzer.dto.UssdResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestResponsePairMapper {

    default RequestResponsePairDTO toDto(UssdRequestDTO request, UssdResponseDTO response) {
        return RequestResponsePairDTO.builder()
                .request(request)
                .response(response)
                .build();
    }
}
