package com.dlmu.circle.dao;

import com.dlmu.circle.model.Grade;
import com.dlmu.circle.model.PageBean;
import com.dlmu.circle.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by cf on 2017/3/21.
 */
public class gradeDao {
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_grade");
        if(grade!=null && StringUtil.isNotEmpty(grade.getGradeName())){
            sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }
    public int gradeCount(Connection con,Grade grade) throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from t_grade");
        if(StringUtil.isNotEmpty(grade.getGradeName())){
            sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }

    }

    //批量删除记录
    public int gradeDelete(Connection con,String delIds)throws Exception{
        String sql="delete from t_grade where id in("+delIds+")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public int gradeAdd(Connection con,Grade grade) throws Exception{
        String sql="insert into t_grade values(null,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        return pstmt.executeUpdate();
    }
    public int gradeUpdate(Connection con,Grade grade)throws Exception{
        String sql="update t_grade set gradeName=?,gradeDesc=? where id=?";
        PreparedStatement psmt=con.prepareStatement(sql);
        psmt.setString(1,grade.getGradeName());
        psmt.setString(2,grade.getGradeDesc());
        psmt.setInt(3,grade.getId());
        return psmt.executeUpdate();
    }
}
