package com.webmagic.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by iceyohoo on 2016/10/13.
 */
public class AreaSpider implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
//        page.putField("area",page.getHtml().xpath("//dl[@id='region']/dd/a/text()").all());

        List<String> areas = page.getHtml().xpath("//dl[@id='region']/dd/a/text()").all();
        for (int i=1;i<areas.size()-1;i++){
            System.out.println("抓取到的数据：" + areas.get(i));
            //遍历每一个，然后去寻找每一个下面的片区名称
        }
    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new AreaSpider())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://fz.58.com/ershoufang/?utm_source=market&spm=b-31580022738699-me-f-824.bdpz_biaoti&PGTID=0d100000-0013-0c8f-0142-3366ef18361c&ClickID=1")
//                .addPipeline(new JsonFilePipeline("G://webmagic"))
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
