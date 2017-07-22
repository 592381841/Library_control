<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>密码修改</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="container-fluid" style="padding-top: 50px;">
  		<div class="row">
  			<div class="col-sm-6 col-sm-offset-3">
  				<form id="myform" class="form-horizontal">
  <div class="form-group">
    <label for="oldpasswd" class="col-sm-4 control-label">原密码</label>
    <div class="col-sm-8">
      <input type="password" class="form-control" name="oldPasswd" id="oldpasswd" placeholder="原密码">
    </div>
  </div>
  <div class="form-group">
    <label for="newpasswd" class="col-sm-4 control-label">新密码</label>
    <div class="col-sm-8">
      <input type="password" class="form-control" id="newpasswd" name="newPasswd" placeholder="新密码">
    </div>
  </div>
    <div class="form-group">
    <label for="newpasswdagain" class="col-sm-4 control-label">再次输入新密码</label>
    <div class="col-sm-8">
      <input type="password" class="form-control" id="newpasswdagain" name="newpasswdagain" placeholder="再次输入密码新密码">
    </div>
  </div>

 <div class="form-group">
    <label for="newpasswdagain" class="col-sm-4 control-label sr-only">提示</label>
    <div class="col-sm-8">
      <font color="#EA2000" id="info" ></font>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-9 col-sm-3">
      <a onclick="modify_passwd()"  class="btn btn-primary">修改密码</a>
    </div>
  </div>
</form>
  				
  			</div>
  			
  		</div>
  		
  	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script type="text/javascript">
    function modify_passwd(){
    	var newPasswd=$("#newpasswd").val().trim();
    	var newpasswdagain=$("#newpasswdagain").val().trim();
    	var oldpasswd=$("#oldpasswd").val().trim();
    	
    	if(newPasswd == null || newPasswd == undefined || newPasswd == ''||newpasswdagain == null || newpasswdagain == undefined || newpasswdagain == ''||oldpasswd == null || oldpasswd == undefined || oldpasswd == ''){
    		$("#info").text("请正确填写资料");
    	$("#newpasswd").val("");
       $("#newpasswdagain").val("");
        	$("#oldpasswd").val("");
    	}
    	else if(newPasswd==newpasswdagain){
    	
    	
    $.ajax({
    	url:"<c:url value='/admin/general/json_modify_passwd.action' />",
		type:"POST",
		data:$("#myform").serialize(),
		async:true,
		cache:false,
		 dataType:"json",
		success:function(data){	
			var json=eval(data);
			if(json.result){
				alert(json.info);
				window.top.location='<c:url value='/common/logout.action'/>';
			}
			else{
				$("#info").text(json.info);
			 	$("#newpasswd").val("");
			       $("#newpasswdagain").val("");
			        	$("#oldpasswd").val("");
				
			}
	
		
		},
		error: function(){	
			window.top.location='<c:url value='/error.jsp'/>';
		}
    })
    	}
    	else{
    	 	$("#newpasswd").val("");
    	       $("#newpasswdagain").val("");
    	        	$("#oldpasswd").val("");
    		$("#info").text("两次输入密码不一致。。。");
    	}
    }
    </script>
  </body>
</html>