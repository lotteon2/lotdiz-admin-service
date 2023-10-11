package com.lotdiz.adminservice.repository;

import com.lotdiz.adminservice.entity.MakerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MakerInfoRepository extends JpaRepository<MakerInfo, Long> {

  @Query("select m from MakerInfo m where m.makerName like %:query%")
  Page<MakerInfo> findAllByMakerNameLike(@Param("query") String query, Pageable pageable);
}
