package com.lotdiz.adminservice.exception;

import com.lotdiz.adminservice.exception.common.EntityNotFoundException;

public class ProjectInfoEntityNotFoundException extends EntityNotFoundException {

  private static final String message = "프로젝트 정보를 찾을 수 없습니다.";

  public ProjectInfoEntityNotFoundException() {
    super(message);
  }
}
