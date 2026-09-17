package com.jkweilai.dept.dao.impl;

import com.jkweilai.dept.dao.DeptDao;
import com.jkweilai.dept.entity.Dept;
import com.jkweilai.dept.util.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 注意：正常来说，不应该在DAO中关闭连接对象，因为还要再业务层控制事务。
 * 控制事务的过程可能多次调用不同的DAO方法。这些DAO方法必须放在同一个
 * 事务当中，也就是说，这些再同一个事务中的DAO方法，应该共享同一个连接对象。
 *
 *
 * 以下代码，我们没有按照上述的描述进行，在DAO中关闭了连接池对象（实际来说是不对的）
 */
public class DeptDaoImpl implements DeptDao {
    @Override
    public int insert(Dept dept) {
        String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
        int count = 0;
        try(Connection conn = DbUtils.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,dept.getDeptNo());
            ps.setString(2,dept.getdName());
            ps.setString(3, dept.getLoc());
            count = ps.executeUpdate();

        }catch (Exception e){
            //正常来说，不能在DAO中吞没异常。
            //只要异常发生，必须将异常上抛给调用者
            //因为调用者要控制事务
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return count;
    }

    @Override
    public int deleteById(Integer deptNo) {
        String sql = "delete from dept where deptno = ?";
        int count = 0;
        try(Connection conn = DbUtils.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,deptNo);
            count = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return count;
    }

    @Override
    public int update(Dept newDept) {
        String sql = "update dept set dname = ?,loc = ? where deptno = ?";
        int count = 0;
        try(Connection conn = DbUtils.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,newDept.getdName());
            ps.setString(2,newDept.getLoc());
            ps.setInt(3,newDept.getDeptNo());
            count = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return count;
    }

    @Override
    public Dept selectById(Integer deptNo) {
        String sql = "select deptno,dname,loc from dept where deptno = ?";
        Dept dept = null;
        try(Connection conn = DbUtils.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,deptNo);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    dept = new Dept();
                    dept.setDeptNo(rs.getInt("deptno"));
                    dept.setdName(rs.getString("dname"));
                    dept.setLoc(rs.getString("loc"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
            return dept;
    }

    @Override
    public List<Dept> selectAll() {
        String sql = "select deptno,dname,loc from dept";
        List<Dept> deptList = new ArrayList<>();
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

                while (rs.next()){
                    Dept dept = new Dept();
                    dept.setDeptNo(rs.getInt("deptno"));
                    dept.setdName(rs.getString("dname"));
                    dept.setLoc(rs.getString("loc"));
                    deptList.add(dept);

            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return deptList;
    }

    @Override
    public Integer selectMaxId() {
        String sql = "select max(deptno) from dept";
        Integer maxDeptNo = null;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    maxDeptNo = rs.getInt(1);
                }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return maxDeptNo;
    }
}

