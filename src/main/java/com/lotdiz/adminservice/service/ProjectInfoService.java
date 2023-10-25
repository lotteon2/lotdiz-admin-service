package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.dto.request.AuthorizedProjectRequestDto;
import com.lotdiz.adminservice.entity.ProjectInfo;
import com.lotdiz.adminservice.exception.ProjectInfoEntityNotFoundException;
import com.lotdiz.adminservice.messagequeue.kafka.ProjectProducer;
import com.lotdiz.adminservice.repository.ProjectInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectInfoService {

  private final ProjectInfoRepository projectInfoRepository;
  private final ProjectProducer projectProducer;

  public Page<ProjectInfo> getProjects(Pageable pageable) {
    return projectInfoRepository.findAll(pageable);
  }

  public Page<ProjectInfo> getProjectSearchResult(String query, Pageable pageable) {
    return projectInfoRepository.findAllByLike(query, pageable);
  }

  @Transactional
  public void authorizeProject(AuthorizedProjectRequestDto authorizedProjectRequestDto) {
    Long projectId = authorizedProjectRequestDto.getProjectId();
    ProjectInfo projectInfo =
        projectInfoRepository
            .findById(projectId)
            .orElseThrow(ProjectInfoEntityNotFoundException::new);
    projectInfo.authorize();

    projectProducer.sendAuthorizedProject(authorizedProjectRequestDto);
  }
}
