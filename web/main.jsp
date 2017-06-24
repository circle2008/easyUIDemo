<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2017/3/20
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            var dataTree=[{
               text:"全部信息",
                children:[{
                    text:"班级管理信息",
                    attributes:{
                        url:"gradeInfoManage.jsp"
                    }
                },{
                    text:"学生信息管理",
                    attributes:{
                        url:"studentInfoManage.jsp"
                    }
                }]

            }];
            //实例化树菜单
            $("#tree").tree({
                data:dataTree,
                lines:true,
                onClick:function (node) {
                    if(node.attributes){
                        openTab(node.text,node.attributes.url)
                    }
                }
                    }
            )
            //实例化tab
            function openTab(text,url) {
                //判断标签页是否被打开
                if($("#tabs").tabs('exists',text)){
                    //如果存在，则自动选中
                    $("#tabs").tabs('select',text)
                }else {
                    var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"><iframe>";
                    $("#tabs").tabs('add',{
                        title:text,
                        closable:true,
                        content:content
                    });
                }

            }

        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFF">
    <div align="left" style="width: 80%;float: left"><img src="images/main.jpg"></div>
    <div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.userName }</font></div>

</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" >
            <div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;" title="导航菜单" split="true">
    <ul id="tree"></ul>
</div>
 <div data-options="region:'south'" style="height:25px;" align="center">
            design by circle
 </div>
    
</body>
</html>
