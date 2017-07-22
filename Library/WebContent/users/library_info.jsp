<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>图书馆简介</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <style type="text/css">
	
	@font-face{
				font-family: titie_1;
					src:url('<c:url value='/lib/fonts/fanti_title.ttf' />');
			}
	  
	  #contain{
		  border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
		  box-shadow: 10px 10px 5px #888888;
	  }
	</style>
  <body>
  	<div id="contain" class="container-fluid">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
       <div class="col-sm-6 col-sm-offset-3">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">${inf.library_name }</font>
   
       </div>
    
       
       </div>
       <div class="row">
       	
       	   <div class="col-sm-4 col-sm-offset-8">
       	<font  style="text-align: center; font-family:titie_1;text-align: right;font-size: 15px; color: #e31510 "> 更新时间：${inf.modify_time }</font>
       </div>
       	
       </div>
       	<div class="row"> 
       	
       	       <div class="panel panel-info">
                                <div class="panel-heading">
                                    <div class="text-muted bootstrap-admin-box-title" style="font-family:titie_1; font-size: 20px ">简介</div>
                                </div>
                                <div class="bootstrap-admin-panel-content">
                                   <div class="jumbotron">
  <h5>${inf.library_intro } </h5>
</div>
                                    <table class="table">
                                        
                                        <tbody>
                                            <tr>
                                                <td>馆长姓名</td>
                                                <td>${inf.librarian_name}</td>
                                                
                                            </tr>
                                            <tr>
                                                <td>馆长电话</td>
                                                <td>${inf.librarian_phone}</td>
                                             
                                            </tr>
                                            <tr>
                                                <td>馆长邮箱</td>
                                                <td>${inf.librarian_email}</td>
                                            
                                            </tr>
                                               <tr>
                                                <td>馆长办公地址</td>
                                                <td>${inf.librarian_address}</td>
                                               
                                            </tr>
                                               <tr>
                                                <td>建馆时间</td>
                                                <td>${inf.library_time}</td>
                                           
                                            </tr>
                                         
                                        </tbody>
                                    </table>
                                </div>
                                
                                

                            </div>
       	
       	
       	</div>
  			
  		</div>
  			
  			
  		</div>
  		
  	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
  </body>
</html>