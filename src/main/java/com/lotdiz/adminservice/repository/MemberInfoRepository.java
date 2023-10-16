package com.lotdiz.adminservice.repository;

import com.lotdiz.adminservice.entity.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {

  @Query("select m from MemberInfo m " +
          "where m.memberName like %:query% " +
          "or m.memberPhoneNumber like %:query% " +
          "or m.memberEmail like %:query%")
  Page<MemberInfo> findAllByMemberNameLike(@Param("query") String query, Pageable pageable);
}
