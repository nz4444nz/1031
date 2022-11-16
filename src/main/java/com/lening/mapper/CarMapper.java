package com.lening.mapper;

import com.lening.entity.CarBean;
import com.lening.entity.CarBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    long countByExample(CarBeanExample example);

    int deleteByExample(CarBeanExample example);

    int deleteByPrimaryKey(Integer carid);

    int insert(CarBean record);

    int insertSelective(CarBean record);

    List<CarBean> selectByExample(CarBeanExample example);

    CarBean selectByPrimaryKey(Integer carid);

    int updateByExampleSelective(@Param("record") CarBean record, @Param("example") CarBeanExample example);

    int updateByExample(@Param("record") CarBean record, @Param("example") CarBeanExample example);

    int updateByPrimaryKeySelective(CarBean record);

    int updateByPrimaryKey(CarBean record);
}