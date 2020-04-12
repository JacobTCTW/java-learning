package com.example.druid.multisource.dao.slave;

import com.example.druid.multisource.model.HouseType;

import java.util.List;

public interface SlaveDao {
    /**
     * 测试druid从数据源
     * @return
     */
    public List<HouseType> queryHouseType();
}
