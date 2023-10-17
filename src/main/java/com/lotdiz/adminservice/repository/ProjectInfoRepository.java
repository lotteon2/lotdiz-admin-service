package com.lotdiz.adminservice.repository;

import com.lotdiz.adminservice.entity.MemberInfo;
import com.lotdiz.adminservice.entity.ProjectInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long> {

    @Query("select p from ProjectInfo p " +
            "where p.projectName like %:query% " +
            "or p.categoryName like %:query% " +
            "or p.makerName like %:query%")
    Page<ProjectInfo> findAllByLike(@Param("query") String query, Pageable pageable);
}
