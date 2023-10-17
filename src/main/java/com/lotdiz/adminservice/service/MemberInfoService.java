package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.entity.MemberInfo;
import com.lotdiz.adminservice.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberInfoRepository memberInfoRepository;

  public Page<MemberInfo> getMembers(Pageable pageable) {
    return memberInfoRepository.findAll(pageable);
  }

  public Page<MemberInfo> getMemberSearchResult(String query, Pageable pageable) {
    return memberInfoRepository.findAllByLike(query, pageable);
  }
}
