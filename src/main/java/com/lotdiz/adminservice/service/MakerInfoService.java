package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.entity.MakerInfo;
import com.lotdiz.adminservice.repository.MakerInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MakerInfoService {

  private final MakerInfoRepository makerInfoRepository;

  public Page<MakerInfo> getMakers(Pageable pageable) {
    return makerInfoRepository.findAll(pageable);
  }

  public Page<MakerInfo> getMakerSearchResult(String query, Pageable pageable) {
    return makerInfoRepository.findAllByLike(query, pageable);
  }
}
