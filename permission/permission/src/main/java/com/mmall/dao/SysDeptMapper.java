package com.mmall.dao;

import com.mmall.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 自动生成的
public interface SysDeptMapper {
    // @Param是给数据库里的field设置一个名字，在SysDeptMapper.xml里调用sql语句
    int deleteByPrimaryKey(@Param("id") Integer id);

    // 实现的时候插入所有字段
    int insert(SysDept record);

    // 判断，只插入有值的值，没有传的值不会传
    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();

    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    // 批量更新level
    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    // parentId设置为Integer不是int的原因是sql可以判断!null
    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);
}