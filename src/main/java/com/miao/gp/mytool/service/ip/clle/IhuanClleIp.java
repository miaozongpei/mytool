package com.miao.gp.mytool.service.ip.clle;

import com.miao.gp.mytool.utils.HttpUtils;
import com.miao.gp.mytool.utils.IPBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class IhuanClleIp {
    private static final String MY_IP_API = "https://ip.ihuan.me/today/2022/07/24/09.html";

    // 获取当前ip地址，判断是否代理成功
    public static List<IPBean> getMyIp() {
        try {
            String html = HttpUtils.getResponseContent(MY_IP_API);
            Document doc = Jsoup.parse(html);
            Element element = doc.selectFirst("p.text-left");
            List<IPBean> ips=new ArrayList<>();
            for (int i=2;i<element.childNodeSize();i=i+2){
                Node node=element.childNode(i);
                String ipString=node.toString().split("@")[0];
                String[] ipArr=ipString.split(":");
                String ip=ipArr[0];
                Integer port=Integer.parseInt(ipArr[1]);
                int type=ipString.indexOf("支持HTTPS")!=-1?1:0;
                IPBean ipBean=new IPBean(ip,port,type);
                ips.add(ipBean);
            }
            return ips;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<IPBean>  xx=getMyIp();
        System.out.println(xx);
    }
}
