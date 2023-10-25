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
public class MemberInfo {

  @Id
  @Column(name = "member_id")
  private Long memberId;

  @Column(name = "member_role")
  private String memberRole;

  @Column(name = "member_email")
  private String memberEmail;

  @Column(name = "member_name")
  private String memberName;

  @Column(name = "member_phone_number")
  private String memberPhoneNumber;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
