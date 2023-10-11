package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.dto.response.GetMemberResponseDto;
import com.lotdiz.adminservice.entity.MemberInfo;
import com.lotdiz.adminservice.mapper.MemberInfoMapper;
import com.lotdiz.adminservice.repository.MemberInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberInfoMapper memberInfoMapper;
  private final MemberInfoRepository memberInfoRepository;

  public List<GetMemberResponseDto> getMembers(Pageable pageable) {
    List<MemberInfo> memberInfos = memberInfoRepository.findAll(pageable).getContent();
    return memberInfoMapper.memberInfosToGetMemberResponseDtos(memberInfos);
  }
}
