package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import com.lening.mapper.CarMapper;
import com.lening.mapper.CityMapper;
import com.lening.mapper.ColorMapper;
import com.lening.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Resource
    private CarMapper carMapper;
    @Resource
    private ColorMapper colorMapper;
    @Resource
    private CityMapper cityMapper;
    @Override
    public PageInfo<CarBean> findAll(CarBean carBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        CarBeanExample example = new CarBeanExample();
        CarBeanExample.Criteria criteria = example.createCriteria();
        if (carBean!=null){
            if (!StringUtils.isEmpty(carBean.getAddress())) {
                criteria.andAddressLike("%"+carBean.getAddress()+"%");
            }
            if (!StringUtils.isEmpty(carBean.getBuydate())) {
                criteria.andBuydateGreaterThanOrEqualTo(carBean.getBuydate());
            }
            if (!StringUtils.isEmpty(carBean.getEdate())) {
                criteria.andBuydateLessThanOrEqualTo(carBean.getEdate());
            }
        }
        List<CarBean> list = carMapper.selectByExample(example);
        for (CarBean bean : list) {
            if (bean.getCid()!=null){
                ColorBean colorBean = colorMapper.selectByPrimaryKey(bean.getCid());
                bean.setCb(colorBean);
            }
        }
        PageInfo<CarBean> pageInfo = new PageInfo<CarBean>(list);
        return pageInfo;
    }

    @Override
    public void deleteCar(Integer id) {
        carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ColorBean> findC() {
        return colorMapper.selectByExample(null);
    }

    @Override
    public void addCar(CarBean carBean) {
        carMapper.insertSelective(carBean);
    }

    @Override
    public List<CityBean> getCityListByPid(Integer pid){
        CityBeanExample example=new CityBeanExample();
        CityBeanExample.Criteria criteria=example.createCriteria();
        criteria.andPidEqualTo(pid);
        return cityMapper.selectByExample(example);
    }


}
