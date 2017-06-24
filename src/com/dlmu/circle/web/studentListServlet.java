package com.dlmu.circle.web;

import com.dlmu.circle.dao.studentDao;
import com.dlmu.circle.model.PageBean;
import com.dlmu.circle.model.Student;
import com.dlmu.circle.util.DbUtil;
import com.dlmu.circle.util.JsonUtil;
import com.dlmu.circle.util.StringUtil;
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
 * Created by cf on 2017/3/25.
 */
public class studentListServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    studentDao studentDao=new studentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页
        String page=req.getParameter("page");
        String rows=req.getParameter("rows");
        String stuNo=req.getParameter("stuNo");
        String stuName=req.getParameter("stuName");
        String sex=req.getParameter("sex");
        String bbirthday=req.getParameter("bbirthday");
        String ebirthday=req.getParameter("ebirthday");
        String gradeId=req.getParameter("gradeId");

        Student student=new Student();
        if(stuNo!=null){
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if(StringUtil.isNotEmpty(gradeId)){
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }

        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean,student,bbirthday,ebirthday));
            int total=studentDao.studentCount(con,student,bbirthday,ebirthday);
            result.put("rows", jsonArray);
            result.put("total", total);
            responseUtil.write(resp,result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
