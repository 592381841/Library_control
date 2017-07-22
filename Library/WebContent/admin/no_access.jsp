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
    <title>无权限</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">
<style type="text/css">
	  
	  	  #contain{
		  border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
		  box-shadow: 10px 10px 5px #888888;
		 
	  }
	@font-face{
				font-family: titie_1;
					src:url('<c:url value='/lib/fonts/fanti_title.ttf' />');
			}
	  
	  
	  </style>
  </head>
  <body>
  	<div id="contain" class="container-fluid">
  	
  	
  	
  		<div class="row">
  			<h1 style="text-align: center;">您无此权限</h1>
  			
  			</div>
  		
  	</div>

 <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
  </body>
</html>