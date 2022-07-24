package com.miao.gp.mytool.service.ip;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "proxy_ip")
public class ProxyIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullIp;
    private String ip;
    private Integer port;
    private Integer flag=0;
    private Integer successNum;
}
