package com.lotdiz.adminservice.controller.restcontroller;

import com.lotdiz.adminservice.dto.response.*;
import com.lotdiz.adminservice.entity.MemberInfo;
import com.lotdiz.adminservice.mapper.MemberInfoMapper;
import com.lotdiz.adminservice.service.MemberInfoService;
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
public class MemberRestController {

  private final MemberInfoService memberInfoService;
  private final MemberInfoMapper memberInfoMapper;

  @GetMapping("/members")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getMembers(
      @PageableDefault(
              page = 0,
              size = 10,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<MemberInfo> memberInfos = memberInfoService.getMembers(pageable);
    int totalPages = memberInfos.getTotalPages();
    List<GetMemberResponseDto> getMemberResponseDtos =
        memberInfoMapper.memberInfosToGetMemberResponseDtos(memberInfos.getContent());
    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("회원 조회 성공")
                .data(Map.of("totalPages", totalPages, "members", getMemberResponseDtos))
                .build());
  }

  @GetMapping("/members/search")
  public ResponseEntity<SuccessResponse<Map<String, Object>>> getMemberSearchResult(
      @RequestParam("query") String query,
      @PageableDefault(
              page = 0,
              size = 10,
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          Pageable pageable) {
    Page<MemberInfo> memberInfos = memberInfoService.getMemberSearchResult(query, pageable);
    int totalPages = memberInfos.getTotalPages();
    List<GetMemberSearchResponseDto> getMemberSearchResponseDtos =
        memberInfoMapper.memberInfosToGetMemberSearchResponseDtos(memberInfos.getContent());

    return ResponseEntity.ok()
        .body(
            SuccessResponse.<Map<String, Object>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.name())
                .detail("회원 검색 성공")
                .data(Map.of("totalPages", totalPages, "members", getMemberSearchResponseDtos))
                .build());
  }
}
