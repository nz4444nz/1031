package com.lening.mapper;

import com.lening.entity.ColorBean;
import com.lening.entity.ColorBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColorMapper {
    long countByExample(ColorBeanExample example);

    int deleteByExample(ColorBeanExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(ColorBean record);

    int insertSelective(ColorBean record);

    List<ColorBean> selectByExample(ColorBeanExample example);

    ColorBean selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") ColorBean record, @Param("example") ColorBeanExample example);

    int updateByExample(@Param("record") ColorBean record, @Param("example") ColorBeanExample example);

    int updateByPrimaryKeySelective(ColorBean record);

    int updateByPrimaryKey(ColorBean record);
}