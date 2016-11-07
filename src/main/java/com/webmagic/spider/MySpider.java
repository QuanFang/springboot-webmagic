package com.webmagic.spider;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/** 百度测试
 * Created by iceyohoo on 2016/10/12.
 */
@TargetUrl("http://tieba.baidu.com/")
public class MySpider {


    @ExtractBy("//div[@class='search_nav j_search_nav']/b/text()")
    private String content;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), MySpider.class)
                .addUrl("http://tieba.baidu.com/").thread(5).run();
    }
}
