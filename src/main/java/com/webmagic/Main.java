package com.webmagic;

import com.webmagic.service.PlateSpiderService;
import com.webmagic.spider.PlateSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iceyohoo on 2016/10/14.
 */
@EnableAutoConfiguration
@ComponentScan
@RestController
public class Main {


    @Autowired
    private PlateSpiderService service;

    @Autowired
    private PlateSpider plateSpider;

    @RequestMapping(value = "/spider",method = RequestMethod.GET)
    public String spider(
            @RequestParam(value = "cityName",required = true)String cityName,
            @RequestParam(value = "cityPYAbbreviations",required = true)String cityPYAbbreviations,
            @RequestParam(value = "areaName",required = true)String areaName,
            @RequestParam(value = "areaPYAbbreviations",required = true)String areaPYAbbreviations
    ){
        try {
            //返回areaId
            //城市名要全名，区可以模糊
            Long areaId = service.findAreaIdByCityNameAndAreaName(cityName,areaName);
            if (areaId != null){
                plateSpider.setAreaId(areaId);
                plateSpider.setService(service);
                plateSpider.plateSpiderDo(cityPYAbbreviations,areaPYAbbreviations,plateSpider);
                return "抓取和录取数据库成功！!";
            }else {
                return "请确认城市名和区名是否输入正确！";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "exception!";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
