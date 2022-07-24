package com.miao.gp.mytool.service.ip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyIpRepository extends JpaRepository<ProxyIp,Long> {
}