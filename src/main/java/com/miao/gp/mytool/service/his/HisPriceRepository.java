package com.miao.gp.mytool.service.his;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HisPriceRepository extends JpaRepository<HisPrice,Long> {
    @Query("SELECT max(hisDate) FROM HisPrice  WHERE code= ?1")
    String queryMaxDate(String code);
}