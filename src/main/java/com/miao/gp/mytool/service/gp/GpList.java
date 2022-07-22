package com.miao.gp.mytool.service.gp;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gp_list")
public class GpList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String type;
    private String subType;
    private String name;
    private String ttm;
    private String bizType;
    private String totalCapital;
    private String shareCapital;
}
