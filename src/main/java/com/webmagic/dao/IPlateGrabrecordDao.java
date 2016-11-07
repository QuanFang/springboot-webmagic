package com.webmagic.dao;

import com.webmagic.bean.HousesDictionaryPlateRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by iceyohoo on 2016/10/20.
 */
public interface IPlateGrabrecordDao extends CrudRepository<HousesDictionaryPlateRecord,Long>{

    HousesDictionaryPlateRecord findByAreaId(Long areaId);
}
