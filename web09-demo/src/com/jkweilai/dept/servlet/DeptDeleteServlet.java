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

@WebServlet("/delete")
public class DeptDeleteServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户提交的要删除的部门编号。
        String id = request.getParameter("id");
        Integer deptNo = Integer.valueOf(id);
        //删除
        int count = dao.deleteById(deptNo);
        if(count>0){
            //重新查询数据库展示列表。
            //让浏览器重新发送一次全新的请求。
            //请求的路径是：http://localhost:8080/dept/list
            //重点中的重点：将路径响应给浏览器，浏览器拿到请求路径后，自发的自动的再重新向服务器发送请求
            //response.sendRedirect("http://localhost:8080/dept/list");
            //http://localhost:8080 可以省略。【注意：项目的根路径不能省略。你就看成超链接就行】
            //response.sendRedirect("/dept/list");
            response.sendRedirect(request.getContextPath()+"/list");
        }
    }
}
