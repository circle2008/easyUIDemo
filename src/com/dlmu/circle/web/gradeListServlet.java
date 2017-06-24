package com.dlmu.circle.web;

import com.dlmu.circle.dao.gradeDao;
import com.dlmu.circle.model.Grade;
import com.dlmu.circle.model.PageBean;
import com.dlmu.circle.util.DbUtil;
import com.dlmu.circle.util.JsonUtil;
import com.dlmu.circle.util.responseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by cf on 2017/3/21.
 */
public class gradeListServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    gradeDao gradeDao=new gradeDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页
        String page=req.getParameter("page");
        String rows=req.getParameter("rows");
        String className=req.getParameter("gradeName");
        if(className==null){
            className="";
        }
        Grade grade=new Grade();
        grade.setGradeName(className);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try {
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con,pageBean,grade));
            int total=gradeDao.gradeCount(con,grade);
            result.put("rows",jsonArray);
            result.put("total",total);
            responseUtil.write(resp,result);

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
