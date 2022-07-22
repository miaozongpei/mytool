package com.miao.gp.mytool.service.gp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpListRepository extends JpaRepository<GpList,Long> {
}