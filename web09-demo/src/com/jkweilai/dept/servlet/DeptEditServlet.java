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
import java.io.PrintWriter;

@WebServlet("/edit")
public class DeptEditServlet extends HttpServlet {

    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取id
        String id = request.getParameter("id");
        Integer deptNo = Integer.valueOf(id);
        //根据id查询部门信息
        Dept dept = dao.selectById(deptNo);
        //动态展示修改页面
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("""
                <!DOCTYPE html>
                <html lang='zh-CN'>
                <head>
                    <meta charset='UTF-8'>
                    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                    <title>部门管理系统 - 修改部门</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                            font-family: 'Arial', sans-serif;
                        }
                        body {
                            background-color: #f5f5f5;
                        }
                        .container {
                            max-width: 800px;
                            margin: 30px auto;
                            padding: 30px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                        }
                        .header {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            margin-bottom: 30px;
                            padding-bottom: 15px;
                            border-bottom: 1px solid #eee;
                        }
                        .header h1 {
                            color: #333;
                            font-size: 24px;
                        }
                        .back-btn {
                            padding: 8px 16px;
                            background-color: #6c757d;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            text-decoration: none;
                            font-size: 14px;
                            transition: background-color 0.3s;
                        }
                        .back-btn:hover {
                            background-color: #5a6268;
                        }
                        .form-group {
                            margin-bottom: 20px;
                        }
                        .form-group label {
                            display: block;
                            margin-bottom: 8px;
                            color: #555;
                            font-weight: 500;
                        }
                        .form-group input, .form-group select {
                            width: 100%;
                            padding: 12px;
                            border: 1px solid #ddd;
                            border-radius: 4px;
                            font-size: 16px;
                            transition: border-color 0.3s;
                        }
                        .form-group input:focus, .form-group select:focus {
                            border-color: #4a90e2;
                            outline: none;
                        }
                        .form-group input[readonly] {
                            background-color: #f8f9fa;
                            color: #6c757d;
                        }
                        .btn-group {
                            display: flex;
                            justify-content: space-between;
                            margin-top: 30px;
                            padding-top: 15px;
                            border-top: 1px solid #eee;
                        }
                        .submit-btn {
                            padding: 12px 24px;
                            background-color: #4a90e2;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 16px;
                            transition: background-color 0.3s;
                        }
                        .submit-btn:hover {
                            background-color: #3a7bc8;
                        }
                        .cancel-btn {
                            padding: 12px 24px;
                            background-color: #6c757d;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 16px;
                            transition: background-color 0.3s;
                            text-decoration: none;
                        }
                        .cancel-btn:hover {
                            background-color: #5a6268;
                        }
                    </style>
                </head>
                <body>
                    <div class='container'>
                        <div class='header'>
                            <h1>修改部门信息</h1>
        """);
        String contextPath = request.getContextPath();
        out.print("<a href='"+contextPath+"/list' class='back-btn'>返回列表</a></div>");
        out.print(" <form action='"+contextPath+"/update' method='post'>");
        out.print(""" 
                            <div class='form-group'>
                                <label for='deptId'>部门编号</label>
        """);
        out.print("<input type='text' id='deptId' name='deptId' value='"+dept.getDeptNo()+"' readonly>");
        out.print("""
                            </div>
                            <div class='form-group'>
                                <label for='deptName'>部门名称</label>
        """);
        out.print("<input type='text' id='deptName' name='deptName' value='"+dept.getdName()+"' required>");
        out.print("""
        
                            </div>
                            <div class='form-group'>
                                <label for='location'>部门地理位置</label>
        """);
        out.print("<input type='text' id='location' name='location' value='"+dept.getLoc()+"' required>");
        out.print("""
                            </div>
                
                            <div class='btn-group'>
                """);
        out.print("<a href='"+contextPath+"/list' class='cancel-btn'>取消</a>");
        out.print("""
                                <button type='submit' class='submit-btn'>保存更改</button>
                            </div>
                        </form>
                    </div>
                </body>
                </html>
                """);
    }
}
