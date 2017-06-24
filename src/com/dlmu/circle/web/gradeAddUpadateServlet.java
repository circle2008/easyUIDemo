package com.dlmu.circle.web;

import com.dlmu.circle.dao.gradeDao;
import com.dlmu.circle.model.Grade;
import com.dlmu.circle.util.DbUtil;
import com.dlmu.circle.util.StringUtil;
import com.dlmu.circle.util.responseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by cf on 2017/3/23.
 */
public class gradeAddUpadateServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    gradeDao gradeDao=new gradeDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //乱码解决方案
        req.setCharacterEncoding("utf-8");
        String gradeName=req.getParameter("gradeName");
        String gradeDesc=req.getParameter("gradeDesc");
        String id=req.getParameter("id");
        Grade grade=new Grade(gradeName,gradeDesc);
        if(StringUtil.isNotEmpty(id)){
            grade.setId(Integer.parseInt(id));
        }
        Connection con=null;
        try {
            con=dbUtil.getCon();
            int saveNums=0;
            JSONObject result=new JSONObject();
            if(StringUtil.isNotEmpty(id)){
                saveNums=gradeDao.gradeUpdate(con,grade);
            }else {
                saveNums=gradeDao.gradeAdd(con,grade);
            }
            if(saveNums>0){
                result.put("success", "true");
            }else {
                //技术上设置为true，为了实现前端的api
                result.put("success", "true");
                result.put("errorMsg","保存信息失败");
            }
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
