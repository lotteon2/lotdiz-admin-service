package com.lotdiz.adminservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMakerResponseDto {

    private Long makerId;
    private String makerName;
    private String makerEmail;
    private String makerPhoneNumber;
    private String makerKakaoUrl;
    private String makerHomeUrl;
    private String makerSnsUrl;
    private LocalDateTime createdAt;
}
