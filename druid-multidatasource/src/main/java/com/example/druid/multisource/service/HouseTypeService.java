package com.example.druid.multisource.service;

import com.example.druid.multisource.model.HouseType;

import java.util.List;

/**
 * @author Jacob
 */
public interface HouseTypeService {
    /**
     * 查询从数据源所有的房屋类型
     */
    List<HouseType> queryAllHouseType();

}
