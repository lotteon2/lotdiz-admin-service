package com.lotdiz.adminservice.repository;

import com.lotdiz.adminservice.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {}
