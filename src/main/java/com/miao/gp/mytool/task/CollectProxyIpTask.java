package com.miao.gp.mytool.task;

import com.miao.gp.mytool.service.his.HistoryDateCollectService;
import com.miao.gp.mytool.service.ip.ProxyIpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class CollectProxyIpTask {
    @Resource
    private ProxyIpService proxyIpService;

    //@Scheduled(cron = "0 0/2 * * * ?")
    public void execute() {
        log.info("CollectProxyIpTask start");
        proxyIpService.saveIps();
    }

}