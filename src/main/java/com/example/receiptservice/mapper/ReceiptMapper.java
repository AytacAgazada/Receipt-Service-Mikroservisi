package com.example.receiptservice.mapper;

import com.example.receiptservice.dto.receipt.ReceiptRequestDTO;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.entity.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DrugMapper.class})
public interface ReceiptMapper {

    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "drugs", target = "drugs")
    Receipt toEntity(ReceiptRequestDTO requestDto);

    @Mapping(source = "drugs", target = "drugs")
    ReceiptResponseDto toResponseDto(Receipt receipt);

    // List<Receipt> obyektini List<ReceiptResponseDto>-ya çevirmə metodu
    List<ReceiptResponseDto> toResponseDtoList(List<Receipt> receipts);
}