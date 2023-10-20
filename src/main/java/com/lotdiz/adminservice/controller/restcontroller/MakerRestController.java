package com.lotdiz.adminservice.controller.restcontroller;

import com.lotdiz.adminservice.dto.response.GetMakerResponseDto;
import com.lotdiz.adminservice.dto.response.GetMakerSearchResponseDto;
import com.lotdiz.adminservice.entity.MakerInfo;
import com.lotdiz.adminservice.mapper.MakerInfoMapper;
import com.lotdiz.adminservice.service.MakerInfoService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MakerRestController {

  private final MakerInfoMapper makerInfoMapper;
  private final MakerInfoService makerInfoService;

  @GetMapping("/makers")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getMakers(
      @PageableDefault(
              page = 0,
              size = 10,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<MakerInfo> makerInfos = makerInfoService.getMakers(pageable);
    int totalPages = makerInfos.getTotalPages();
    List<GetMakerResponseDto> getMakerResponseDtos =
        makerInfoMapper.makerInfosToGetMakerResponseDtos(makerInfos.getContent());

    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("메이커 조회 성공")
                .data(Map.of("totalPages", totalPages, "makers", getMakerResponseDtos))
                .build());
  }

  @GetMapping("/makers/search")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getMakerSearchResult(
      @RequestParam("query") String query,
      @PageableDefault(
              page = 0,
              size = 10,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<MakerInfo> makerInfos = makerInfoService.getMakerSearchResult(query, pageable);
    int totalPages = makerInfos.getTotalPages();
    List<GetMakerSearchResponseDto> getMakerSearchResponseDtos =
        makerInfoMapper.makerInfosToGetMakerSearchResponseDtos(makerInfos.getContent());

    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("메이커 검색 성공")
                .data(Map.of("totalPages", totalPages, "makers", getMakerSearchResponseDtos))
                .build());
  }
}
