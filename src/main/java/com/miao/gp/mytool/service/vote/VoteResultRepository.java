package com.miao.gp.mytool.service.vote;

import com.miao.gp.mytool.service.ip.ProxyIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteResultRepository extends JpaRepository<VoteResult,Long> {
}