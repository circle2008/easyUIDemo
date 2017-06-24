package com.dlmu.circle.web;

import com.dlmu.circle.dao.studentDao;
import com.dlmu.circle.model.Student;
import com.dlmu.circle.util.DateUtil;
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
import java.text.ParseException;

/**
 * Created by cf on 2017/3/27.
 */
public class studentAddUpadateServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    studentDao studentDao=new studentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String stuNo=req.getParameter("stuNo");
        String stuName=req.getParameter("stuName");
        String sex=req.getParameter("sex");
        String birthday=req.getParameter("birthday");
        String gradeId=req.getParameter("gradeId");
        String email=req.getParameter("email");
        String stuDesc=req.getParameter("stuDesc");
        String stuId=req.getParameter("stuId");
        Student student=null;
        try {
           student=new Student(stuNo,stuName,sex, DateUtil.formatString(birthday,"yyyy-MM-dd"),Integer.parseInt(gradeId),email,stuDesc);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(StringUtil.isNotEmpty(stuId)){
            student.setStuId(Integer.parseInt(stuId));
        }
        Connection con=null;
        try {
            con=dbUtil.getCon();
            int saveNums=0;
            JSONObject result=new JSONObject();
            if(StringUtil.isNotEmpty(stuId)){
                saveNums=studentDao.studentUpadate(con,student);
            }else {
                saveNums=studentDao.studentAdd(con,student);
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
