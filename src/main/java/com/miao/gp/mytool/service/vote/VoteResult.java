package com.miao.gp.mytool.service.vote;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vote_result")
public class VoteResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullIp;
    private String voteDate;
    private Integer successNum;
}
