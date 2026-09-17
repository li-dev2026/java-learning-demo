package com.jkweilai.dept.dao;

import com.jkweilai.dept.entity.Dept;

import java.util.List;

/**
 * 专门完成增删改查的DAO接口
 * 给业务层提供CRUD的接口
 * 为什么定义接口？让DAO层和业务层解耦合。
 * DAO中不能定义业务代码，只能是单纯的CRUD操作
 */
public interface DeptDao {

    /**
     * 保存部门
     * @param dept
     * @return
     */
    int insert(Dept dept);

    /**
     * 根据id删除部门信息
     * @param deptNo
     * @return
     */
    int deleteById(Integer deptNo);

    /**
     * 修改部门信息
     * @param newDept
     * @return
     */
    int update(Dept newDept);

    /**
     * 根据编号查询部门信息
     * @param deptNo
     * @return
     */
    Dept selectById(Integer deptNo);

    /**
     * 查询所有部门信息返回部门列表
     * @return
     */
    List<Dept> selectAll();

    /**
     * 找出最大的部门编号
     * @return
     */
    Integer selectMaxId();
}
