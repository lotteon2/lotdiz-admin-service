package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.dto.request.AuthorizedProjectRequestDto;
import com.lotdiz.adminservice.dto.response.GetProjectResponseDto;
import com.lotdiz.adminservice.entity.ProjectInfo;
import com.lotdiz.adminservice.exception.ProjectInfoEntityNotFoundException;
import com.lotdiz.adminservice.mapper.ProjectInfoMapper;
import com.lotdiz.adminservice.messagequeue.kafka.ProjectProducer;
import com.lotdiz.adminservice.repository.ProjectInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectInfoService {

  private final ProjectInfoMapper projectInfoMapper;
  private final ProjectInfoRepository projectInfoRepository;
  private final ProjectProducer projectProducer;

  public List<GetProjectResponseDto> getProjects(Pageable pageable) {
    List<ProjectInfo> projectInfos = projectInfoRepository.findAll(pageable).getContent();
    return projectInfoMapper.projectInfosToGetProjectResponseDtos(projectInfos);
  }

  @Transactional
  public void authorizeProject(AuthorizedProjectRequestDto authorizedProjectRequestDto) {
    Long projectId = authorizedProjectRequestDto.getProjectId();
    ProjectInfo projectInfo = projectInfoRepository.findById(projectId)
            .orElseThrow(ProjectInfoEntityNotFoundException::new);
    projectInfo.authorize();

    projectProducer.sendAuthorizedProject(authorizedProjectRequestDto);
  }
}
