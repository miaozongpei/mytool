package com.miao.gp.mytool.service;

import com.miao.gp.mytool.MytoolApplication;
import com.miao.gp.mytool.service.ip.ProxyIp;
import com.miao.gp.mytool.service.ip.ProxyIpService;
import com.miao.gp.mytool.service.vote.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MytoolApplication.class)
@Lazy
class VoteServiceTest {
    @Resource
    private VoteService voteService;
    @Test
    void vote() {
       voteService.vote();
    }
}