package com.lotdiz.adminservice.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectInfo {

  @Id
  @Column(name = "project_id")
  private Long projectId;

  @Column(name = "project_name", nullable = false)
  private String projectName;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(name = "maker_name", nullable = false)
  private String makerName;

  @Column(name = "project_due_date", nullable = false)
  private LocalDateTime projectDueDate;

  @Column(name = "project_is_authorized", nullable = false)
  private Boolean projectIsAuthorized;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  public void authorize() {
    this.projectIsAuthorized = true;
  }
}
