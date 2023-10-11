package com.lotdiz.adminservice.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakerInfo {

  @Id
  @Column(name = "maker_id")
  private Long makerId;

  @Column(name = "maker_name", nullable = false)
  private String makerName;

  @Column(name = "maker_email", nullable = false)
  private String makerEmail;

  @Column(name = "maker_phone_number", nullable = false)
  private String makerPhoneNumber;

  @Column(name = "maker_kakao_url")
  private String makerKakaoUrl;

  @Column(name = "maker_home_url")
  private String makerHomeUrl;

  @Column(name = "maker_sns_url")
  private String makerSnsUrl;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
}
