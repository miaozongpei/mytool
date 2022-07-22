package com.miao.gp.mytool.service.his;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.miao.gp.mytool.service.gp.GpList;
import com.miao.gp.mytool.service.gp.GpListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HistoryDateCollectService {
   @Resource
   private GpListService gpListService;
   @Resource
   private SohuHistoryService sohuHistoryService;
   @Resource
   private HisPriceRepository hisPriceRepository;
   private ExecutorService executorService = Executors.newFixedThreadPool(20);        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
   //Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
   //Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
//   Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
   public void collect(String code){
      GpList gp=gpListService.query(code);
      collect(gp);
   }

   private void collect(GpList gp){
      String code=gp.getCode();
      String ttm=gp.getTtm();
      String tYear=ttm.split("-")[0];
      try {
         int year=Integer.parseInt(tYear);
         for(;year<=2021;year++){
            sohuHistoryService.query(code,year+"0101",year+"1231");
         }
      }catch (Exception e){
         log.info("code:{},ttm:{},errorMsg:{}",code,ttm,e.getMessage());
      }
   }

   public void collectAll(){
      List<GpList> list=gpListService.queryAll();
      for (GpList gp:list){
         collect(gp);
      }
   }
   public void collectLaseNow(String code){
      String maxDateStr=hisPriceRepository.queryMaxDate(code);
      if (!StringUtils.isEmpty(maxDateStr)){
         Date maxDate=DateUtil.parse(maxDateStr,DatePattern.NORM_DATE_FORMAT);
         Date startDate=DateUtil.offsetDay(maxDate,1);
         String endDateStr=DateUtil.format(new Date(),DatePattern.PURE_DATE_PATTERN);
         maxDateStr=maxDateStr.replaceAll("-","");
         if (endDateStr.equals(maxDateStr)){
            return;
         }
         sohuHistoryService.query(code,DateUtil.format(startDate,DatePattern.PURE_DATE_PATTERN), DateUtil.format(new Date(),DatePattern.PURE_DATE_PATTERN));
      }
   }
   public void collectAllLaseNow(){
      List<GpList> list=gpListService.queryAll();
      for (GpList gp:list){
         executorService.execute(new Runnable() {
            @Override
            public void run() {
               collectLaseNow(gp.getCode());
            }
         });
      }
   }
   public void collectAllYesterday(){
      List<GpList> list=gpListService.queryAll();
      List<String> codes = list.stream().map(GpList::getCode).collect(Collectors.toList());
      List<String> subList=new ArrayList<>();
      for (int i=0;i<codes.size();i++){
         subList.add(codes.get(i));
         if ((i+1)%200==0){
            String yesterdayDate=DateUtil.format(DateUtil.yesterday(),DatePattern.PURE_DATE_PATTERN);
            sohuHistoryService.query(subList,yesterdayDate, yesterdayDate);
            subList.clear();
         }
      }

   }
}
