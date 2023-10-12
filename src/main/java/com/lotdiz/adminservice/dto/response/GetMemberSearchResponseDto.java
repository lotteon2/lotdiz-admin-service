package com.lotdiz.adminservice.dto.response;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMemberSearchResponseDto {

  private Long memberId;
  private String memberRole;
  private String memberEmail;
  private String memberName;
  private String memberPhoneNumber;
  private LocalDateTime createdAt;
}
