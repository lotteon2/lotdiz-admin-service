package com.lotdiz.adminservice.mapper;

import com.lotdiz.adminservice.dto.request.CreateMemberRequestDto;
import com.lotdiz.adminservice.dto.response.GetMemberResponseDto;
import com.lotdiz.adminservice.dto.response.GetMemberSearchResponseDto;
import com.lotdiz.adminservice.entity.MemberInfo;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MemberInfoMapper {

  MemberInfo createMemberRequestDtoToMemberInfo(CreateMemberRequestDto createMemberRequestDto);

  @Named("MRD")
  GetMemberResponseDto memberInfoToGetMemberResponseDto(MemberInfo memberInfo);

  @IterableMapping(qualifiedByName = "MRD")
  List<GetMemberResponseDto> memberInfosToGetMemberResponseDtos(List<MemberInfo> memberInfos);

  @Named("MSRD")
  GetMemberSearchResponseDto memberInfoToGetMemberSearchResponseDto(MemberInfo memberInfo);

  @IterableMapping(qualifiedByName = "MSRD")
  List<GetMemberSearchResponseDto> memberInfosToGetMemberSearchResponseDtos(List<MemberInfo> memberInfos);
}
