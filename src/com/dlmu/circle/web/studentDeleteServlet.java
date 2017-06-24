package com.dlmu.circle.web;

import com.dlmu.circle.dao.studentDao;
import com.dlmu.circle.util.DbUtil;
import com.dlmu.circle.util.responseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by cf on 2017/3/26.
 */
public class studentDeleteServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    studentDao studentDao=new studentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delIds=req.getParameter("delIds");
        Connection con=null;
        try {
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=studentDao.studentDelete(con,delIds);
            if(delNums>0){
                result.put("success", "true");
                result.put("delNums", delNums);
            }else {
                result.put("errorMsg","删除失败");
            }
            responseUtil.write(resp,result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
