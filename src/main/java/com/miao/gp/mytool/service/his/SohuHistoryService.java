package com.miao.gp.mytool.service.his;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miao.gp.mytool.service.gp.GpList;
import com.miao.gp.mytool.utils.HttpClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SohuHistoryService {
    //https://q.stock.sohu.com/hisHq?code=cn_300228&start=20130930&end=20131231&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp
    private static final String url="https://q.stock.sohu.com/hisHq?stat=1&order=A&period=d&callback=historySearchHandler&rt=json";
    @Resource
    private HisPriceRepository hisPriceRepository;
    public List<HisPrice> query(String code, String startDate, String endDate){
        List<String> codes=new ArrayList<>();
        codes.add(code);
        return query(codes,startDate,endDate);
    }

    public List<HisPrice> query(List<String> codes,String startDate, String endDate){
        Map<String, String> params=new HashMap<>();
        StringBuffer codeStr=new StringBuffer();
        for (String code:codes){
            codeStr.append("cn_"+code).append(',');
        }
        codeStr.setLength(codeStr.length()-1);
        params.put("code",codeStr.toString());
        params.put("start",startDate);
        params.put("end",endDate);
        String result=HttpClientUtils.doGet(url,params);
        List<HisPrice> list=new ArrayList<>();
        if (result.indexOf("hq")!=-1) {
            JSONArray resultArray = JSON.parseArray(result);
            for (int j=0;j<resultArray.size();j++) {
                JSONObject resultJson = resultArray.getJSONObject(j);
                JSONArray hqArray = resultJson.getJSONArray("hq");
                String code =resultJson.getString("code");
                if (!StringUtils.isEmpty(code)) {
                    for (int i = 0; i < hqArray.size(); i++) {
                        JSONArray hq = hqArray.getJSONArray(i);
                        HisPrice hisPrice = new HisPrice();
                        hisPrice.setCode(code.replace("cn_",""));
                        hisPrice.setHisDate(hq.getString(0));
                        hisPrice.setStartPrice(hq.getDouble(1));
                        hisPrice.setEndPrice(hq.getDouble(2));
                        hisPrice.setUpAmount(hq.getDouble(3));
                        hisPrice.setUpRate(getRateDouble(hq, 4));
                        hisPrice.setMinPrice(hq.getDouble(5));
                        hisPrice.setMaxPrice(hq.getDouble(6));
                        hisPrice.setChangeHands(hq.getDouble(7));
                        hisPrice.setChangeHandsAmount(hq.getDouble(8));
                        hisPrice.setChangeHandsRate(getRateDouble(hq, 9));
                        list.add(hisPrice);
                    }
                    hisPriceRepository.saveAll(list);
                }
            }
        }
        return list;
    }
    private Double getRateDouble( JSONArray hq,int index){
        String ds=hq.getString(index);
        if (ds.indexOf("%")!=-1){
            return Double.parseDouble(ds.replace("%",""))*0.01;
        } else if (ds.indexOf("-")!=-1){
            return null;
        }
        return hq.getDouble(index);
    }
}
