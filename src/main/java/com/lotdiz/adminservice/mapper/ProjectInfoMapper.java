package com.lotdiz.adminservice.mapper;

import com.lotdiz.adminservice.dto.request.CreateProjectRequestDto;
import com.lotdiz.adminservice.dto.response.GetProjectResponseDto;
import com.lotdiz.adminservice.entity.ProjectInfo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectInfoMapper {

//  ProjectInfoMapper INSTANCE = Mappers.getMapper(ProjectInfoMapper.class);

  ProjectInfo createProjectRequestDtoToProjectInfo(CreateProjectRequestDto createProjectRequestDto);

  @Named("PRD")
  GetProjectResponseDto
  projectInfoToGetProjectResponseDto(ProjectInfo projectInfo);

  @IterableMapping(qualifiedByName = "PRD")
  List<GetProjectResponseDto>
  projectInfosToGetProjectResponseDtos(List<ProjectInfo> projectInfos);
}
