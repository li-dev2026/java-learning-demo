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

@WebServlet("/update")
public class DeptUpdateServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单提交的数据
        String deptId = request.getParameter("deptId");
        String deptName = request.getParameter("deptName");
        String location = request.getParameter("location");
        //封装对象
        Dept dept = new Dept(Integer.valueOf(deptId),deptName,location);
        //更新
        int count = dao.update(dept);
        if(count>0){
            response.sendRedirect(request.getContextPath()+"/list");
        }
    }
}
