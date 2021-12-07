package com.mmall.dao;

import com.mmall.model.SysDept;

import java.util.List;

// 自动生成的
public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    // 实现的时候插入所有字段
    int insert(SysDept record);

    // 判断，只插入有值的值，没有传的值不会传
    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();
}