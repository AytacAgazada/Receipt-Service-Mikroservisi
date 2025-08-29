package com.example.receiptservice.mapper;

import com.example.receiptservice.dto.drug.DrugDTO;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.entity.Drug;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DrugMapper {

    DrugMapper INSTANCE = Mappers.getMapper(DrugMapper.class);

    @Mapping(target = "receipt", ignore = true) // 'receipt' sahəsini yox sayırıq, çünki bu, 'Receipt' tərəfindən idarə olunur.
    Drug toEntity(DrugDTO drugDto);

    DrugResponseDto toResponseDto(Drug drug);
}
