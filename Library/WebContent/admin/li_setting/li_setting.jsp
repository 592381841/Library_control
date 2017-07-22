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
    <title>图书馆设置</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">
    
    
        <link href="<c:url value='/lib/css/bootstrap-responsive.min.css' />" rel="stylesheet" media="screen">
        <!-- Bootstrap -->     
        <link rel="stylesheet/less" type="text/less" href="<c:url value='/lib/less/datepicker.less' />">
        <link href="<c:url value='/lib/css/datepicker.css' />" rel="stylesheet" media="screen">
    
	  
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
	  
	  .form_content{
		  margin-top: 1%;
		  margin-right: 1%;
	  }
	</style>
  <body>
  	<div id="contain" class="container-fluid">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
       <div class="col-sm-6 col-sm-offset-3">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">图书馆设置</font>
   
       </div>
    
       
       </div>
<!--
       <div class="row">
       	
       	   <div class="col-sm-4 col-sm-offset-8">
       	<font  style="text-align: center; font-family:titie_1;text-align: right;font-size: 15px; color: #e31510 "> 更新时间：2012-05-20 12：21：15</font>
       </div>
       	
       </div>
-->

       	<div class="row"> 
       	
       	       <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <div class="text-muted bootstrap-admin-box-title" style="font-family:titie_1; "></div>
                                </div>
                                <form id="myform" >
                                <div class="bootstrap-admin-panel-content">
													<div class="form_content row">
														<div class="col-sm-6">
															<div class="form-horizontal">
  <div class="form-group">
    <label for="li_name" class="col-sm-3 control-label">图书馆名</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="li_name" name="libraryInfo.library_name" placeholder="输入图书馆名" value="${libraryInfo.library_name }">
    </div>
  </div>
  <div class="form-group">
    <label for="li_time" class="col-sm-3 control-label"  >建馆时间</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="li_time" readonly name="libraryInfo.library_time" placeholder="选择时间" value="${libraryInfo.library_time }">
    </div>
  </div>

  <div class="form-group">
    <label for="li_boss_name" class="col-sm-3 control-label">馆长姓名</label>
    <div class="col-sm-9">
      <input name="libraryInfo.librarian_name" type="text" class="form-control" id="li_boss_name" placeholder="输入馆长姓名" value="${libraryInfo.librarian_name }">
    </div>
  </div>
</div>
															
															
														</div>
														<div class="col-sm-6">
																<div class="form-horizontal">
  <div class="form-group">
    <label for="li_boss_phone" class="col-sm-3 control-label">馆长电话</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="li_boss_phone" name="libraryInfo.librarian_phone" placeholder="请输入电话" value="${libraryInfo.librarian_phone }">
    </div>
  </div>
  <div class="form-group">
    <label for="li_boss_adress" class="col-sm-3 control-label">办公地址</label>
    <div class="col-sm-9">
      <input name="libraryInfo.librarian_address" type="text" class="form-control" id="li_boss_adress" placeholder="输入办公地址" value="${libraryInfo.librarian_address }">
    </div>
  </div>

  <div class="form-group">
    <label for="li_boss_email" class="col-sm-3 control-label">馆长邮箱</label>
    <div class="col-sm-9">
      <input name="libraryInfo.librarian_email" type="email" class="form-control" id="li_boss_email" placeholder="输入邮箱地址" value="${libraryInfo.librarian_email }">
    </div>
  </div>
</div>
															
														</div>
														
	
														
														
														
													</div>
                                                        			
                                                        			
                                                        			
                                 
                                 <div class="form_content row">
                                  <div class="form-horizontal">
  <div class="form-group">
                                  <label for="li_introduce" class="col-sm-offset-4 col-sm-3 control-label " >
                                   <font style="text-align:left;font-size:20px;font-family:titie_1;color: #e31510 ">图书馆简介</font>
	  </label>
								</div>
								</div>
									</div>
                                 
                                 
                                  
                                  			<div class="form_content row">
													<div class="form-horizontal">
  <div class="form-group">
    <label for="" class="col-sm-1 control-label sr-only" ></label>
    <div class="col-sm-10">
      <textarea name="libraryInfo.library_intro" id="bootstrap-editor"  style="width:100%;resize: none; min-height: 300px" class="form-control" placeholder=" 请输入图书馆简介"  >${libraryInfo.library_intro }</textarea>
     
    </div>
  </div>
															</div>
															
															
															
														</div>
														<div style="margin-bottom: 2%" class="row">
                            
								<div class="col-sm-2 col-sm-offset-9">
									
									<a  onclick="modifyLi()" class="btn btn-block btn-primary "><span class="glyphicon glyphicon-pencil" >修改</a>
								</div>
                                    	
                                    	
                                    	
                                    </div>  
                                  
                                  
                                   
                                </div>
                                </form>
                                

                            </div>
       	
       	
       	</div>
  			
  		</div>
  			
  			
  		</div>
  		
  	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/lib/js/bootstrap-datepicker.js' />"></script>
    <script type="text/javascript">
	   $("#li_time").datepicker({
				format: 'yyyy-mm-dd'
			});
	   function modifyLi(){
		 
	   $.ajax({
		   url:"<c:url value='/admin/li_setting/json_modify_library_info.action' />",
		   type: "POST",
		   data:$("#myform").serialize(),
		   async :true,
		   cacche : false,
		   dataType :"json",
		   success:function(data){
			   var json=eval(data);
			   alert(json.info);
			   parent.location.reload(); 
		   },
		   error: function(){	
// 				window.location='<c:url value='/error.jsp'/>';
			}
		   
	   })
	   }
	  
	  </script>
  </body>
</html>