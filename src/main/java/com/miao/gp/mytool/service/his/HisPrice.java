package com.miao.gp.mytool.service.his;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "his_price")
public class HisPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    //0：日期
    private String hisDate;
    //1：开盘价
    private Double startPrice;
    //2：收盘价
    private Double endPrice;
    //3：上涨金额
    private Double upAmount;
    //4：涨幅
    private Double upRate;
    //5：最低价
    private Double minPrice;
    //6：最高价
    private Double maxPrice;
    //7：换手量
    private Double changeHands;
    //8：换手额
    private Double changeHandsAmount;
    //9：换手率
    private Double changeHandsRate;
}
