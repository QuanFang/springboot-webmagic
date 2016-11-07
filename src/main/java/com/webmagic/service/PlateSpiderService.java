package com.webmagic.service;

import com.webmagic.bean.HousesDictonaryPlate;


/**
 * Created by iceyohoo on 2016/10/14.
 */
public interface PlateSpiderService {

    void addHouseDictionaryPlate(HousesDictonaryPlate plate);

    Long findAreaIdByCityNameAndAreaName(String cityName, String areaName);

    boolean areaAlradyGrab(Long areaId);

    void addPlateGrabrecord(Long areaId);
}
