package com.webmagic.bean;

import javax.persistence.*;
import java.util.Date;

/** 对爬取过的区进行一个记录
 * Created by iceyohoo on 2016/10/20.
 */
@Entity
@Table(name = "pro_house_dictionary_plate_grabrecord")
public class HousesDictionaryPlateRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long areaId;

    //抓取时间
    @Column
    private Date grabDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Date getGrabDate() {
        return grabDate;
    }

    public void setGrabDate(Date grabDate) {
        this.grabDate = grabDate;
    }
}
