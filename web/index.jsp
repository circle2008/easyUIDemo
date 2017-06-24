<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2017/3/20
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">
      function resetValue() {
        document.getElementById("userName").value="";
        document.getElementById("password").value="";
      }
    </script>
  </head>
  <body>
  <div align="center" style="padding-top: 50px;">
    <form action="login" method="post">
      <table  width="740" height="500" background="images/login.jpg" >
        <tr height="180">
          <td colspan="4"></td>
        </tr>
        <tr height="10">
          <td width="40%"></td>
          <td width="10%">用户名：</td>
          <td><input type="text" value="${userName}" name="userName" stuId="userName"/></td>
          <td width="30%"></td>
        </tr>
        <tr height="10">
          <td width="40%"></td>
          <td width="10%">密  码：</td>
          <td><input type="password" value="${password}" name="password" stuId="password"/></td>
          <td width="30%"></td>
        </tr>
        <tr height="10">
          <td width="40%"></td>
          <td width="10%"><input type="submit" value="登录"/></td>
          <td><input type="button" value="重置" onclick="resetValue()"/></td>
          <td width="30%"></td>
        </tr>
        <tr height="10">
          <td width="40%"></td>
          <td colspan="3">
            <font color="red">${error }</font>
          </td>
        </tr>
        <tr >
          <td></td>
        </tr>
      </table>
    </form>
  </div>
  </body>
</html>
