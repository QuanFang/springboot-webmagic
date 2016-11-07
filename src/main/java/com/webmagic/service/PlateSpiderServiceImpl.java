package com.webmagic.service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.webmagic.bean.HousesDictionaryPlateRecord;
import com.webmagic.bean.HousesDictonaryPlate;
import com.webmagic.dao.IPlateGrabrecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * Created by iceyohoo on 2016/10/14.
 */
@Service
public class PlateSpiderServiceImpl implements PlateSpiderService {

/*    private static final String DRIVER = "com.mysql.jdbc.Driver"; //驱动
    private static final String URL = "jdbc:mysql://139.224.45.98:3306/teamwork_java?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    private static final String USER_NAME = "tworkj";
    private static final String USER_PWD = "HouseEagle,Service,Mysql,2016";*/

    @Autowired
    private IPlateGrabrecordDao plateGrabrecordDao;

    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    @Transactional
    @Override
    public void addHouseDictionaryPlate(HousesDictonaryPlate plate) {
        if (plate != null){
            Query query = getEm().createNativeQuery("INSERT INTO pro_house_dictionary_plate(`name`,plateId,housesDictionaryAreaCounty_areaCountyId) VALUES(?,?,?)");
            query.setParameter(1,plate.getName());
            query.setParameter(2,plate.getPlateId());
            query.setParameter(3,plate.getAreaId());
            query.executeUpdate();
        }else {
            //传入的plate为空
        }


       /* try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("classForName Exception:" + e.getMessage());
        }

        try {
            java.sql.Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PWD);


            String sql = "INSERT INTO pro_house_dictionary_plate(`name`,plateId,housesDictionaryAreaCounty_areaCountyId) VALUES(?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,plate.getName());
            statement.setLong(2,plate.getPlateId());
            statement.setLong(3,plate.getAreaId());

            statement.executeUpdate();

            statement.close();

            conn.close();
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage());
        }*/
    }

    @Override
    public Long findAreaIdByCityNameAndAreaName(String cityName, String areaName) {

        StringBuffer sb = new StringBuffer();
        if (cityName != null && !"".equals(cityName) && areaName != null && !"".equals(areaName)){
            String baseSql = "SELECT a.id,a.areaCountyId FROM pro_house_dictionary_areacounty as a " +
                    " JOIN pro_house_dictionary_city as b ON a.housesDictionaryCity_cityId=b.cityId " +
                    " AND b.cityName=?  AND a.`name` LIKE ?";
            sb.append(baseSql);
            Query query = getEm().createNativeQuery(sb.toString());
            query.setParameter(1,cityName);
            query.setParameter(2,"%" + areaName + "%");
            List<Object[]> result = query.getResultList();
            if (result != null && result.size()>0 ){
                Object[] obj = result.get(0);
                return ((BigInteger)obj[1]).longValue();
            }else {
                //没有结果
                return null;
            }
        }else {
            //传入参数有为空的
            return null;
        }


    }

    @Override
    public boolean areaAlradyGrab(Long areaId) {
        if (areaId != null){
            HousesDictionaryPlateRecord record = plateGrabrecordDao.findByAreaId(areaId);
            if (record != null){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void addPlateGrabrecord(Long areaId) {
        if (areaId != null){
            HousesDictionaryPlateRecord record = new HousesDictionaryPlateRecord();
            record.setAreaId(areaId);
            record.setGrabDate(new Date());
            plateGrabrecordDao.save(record);
        }
    }
}
