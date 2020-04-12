package com.example.druid.multisource.service.impl;

import com.example.druid.multisource.dao.master.MasterDao;
import com.example.druid.multisource.dao.slave.SlaveDao;
import com.example.druid.multisource.model.HouseType;
import com.example.druid.multisource.model.Person;
import com.example.druid.multisource.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jacob
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    private MasterDao masterDao;

    @Override
    @Transactional(value = "masterTransactionManager")
    public List<Person> queryAllPerson() {
        return masterDao.queryAllPersons();
    }
}
