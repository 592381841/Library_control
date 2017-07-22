<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html class="">
    <head>
        <title>error</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet" media="screen"
	href="<c:url value='/lib/css/bootstrap.min.css' />">
<link rel="stylesheet" media="screen"
	href="<c:url value='/lib/css/bootstrap-theme.min.css' />">


<!-- Bootstrap Admin Theme -->
<link rel="stylesheet" media="screen"
	href="<c:url value='/lib/css/bootstrap-admin-theme.css' />">
<!-- <link rel="stylesheet" media="screen" -->
<%-- 	href="<c:url value='/lib/css/bootstrap-admin-theme-change-size.css' />"> --%>

        <!-- Bootstrap Error Page -->
<%--         <link rel="stylesheet" media="screen" href="<c:url value='/lib/css/bootstrap-error-page.css' />"> --%>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
           <script type="text/javascript" src="js/html5shiv.js"></script>
           <script type="text/javascript" src="js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <!-- content -->
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-lg-12">
                    <div class="centering text-center">
                        <div class="text-center">
                            <h2 class="without-margin">Don't worry. It's <span class="text-danger"><big>page</big></span> error only.</h2>
<%--                             <s:debug></s:debug> --%>
                            <h4 class="text-danger">点击下面，返回主界面</h4>
                        </div>
<%--                         <s:debug></s:debug> --%>
                        <hr>
                        <ul class="pager">
                            <li><a onclick="forwardLogin()" href="">返回主页</a></li>
                        </ul>
                        
                        <s:property value="exception"/>
<s:property value="exceptionStack"/>
                        
                    </div>
                </div>
            </div>
        </div>

       <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
   	<script type="text/javascript">
   	
   	function forwardLogin(){
   		window.top.location.href="<c:url value='/login.jsp' />";
   	}
   	
   		
   	
   	</script>
    </body>
</html>

