package com.miao.gp.mytool.service.ip;

import com.alibaba.fastjson.JSONArray;
import com.miao.gp.mytool.service.gp.GpList;
import com.miao.gp.mytool.utils.MyHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProxyIpService {
    @Resource
    private ProxyIpRepository proxyIpRepository;
    public List<ProxyIp> queryAll() {
        return proxyIpRepository.findAll();
    }
    public ProxyIp query(String fullIp) {
        ProxyIp proxyIp=new ProxyIp();
        proxyIp.setFullIp(fullIp);
        proxyIp.setFlag(null);
        Optional<ProxyIp> one = proxyIpRepository.findOne(Example.of(proxyIp));
        try {
            return one.get();
        }catch (Exception e){
            return null;
        }
    }
    public void saveIps(String fullIp) {
        try {
                String ipsStr = fullIp;
                ProxyIp crrProxyIp=query(ipsStr);
                if (crrProxyIp!=null){
                    return;
                }
                if (!StringUtils.isEmpty(ipsStr)) {
                    String ip = ipsStr.split(":")[0];
                    int port = Integer.parseInt(ipsStr.split(":")[1]);
                    ProxyIp proxyIp = new ProxyIp();
                    proxyIp.setFullIp(ipsStr);
                    proxyIp.setIp(ip);
                    proxyIp.setPort(port);
                    if (MyHttpClientUtil.isValid(ip, port)) {
                        proxyIp.setFlag(1);
                    }
                    try {
                        proxyIpRepository.save(proxyIp);
                    }catch (Exception e){
                        log.error("保存IP异常",e);
                    }
                }
        } catch (Exception e) {
            log.error("获取Ip异常：", e);
        }
    }
    public void saveIps() {
        String url = "http://dev.qydailiip.com/api/?apikey=86566fe8418dc507a24aa96bad1cbd4c48707a52&" +
                "num=30&type=json&line=win&proxy_type=putong&sort=1&model=all&protocol=https&" +
                "address=&kill_address=&port=&kill_port=&today=false&abroad=1&isp=&anonymity=";
        String ips = null;
        try {
            ips = MyHttpClientUtil.get4Json(url);
            JSONArray ipList = JSONArray.parseArray(ips);
            for (int i = 0; i < ipList.size(); i++) {
                String ipsStr = ipList.get(i).toString();
                saveIps(ipsStr);
            }
        } catch (Exception e) {
            log.error("获取Ip异常：", e);
        }
    }
}
