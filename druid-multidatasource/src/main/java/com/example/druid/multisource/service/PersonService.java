package com.example.druid.multisource.service;

import com.example.druid.multisource.model.Person;

import java.util.List;

/**
 * @author Jacob
 */
public interface PersonService {
    /**
     *查询主数据源所有人的信息
     */
    List<Person> queryAllPerson();
}
