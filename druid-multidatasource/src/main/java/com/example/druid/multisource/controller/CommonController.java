package com.example.druid.multisource.controller;

import com.example.druid.multisource.api.CommonPage;
import com.example.druid.multisource.api.CommonResult;
import com.example.druid.multisource.service.HouseTypeService;
import com.example.druid.multisource.model.HouseType;
import com.example.druid.multisource.model.Person;
import com.example.druid.multisource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jacob
 */
@RestController
@RequestMapping("/api")
public class CommonController {
    @Autowired
    private HouseTypeService houseTypeService;

    @Autowired
    private PersonService personService;

    /**
     * 主数据源api
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/master/{page}/{size}")
    public CommonResult<CommonPage<Person>> queryAllPerson(@PathVariable Integer page, @PathVariable Integer size){
        return CommonResult.success(CommonPage.restPage(personService.queryAllPerson()));
    }

    /**
     * 从数据源api
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/slave/{page}/{size}")
    public CommonResult<CommonPage<HouseType>> queryAllHouseType(@PathVariable Integer page, @PathVariable Integer size){
        return CommonResult.success(CommonPage.restPage(houseTypeService.queryAllHouseType()));
    }

}
