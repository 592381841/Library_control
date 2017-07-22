<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>读者主页</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap Docs -->
<!--        <link href="http://getbootstrap.com/docs-assets/css/docs.css" rel="stylesheet" media="screen">-->

        <!-- Bootstrap --> 
        <link rel="stylesheet" media="screen" href="<c:url value='/lib/css/bootstrap.min.css' />">
<!--        <link rel="stylesheet" media="screen" href="css/bootstrap-theme.min.css">-->

        <!-- Bootstrap Admin Theme -->
<!--        <link rel="stylesheet" media="screen" href="css/bootstrap-admin-theme.css">-->
<!--        <link rel="stylesheet" media="screen" href="css/bootstrap-admin-theme-change-size.css">-->

        <!-- Custom styles -->
        <style type="text/css">
			@font-face{
				font-family: titie_1;
					src:url('<c:url value='/lib/fonts/fanti_title.ttf' />')
			}
			body{
				padding-top: 0px;
			}
            @font-face {
                font-family: Ubuntu;
                src: url('<c:url value='/lib/fonts/Ubuntu-Regular.ttf' />');
            }
			
            .bs-docs-masthead{
                background-color: #6f5499;
                background-image: linear-gradient(to bottom, #563d7c 0px, #6f5499 100%);
                background-repeat: repeat-x;
            }
            .bs-docs-masthead{
                padding: 0;
            }
            .bs-docs-masthead h1{
                color: #fff;
                font-size: 40px;
                margin: 0;
                padding: 34px 0;
                text-align: center;
            }
            .bs-docs-masthead a:hover{
                text-decoration: none;
            }
            .meritoo-logo a{
                background-color: #fff;
                border: 1px solid rgba(66, 139, 202, 0.4);
                display: block;
                font-family: Ubuntu;
                padding: 22px 0;
                text-align: center;
            }
            .meritoo-logo a,
            .meritoo-logo a:hover,
            .meritoo-logo a:focus{
                text-decoration: none;
            }
            .meritoo-logo a img{
                display: block;
                margin: 0 auto;
            }
            .meritoo-logo a span{
                color: #4e4b4b;
                font-size: 18px;
            }
            .row-urls{
                margin-top: 4px;
            }
            .row-urls .col-md-6{
                text-align: center;
            }
            .row-urls .col-md-6 a{
                font-size: 14px;
            }
			.nav-child:hover{
				background-color: #15B8CF;
			}
        </style>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
           <script type="text/javascript" src="js/html5shiv.js"></script>
           <script type="text/javascript" src="js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
				<div class="container-fluid" style="background-color: #DFF3FE"> 
      			<div class="row">      
       <div class="col-lg-4" style="text-align:left;font-size:50px;font-family:titie_1; "><font style="color: #e31510 ">${li_name }</font>
       </div>
       
             <div style="align-content: " class="col-lg-8">
                        <div class="collapse navbar-collapse">
                     
                            <ul class="nav navbar-nav navbar-right ">
                                
                                <li class="nav-child">
                                    <a target="iframe1" href="<c:url value='/users/modify_passwd.jsp' />" ><font>修改密码</font> <i class="glyphicon glyphicon-pencil"></i></a>
                                </li>
                                  <li class="nav-child">
                                    <a  href="<c:url value='/common/logout.action' />"  ><font>退出登录 </font><i class="glyphicon glyphicon-share-alt"></i></a>
                                </li>
                                <li class="nav-child">
                                    <a target="iframe1" href="<c:url value='/users/reader_info.jsp' />" ><font>${reader.usersName }</font> <i class="glyphicon glyphicon-user"></i></a>
                                </li>
                              
                       
                            </ul>
                        </div>
                    </div>
       </div>
       </div>

       
  

        <!-- main / large navbar -->
        <nav class="navbar navbar-default navbar-inverse  bootstrap-admin-navbar" role="navigation">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-navbar-collapse">
                                <span class="sr-only"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            
                            
                        </div>
                        <div class="collapse navbar-collapse main-navbar-collapse">
                            <ul class="nav navbar-nav">
                            <li><a target="iframe1"  href="<c:url value='/users/jump_keyword_search.action' />">目录检索</a></li>
                                <li> <a target="iframe1" href="<c:url value='/users/jump_type_search.action' />">分类检索</a></li>
                                <li class="dropdown">
                                    <a onclick="" class="dropdown-toggle" data-hover="dropdown">图书借阅 <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                       
                                        <li><a target="iframe1" href="<c:url value='/users/jump_borrow_status.action' />">当前图书借阅情况</a></li>
                                        <li><a target="iframe1" href="<c:url value='/users/jump_borrow_history.action' />">我的借书历史</a></li>
<!--
                                        <li><a href="#">Something else here</a></li>
                                        <li role="presentation" class="divider"></li>
                                        <li role="presentation" class="dropdown-header">Dropdown header</li>
                                        <li><a href="#">Separated link</a></li>
                                        <li><a href="#">One more separated link</a></li>
-->
                                    </ul>
                                </li>
                                 <li><a target="iframe1" href="<c:url value='/users/reader_info.jsp' />">个人信息</a></li>
                                 <li><a target="iframe1" href="<c:url value='/users/jump_library_info.action' />">图书馆简介</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>

        <div class="container">
            <!-- left, vertical navbar & content -->
            <div class="row">


                <!-- content -->
                <div class="col-md-10 col-md-offset-1">
 <iframe name="iframe1" id="iframe1" style="zoom: 0.8;" height="1000" src="<c:url value='/users/jump_keyword_search.action' />" frameBorder="0" width="100%"></iframe>
            

           
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="navbar navbar-footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <footer role="contentinfo">
                            <p align="center" class="center">cjy制作 &copy; 2017</p>
                            
                        </footer>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/lib/js/twitter-bootstrap-hover-dropdown.min.js' />"></script>
<!--        <script type="text/javascript" src="js/bootstrap-admin-theme-change-size.js"></script>-->
    </body>
</html>
