package com.lening.service;

import com.github.pagehelper.PageInfo;
import com.lening.entity.CarBean;
import com.lening.entity.CityBean;
import com.lening.entity.ColorBean;

import java.util.List;

public interface CarService {
    PageInfo<CarBean> findAll(CarBean carBean, Integer pageNum, Integer pageSize);

    void deleteCar(Integer id);

    List<ColorBean> findC();

    void addCar(CarBean carBean);


    List<CityBean> getCityListByPid(Integer pid);

}
