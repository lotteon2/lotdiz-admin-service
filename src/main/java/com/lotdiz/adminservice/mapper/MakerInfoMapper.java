package com.lotdiz.adminservice.mapper;

import com.lotdiz.adminservice.dto.request.CreateMakerRequestDto;
import com.lotdiz.adminservice.dto.response.GetMakerResponseDto;
import com.lotdiz.adminservice.dto.response.GetMakerSearchResponseDto;
import com.lotdiz.adminservice.dto.response.GetProjectResponseDto;
import com.lotdiz.adminservice.entity.MakerInfo;
import com.lotdiz.adminservice.entity.ProjectInfo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MakerInfoMapper {

  MakerInfo createMakerRequestDtoToMakerInfo(CreateMakerRequestDto createMakerRequestDto);

  @Named("MRD")
  GetMakerResponseDto makerInfoToGetMakerResponseDto(MakerInfo makerInfo);

  @IterableMapping(qualifiedByName = "MRD")
  List<GetMakerResponseDto> makerInfosToGetMakerResponseDtos(List<MakerInfo> makerInfos);

  @Named("MSRD")
  GetMakerSearchResponseDto makerInfoToGetMakerSearchResponseDto(MakerInfo makerInfo);

  @IterableMapping(qualifiedByName = "MSRD")
  List<GetMakerSearchResponseDto> makerInfosToGetMakerSearchResponseDtos(List<MakerInfo> makerInfos);
}
