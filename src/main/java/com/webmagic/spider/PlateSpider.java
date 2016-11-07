package com.webmagic.spider;

import com.webmagic.bean.HousesDictonaryPlate;
import com.webmagic.service.PlateSpiderService;
import com.webmagic.utils._16MD5Utils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by iceyohoo on 2016/10/13.
 */
@Component
public class PlateSpider implements PageProcessor {

    private PlateSpiderService service;

    private Long areaId;

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public void setService(PlateSpiderService service) {
        this.service = service;
    }

    public PlateSpider(){
    }

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
        //进行存储
        List<String> contents = page.getHtml().xpath("//div[@class='subarea']/a/text()").all();
        boolean result = service.areaAlradyGrab(areaId);
        if (!result){
            if (contents != null && contents.size() > 0 ){
                //对已经爬取过的区做一个记录，防止二次爬取
                for (int i=0;i<contents.size();i++){
                    HousesDictonaryPlate plate = new HousesDictonaryPlate();
                    plate.setName(contents.get(i));
                    plate.setPlateId(_16MD5Utils.get12UUIDNum());
                    plate.setAreaId(areaId);
                    service.addHouseDictionaryPlate(plate);
                }
                //抓取过后进行存储
                service.addPlateGrabrecord(areaId);
            }else {
                //抓取的数据为空的情况
            }
        }else {
            //已经抓取过了
        }


    }

    public Site getSite() {
        return site;
    }

    public void plateSpiderDo(String cityPYAbbreviations,String areaPYAbbreviations,PlateSpider spider){
        String url = "http://" + cityPYAbbreviations + ".58.com/" + areaPYAbbreviations + "/?PGTID=0d30000c-0013-0840-0f7c-ecb3f7b9df9b&ClickID=4";
        Spider.create(spider)
                .addUrl(url)
//                .addPipeline(new JsonFilePipeline("G://webmagic"))
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

    public static void main(String[] args) {
//        PlateSpider.plateSpiderDo("xm","siming");
    }
}
