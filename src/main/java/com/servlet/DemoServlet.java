package com.servlet;

import com.servlet.db.bean.UserBean;
import com.servlet.db.dao.IDao;
import com.servlet.db.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("acc");
        String pass = req.getParameter("pass");
        String result = "数据库读取异常";
        if (account == null || account.isEmpty()){
            result = "用户名为空";
        } else if (pass == null || pass.isEmpty()){
            result = "密码为空";
        } else {
            try {
                UserBean user = UserDaoImpl.getInstance().getUserByName(account);
                if (user == null){
                    result = "不存在此用户";
                } else {
                    if (pass.endsWith(user.getUserPwd())){
                        result  = "登录成功";
                    } else {
                        result = "用户名密码不匹配，登录失败";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(result);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
