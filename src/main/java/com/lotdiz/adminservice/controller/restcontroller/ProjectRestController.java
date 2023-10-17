package com.lotdiz.adminservice.controller.restcontroller;

import com.lotdiz.adminservice.dto.request.AuthorizedProjectRequestDto;
import com.lotdiz.adminservice.dto.response.GetProjectResponseDto;
import com.lotdiz.adminservice.dto.response.GetProjectSearchResponseDto;
import com.lotdiz.adminservice.entity.ProjectInfo;
import com.lotdiz.adminservice.mapper.ProjectInfoMapper;
import com.lotdiz.adminservice.service.ProjectInfoService;
import com.lotdiz.adminservice.utils.SuccessResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRestController {

  private final ProjectInfoMapper projectInfoMapper;
  private final ProjectInfoService projectInfoService;

  @GetMapping("/projects")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getProjects(
      @PageableDefault(
              page = 0,
              size = 20,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<ProjectInfo> projectInfos = projectInfoService.getProjects(pageable);
    int totalPages = projectInfos.getTotalPages();
    List<GetProjectResponseDto> getProjectResponseDtos =
        projectInfoMapper.projectInfosToGetProjectResponseDtos(projectInfos.getContent());

    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("프로젝트 조회 성공")
                .data(Map.of("totalPages", totalPages, "projects", getProjectResponseDtos))
                .build());
  }

  @GetMapping("/projects/search")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getMemberSearchResult(
      @RequestParam("query") String query,
      @PageableDefault(
              page = 0,
              size = 20,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<ProjectInfo> projectInfos = projectInfoService.getProjectSearchResult(query, pageable);
    int totalPages = projectInfos.getTotalPages();
    List<GetProjectSearchResponseDto> getProjectSearchResponseDtos =
        projectInfoMapper.projectInfosToGetProjectSearchResponseDtos(projectInfos.getContent());

    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("프로젝트 검색 성공")
                .data(Map.of("totalPages", totalPages, "projects", getProjectSearchResponseDtos))
                .build());
  }

  @PostMapping("/projects/{projectId}/auth")
  public ResponseEntity<SuccessResponse<Object>> authorizeProject(
      @PathVariable("projectId") Long projectId) {
    projectInfoService.authorizeProject(
        AuthorizedProjectRequestDto.builder().projectId(projectId).build());

    return ResponseEntity.ok().body(null);
  }
}
