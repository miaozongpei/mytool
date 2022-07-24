package com.miao.gp.mytool.service.vote;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.miao.gp.mytool.service.ip.ProxyIp;
import com.miao.gp.mytool.service.ip.ProxyIpRepository;
import com.miao.gp.mytool.utils.MyHttpClientUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
@Slf4j
public class VoteService {
    @Resource
    private ProxyIpRepository proxyIpRepository;

    @Resource
    private VoteResultRepository voteResultRepository;
    public  void vote()  {
        ProxyIp proxyIp=new ProxyIp();
        //proxyIp.setFlag(0);
        List<ProxyIp> proxyIps=proxyIpRepository.findAll(Example.of(proxyIp));
        for (ProxyIp ip:proxyIps){
            if (query(ip.getFullIp(),DateUtil.formatDate(new Date()))==null) {
                vote(ip);
            }
        }

    }
    public VoteResult query(String fullIp,String date) {
        VoteResult voteResult=new VoteResult();
        voteResult.setFullIp(fullIp);
        voteResult.setVoteDate(date);
        Optional<VoteResult> one = voteResultRepository.findOne(Example.of(voteResult));
        try {
            return one.get();
        }catch (Exception e){
            return null;
        }
    }
    public  void vote(ProxyIp ip)  {
        String voteUrl="https://2022ctacoc-api.sports.cn/api/open/match/voteVideo?videoOpenId=6D29462F8D604997B7FB6605EB0DA9BA";
        int successNum=vote4Json(ip.getIp(),ip.getPort(),voteUrl);
        VoteResult voteResult=new VoteResult();
        voteResult.setFullIp(ip.getFullIp());
        voteResult.setSuccessNum(successNum);
        voteResult.setVoteDate(DateUtil.formatDate(new Date()));
        voteResultRepository.save(voteResult);
        if (successNum==3){
            ip.setSuccessNum(1);
            proxyIpRepository.save(ip);
        }
    }
    public  int vote4Json(String ip,int port,String url)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        /* 设置超时 */
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        httpGet.setConfig(defaultRequestConfig);
        httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
        HttpHost proxy = new HttpHost(ip, port);
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(2000)
                .setSocketTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
        httpGet.setHeader("User-Agent", MyHttpClientUtil.getUserAgent());
        CloseableHttpResponse response = null;
        String result = null;
        try {
           if (vote(httpClient,httpGet)){
               //2次
               vote(httpClient,httpGet);
               //3次
               vote(httpClient,httpGet);
               return 3;
           }

        } catch (Exception e) {

        } finally {

            if (response != null) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
    private boolean vote(CloseableHttpClient httpClient,HttpGet httpGet){
        try {
            CloseableHttpResponse  response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            VoteRes voteRes= JSON.parseObject(result, VoteRes.class);
            if ("0".equals(voteRes.getCode())) {
                return true;
            }
        } catch (Exception e) {
           log.error("vote过程异常：",e);
        }
        return false;
    }
}
