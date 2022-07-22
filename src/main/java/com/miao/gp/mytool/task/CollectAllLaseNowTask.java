package com.miao.gp.mytool.task;

import com.miao.gp.mytool.service.his.HistoryDateCollectService;
import com.miao.gp.mytool.service.his.SohuHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class CollectAllLaseNowTask {
    @Resource
    private HistoryDateCollectService historyDateCollectService;

    @Scheduled(cron = "* 40 15 * * ?")
    public void execute() {
        log.info("CollectAllLaseNowTask start");
        historyDateCollectService.collectAllLaseNow();
    }

}