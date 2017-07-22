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
        <title>后台管理主页</title>
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
       <div class="col-lg-5" style="text-align:left;font-size:50px;font-family:titie_1; "><font style="color: #e31510 ">${li_name }<sub>--管理员主页</sub></font>
       </div>
       
             <div style="align-content: " class="col-lg-7">
                        <div class="collapse navbar-collapse">
                     
                            <ul class="nav navbar-nav navbar-right ">
                                
                                <li class="nav-child">
                                    <a target='iframe1' href="<c:url value='/admin/modify_passwd.jsp' />" ><font>修改密码</font> <i class="glyphicon glyphicon-pencil"></i></a>
                                </li>
                                  <li class="nav-child">
                                    <a href="<c:url value='/common/logout.action' />"  ><font>退出登录 </font><i class="glyphicon glyphicon-share-alt"></i></a>
                                </li>
                                <li class="nav-child">
                                    <a target='iframe1' href="<c:url value='/admin/admin_info.jsp' />" ><font>${Liadmin.adminName }</font> <i class="glyphicon glyphicon-user"></i></a>
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
                            <a class="navbar-brand" href="<c:url value='/admin/index.jsp' />">主页</a>
                        </div>
                        <div class="collapse navbar-collapse main-navbar-collapse">
                            <ul class="nav navbar-nav">
                            
                <c:if test="${Liadmin.libJu }">
                                <li class="active"><a class="menu" target="iframe1" href="<c:url value='/admin/borrow_control/jump_borrow_control.action' />">借阅办理</a></li>
                                 <li><a class="menu" target="iframe1" href="<c:url value='/admin/borrow_control/jump_all_borrow_history.action' />">借阅记录查询</a></li>
                               </c:if>
            
                <c:if test="${Liadmin.readTypeJu }">
                                 <li><a class="menu" target="iframe1" href="<c:url value='/admin/readType_control/jump_borrow_number_about_user_type.action' />">用户类型管理</a></li>
                                 </c:if>
                                  <c:if test="${Liadmin.readerJu }">
                                 <li><a class="menu" target="iframe1" href="<c:url value='/admin/reader_control/jump_reader_control.action' />">读者管理</a></li>
                                  </c:if>
                                    <c:if test="${Liadmin.asrJu }">
                                         <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-hover="dropdown">图书管理 <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        
                                        <li><a class="menu" target="iframe1" href="<c:url value='/admin/book_control/jump_type_book_search.action' />"><span class="glyphicon glyphicon-search" > </span>分类检索</a></li>
                                        <li><a class="menu" target="iframe1" href="<c:url value='/admin/book_control/jump_keyword_book_search.action' />"><span class="glyphicon glyphicon-search" > </span> 目录检索</a></li>
                                          <li role="separator" class="divider"></li>
                                         <li><a  class="menu" target="iframe1" href="<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=true"><span class="glyphicon glyphicon-plus" > </span>添加图书</a></li>
 										<li><a  class="menu" target="iframe1" href="<c:url value='/admin/book_control/jump_book_type_control.action' />"><span class="glyphicon glyphicon-plus" > </span>图书分类管理</a></li>	
 <li><a  target="iframe1" href="<c:url value='/admin/book_control/jump_delete_book_list.action' />"><span class="glyphicon glyphicon-search" > </span>已下架图书</a></li>

                                    </ul>
                                </li>
                                </c:if>
                                <c:if test="${Liadmin.asrJu }">
                                 <li><a class="menu"  target="iframe1" href="<c:url value='/admin/stack_room_control/jump_stack_room_control.action' />">书库管理</a></li>
                                  </c:if>
                                  <c:if test="${Liadmin.aisRoot }">
                                   <li><a class="menu"  target="iframe1" href="<c:url value='/admin/manager_control/jump_manager_control.action' />">工作人员管理</a></li>
                                     <li><a class="menu"  target="iframe1" href="<c:url value='/admin/li_setting/jump_li_setting.action' />">图书馆设置</a></li>
                           </c:if>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>

        <div class="container">
            <!-- left, vertical navbar & content -->
            <div class="row">
 
                <div class="col-md-10 col-md-offset-1">
                <iframe name="iframe1" id="iframe1" style="zoom: 0.8;" height="1200" src="<c:url value='/admin/no_access.jsp' />" frameBorder="0" width="100%"></iframe>
                
 
            

           
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="navbar navbar-footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <footer role="contentinfo">
                            <p align="center" class="center">cjy 1.0制作 &copy; 2017</p>
                            
                        </footer>
                    </div>
                </div>
            </div>
        </div>

<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
            <script type="text/javascript" src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/lib/js/twitter-bootstrap-hover-dropdown.min.js' />"></script>
<!--        <script type="text/javascript" src="js/bootstrap-admin-theme-change-size.js"></script>-->
    </body>
    <script type="text/javascript">
    var urlmenu;
    var menu=$(".menu");

    if(menu.length!=0){
    	
 
    	urlmenu=$(menu[0]).attr("href");
    
    	 $("#iframe1").attr("src",urlmenu);
    }
   
    </script>
</html>
