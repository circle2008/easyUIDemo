package com.dlmu.circle.web;

import com.dlmu.circle.dao.userDao;
import com.dlmu.circle.model.User;
import com.dlmu.circle.util.DbUtil;
import com.dlmu.circle.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
/**
 * Created by cf on 2017/3/20.
 */
public class loginServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    userDao userDao=new userDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        req.setAttribute("userName",userName);
        req.setAttribute("password",password);
        if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
            req.setAttribute("error","用户名或密码不能为空");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }
        Connection con=null;
        User user=new User(userName,password);
        try {
            con=dbUtil.getCon();
            User currentUser=userDao.login(con,user);
            if(currentUser!=null){
                HttpSession session=req.getSession();
                session.setAttribute("currentUser",currentUser);
                resp.sendRedirect("main.jsp");
            }else {
                req.setAttribute("error","用户名或密码错误");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
