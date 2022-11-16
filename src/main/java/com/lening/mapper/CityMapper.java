package com.lening.mapper;

import com.lening.entity.CityBean;
import com.lening.entity.CityBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {
    long countByExample(CityBeanExample example);

    int deleteByExample(CityBeanExample example);

    int insert(CityBean record);

    int insertSelective(CityBean record);

    List<CityBean> selectByExample(CityBeanExample example);

    int updateByExampleSelective(@Param("record") CityBean record, @Param("example") CityBeanExample example);

    int updateByExample(@Param("record") CityBean record, @Param("example") CityBeanExample example);
}