package com.dlmu.circle.web;

import com.dlmu.circle.dao.gradeDao;
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
 * Created by cf on 2017/3/25.
 */
public class gradeComboListServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    gradeDao gradeDao=new gradeDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con=null;
        try {
            con=dbUtil.getCon();
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", "");
            jsonObject.put("gradeName", "请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, null,null)));
            responseUtil.write(resp, jsonArray);
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
