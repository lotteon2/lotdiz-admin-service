package com.lotdiz.adminservice.mapper;

import com.lotdiz.adminservice.dto.request.CreateProjectRequestDto;
import com.lotdiz.adminservice.dto.response.GetProjectResponseDto;
import com.lotdiz.adminservice.entity.ProjectInfo;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProjectInfoMapper {

  ProjectInfo createProjectRequestDtoToProjectInfo(CreateProjectRequestDto createProjectRequestDto);

  @Named("PRD")
  GetProjectResponseDto projectInfoToGetProjectResponseDto(ProjectInfo projectInfo);

  @IterableMapping(qualifiedByName = "PRD")
  List<GetProjectResponseDto> projectInfosToGetProjectResponseDtos(List<ProjectInfo> projectInfos);
}
