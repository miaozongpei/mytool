package com.miao.gp.mytool.service;

import com.miao.gp.mytool.MytoolApplication;
import com.miao.gp.mytool.service.gp.GpList;
import com.miao.gp.mytool.service.gp.GpListRepository;
import com.miao.gp.mytool.service.his.HisPrice;
import com.miao.gp.mytool.service.his.HistoryDateCollectService;
import com.miao.gp.mytool.service.his.SohuHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MytoolApplication.class)
@Lazy
class SohuHistoryServiceTest {
    @Resource
    private SohuHistoryService sohuHistoryService;
    @Resource
    private GpListRepository gpListRepository;

    @Resource
    private HistoryDateCollectService historyDateCollectService;
    @Test
    void query() {
        List<HisPrice> list= sohuHistoryService.query("002475","20210101","20211231");
        System.out.println(list);
    }
    @Test
    void gpList() {
        List<GpList> list= gpListRepository.findAll();
        System.out.println(list);
    }
    @Test
    void collect() {
        historyDateCollectService.collect("002475");
    }
    @Test
    void collectAll() {
        historyDateCollectService.collectAll();
    }

    @Test
    void collectLaseNow()  {
        historyDateCollectService.collectLaseNow("002475");
    }
    @Test
    void collectAllLaseNow() throws InterruptedException{
        historyDateCollectService.collectAllLaseNow();
        Thread.sleep(120*1000);
    }
    @Test
    void collectAllYesterday() {
        historyDateCollectService.collectAllYesterday();
    }
}