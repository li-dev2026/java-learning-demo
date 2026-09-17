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

@WebServlet("/detail")
public class DeptDetailServlet extends HttpServlet {

    private DeptDao dao = new DeptDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //发送的请求是什么格式？/dept/detail?id=10
        //获取部门编号
        // 新内容：怎么获取前端提交的数据？找request对象要这个数据。
        //request 表示请求，这个对象封装了所有请求提交的数据。这个对象封装了HTTP请求协议的所有内容。
        //这个方法的返回值类型永远是String类型
        String id = request.getParameter("id");
        //将String类型的id转换成Integer，因为DAO中需要Integer类型的参数
        Integer deptNo = Integer.valueOf(id);

        //获取部门信息
        Dept dept = dao.selectById(deptNo);

        //动态打印网页
        response.setContentType("text/html;cahrset=utf-8");
        PrintWriter out = response.getWriter();

        out.print("""
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>部门管理系统 - 部门详情</title>
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
                        .detail-card {
                            padding: 20px;
                            border-radius: 6px;
                            background-color: #f8f9fa;
                        }
                        .detail-row {
                            display: flex;
                            margin-bottom: 15px;
                            padding-bottom: 15px;
                            border-bottom: 1px solid #e9ecef;
                        }
                        .detail-row:last-child {
                            margin-bottom: 0;
                            padding-bottom: 0;
                            border-bottom: none;
                        }
                        .detail-label {
                            width: 150px;
                            font-weight: 600;
                            color: #495057;
                        }
                        .detail-value {
                            flex: 1;
                            color: #212529;
                        }
                        .action-btns {
                            margin-top: 30px;
                            text-align: right;
                        }
                        .edit-btn {
                            padding: 10px 20px;
                            background-color: #f0ad4e;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            text-decoration: none;
                            font-size: 14px;
                            transition: background-color 0.3s;
                        }
                        .edit-btn:hover {
                            background-color: #eea236;
                        }
                    </style>
                </head>
                """);
                //获取应用的根路径
                    String contextPath = request.getContextPath();
                    out.print("<body>");
                out.print("<div class='container'>");
                out.print("<div class='header'>");
                out.print("<h1>部门详细信息</h1>");
                out.print("<a href='"+contextPath+"/list' class='back-btn'>返回列表</a>");
                out.print("</div>");
                out.print("<div class='detail-card'>");
                out.print("<div class='detail-row'>");
                out.print("<div class='detail-label'>部门编号</div>");
                out.print("<div class='detail-value'>"+dept.getDeptNo()+"</div>");
                out.print("</div>");
                out.print("<div class='detail-row'>");
                out.print("<div class='detail-label'>部门名称</div>");
                out.print("<div class='detail-value'>"+dept.getdName()+"</div>");
                out.print("</div>");
                out.print("<div class='detail-row'>");
                out.print("<div class='detail-label'>部门地理位置</div>");
                out.print("<div class='detail-value'>"+dept.getLoc()+"</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("<div class='action-btns'>");
                out.print("<a href='"+contextPath+"/edit?id="+dept.getDeptNo()+"' class='edit-btn'>编辑部门信息</a>");
                out.print("</div>");
                out.print("</div>");
                out.print("</body>");
                out.print("</html>");

    }
}
