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
    <title>管理员个人信息</title>

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
src:url('<c:url value='/lib/fonts/fanti_title.ttf' />')
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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">管理员个人信息</font>
   
       </div>
    
       
       </div>
 
       	<div class="row"> 

       	<div class="col-sm-7">
       	
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td>姓名</td>
                                                <td>${Liadmin.adminName }</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                <td>${Liadmin.idCard }</td>
                                             <td></td>
                                            </tr>
                                            <tr>
                                                <td>账号</td>
                                                <td>${Liadmin.account }</td>
                                            <td></td>
                                            </tr>
                                      
                                               <tr id="is_status_tr">
                                                <td>状态：</td>
<%--                                                 <c:choose> --%>
<%--                                                  <c:when test="${Liadmin.aisStatus }"> --%>
                                                <td>正常</td>
                                                <td class="actions">
                                                    <a onclick="modify_is_status(false)">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-remove"></i>
                                                            停用
                                                        </button>
                                                    </a>
                                                    </td>
                                             
<%--                                                </c:when> --%>
<%--                                                <c:otherwise> --%>
<!--                                                                             <td>停用</td> -->
<!--                                                                                           <td class="actions"> -->

<!--                                                        <a href="" onclick="modify_is_status(true)"> -->
<!--                                                         <button class="btn btn-sm btn-primary"> -->
<!--                                                             <i class="glyphicon glyphicon-ok"></i> -->
<!--                                                             激活 -->
<!--                                                         </button> -->
<!--                                                     </a> -->
                                                     
<!-- 												   </td> -->
                                               
                                               
<%--                                                </c:otherwise> --%>
                                                
                                                
<%--                                                 </c:choose> --%>
                              
                                              
                    
                                                 
                                                 
                                             
                                                
  
                                            </tr>
     
                                        </tbody>
                                    </table>
                              
                                
                                

                           
       	</div>
  	<div class="col-sm-5">
       		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">照片</h3>
  </div>
  <div class="panel-body">
  <div class="row">
 <div class="col-xs-6">
	   <img  id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;" src="<c:url value='/people_pic/${Liadmin.pic}' />"  onerror="this.src='<c:url value='/lib/images/default_pic.gif' />'">

	 
	 </div>
	 <div class="col-xs-6">
	 	  <div class="row" style="margin-top: 3%">
	  <div class="col-xs-10 col-xs-offset-1">
	  <div class="form-horizontal">
	  <div class="row">
    <div class="col-xs-10 col-xs-offset-1"  >
     <input class="form-control" id="upLoadFile" name="uploadFile" type="file">
     </div>
     </div>
     <div class="row" style="margin-top: 3%">
     <div class="col-xs-6 col-xs-offset-5">
      <a class="pull-right
 btn btn-primary" onclick="uploadPic()" ><i class="glyphicon glyphicon-arrow-up
 			 "></i>上传照片</a> 
     </div>
  </div>
  
		
	  </div>
	 	  

  </div>

	 </div>
	 
	  
  <div class="row">
	  <div align="center" class="col-xs-12">
		  <label class="label-control" style="align-content: center;color: #e31510">请上传96*128像素格式为jpg、jpeg、bmp、gif的照片</label>
	  	
	  </div>
  	
  </div>
	 
	 
	 </div>

  </div>

  </div>
</div>
       	</div>
       	
       	
       	
       	</div>
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
				         		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">权限管理</h3>
  </div>
  <div class="panel-body">
					  <table class="table">
                                        <thead>
                                            <tr>
                                             
                                         
                                              
                                                <th>书库/图书管理</th>
                                                <th>root</th>
                                                <th> 用户类型管理</th>
                                                <th>读者管理</th>
                                                <th>借阅管理</th>
											
                                            </tr>
                                        </thead>
                                        <tbody>
                                           
                                                                     <tr>
                                                                
                                            
                                               <td>
                                               <c:choose>
                                               <c:when test="${Liadmin.asrJu }">
                                              <i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                              <i class="glyphicon glyphicon-remove"></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                               
                                               <td>
                                               <c:choose>
                                               <c:when test="${Liadmin.aisRoot }">
												<i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                                <i class="glyphicon glyphicon-remove"/></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
 
                                              
                                                <td>
                                               <c:choose>
                                               <c:when test="${Liadmin.readTypeJu }">
                                              <i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                              <i class="glyphicon glyphicon-remove"></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                                <td>
                                               <c:choose>
                                               <c:when test="${Liadmin.readerJu }">
                                              <i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                                <i class="glyphicon glyphicon-remove"></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                            <td>
                                               <c:choose>
                                               <c:when test="${Liadmin.libJu }">
                                              <i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                               <i class="glyphicon glyphicon-remove"></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                                
                                             
                                              
                 
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
  	  </div>
  </body>
  <script type="text/javascript">
  
  function modify_is_status(a){
	  $.ajax({
		  url:"<c:url value='/admin/general/json_modify_my_status.action' />",
			type:"POST",
// 			data:{
// 				modify_status : a,
// 			},
			async:true,
			cache:false,
			dateType:"json",
			success:function(data){	
			
				var json=eval(data);
				alert(json.info);
				if(json.result){
					window.top.location='<c:url value='/common/logout.action'/>';
					
				
				}
		
			
			},
			error: function(){	 
				window.top.location='<c:url value='/error.jsp'/>';
			}
	  })
  }
  //上传图片
  function uploadPic(){
  	var formData = new FormData();
//   	console.log($("#upLoadFile")[0].files[0]);
  	formData.append("uploadFile",$("#upLoadFile")[0].files[0]);
  	//项目中所需的数据（无关上传）
  	formData.append("adminDto.account","${Liadmin.account }");
  	$.ajax({
  		url:"<c:url value='/admin/upload_uploadImage.action' />",
  		type : "POST",
  		 data:formData,
  		async : true,
			cache : false,
			processData :false,
			contentType :false,
			dateType : "json",
			success:function(data){
				var json =eval(data);
				alert(json.info);
				if(json.result){
          		$("#pic").attr("src" , "<c:url value='/people_pic/${Liadmin.pic}' />?xxx=" + new Date().getTime());
				}
		
				
				
			},error: function(){
				window.top.location='<c:url value='/error.jsp'/>';
			}
  		
  	});
  }
  
  
  
  </script>
</html>