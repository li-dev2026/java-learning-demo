package com.jkweilai.dept.servlet;

import com.jkweilai.dept.dao.DeptDao;
import com.jkweilai.dept.dao.impl.DeptDaoImpl;
import com.jkweilai.dept.entity.Dept;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save")
public class DeptSaveServlet extends HttpServlet {

    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post请求，在在请求体中提的交数据
        //获取当前部门编号（最大值）
        Integer max = dao.selectMaxId();
        Integer deptNo = max + 1;

        //获取表单提交的数据
        String deptName = request.getParameter("deptName");
        String location = request.getParameter("location");

        //封装一个对象
        Dept dept = new Dept();
        dept.setDeptNo(deptNo);
        dept.setdName(deptName);
        dept.setLoc(location);

        //调用dao保存数据
        int count = dao.insert(dept);
        if(count>0){
            //保存成功之后重定向到 list列表页面
            response.sendRedirect(request.getContextPath()+"/list");
        }
    }
}
