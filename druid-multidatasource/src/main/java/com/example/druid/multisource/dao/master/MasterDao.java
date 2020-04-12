package com.example.druid.multisource.dao.master;

import com.example.druid.multisource.model.Person;


import java.util.List;

public interface MasterDao {
    /**
     * 测试druid主数据源,
     * @return
     */
    public List<Person> queryAllPersons();
}
