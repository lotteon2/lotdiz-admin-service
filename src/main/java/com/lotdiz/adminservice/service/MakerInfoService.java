package com.lotdiz.adminservice.service;

import com.lotdiz.adminservice.dto.response.GetMakerResponseDto;
import com.lotdiz.adminservice.entity.MakerInfo;
import com.lotdiz.adminservice.mapper.MakerInfoMapper;
import com.lotdiz.adminservice.repository.MakerInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MakerInfoService {

  private final MakerInfoMapper makerInfoMapper;
  private final MakerInfoRepository makerInfoRepository;

  public List<GetMakerResponseDto> getMakers(Pageable pageable) {
    List<MakerInfo> makerInfos = makerInfoRepository.findAll(pageable).getContent();
    return makerInfoMapper.makerInfosToGetMakerResponseDtos(makerInfos);
  }
}
