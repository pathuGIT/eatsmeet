package com.wak.eatsmeet.mapper;

import com.wak.eatsmeet.dto.food.curryDto.CreateCurryDto;
import com.wak.eatsmeet.dto.food.curryDto.CurryResponse;
import com.wak.eatsmeet.dto.food.curryDto.UpdateCurryDto;
import com.wak.eatsmeet.model.food.Curry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurryMapper {

    Curry toEntity(CreateCurryDto createCurryDto);

    Curry toEntity(UpdateCurryDto updateCurryDto);

    CurryResponse toDto(Curry curry);

}
