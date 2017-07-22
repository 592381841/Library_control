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
    <title>添加新读者</title>

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
  	<div id="contain" class="container">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
       <div class="col-sm-6 col-sm-offset-3">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">添加新读者</font>
   
       </div>
    
       
       </div>
 
       	<div class="row"> 

       	<div class="col-sm-7">
       	
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td>姓名</td>
                                                    <td><input type="text" class="form-control" id="re_name" placeholder="输入姓名"></td>
                                              
                                                
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                   <td><input type="text" class="form-control" id="id_card" placeholder="身份证号码"></td>
                                            
                                            </tr>
                                            <tr>
                                                <td>账号</td>
                                                <td><s:property value="account" /></td>
                                          
                                            </tr>
                                            <tr>
                                            	   <td>读者类型</td>
                                                <td>
                                                <input type="hidden" id="typeId" value='1' >
                                                	   <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="dropdownSelectText">学生</font> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
  <s:iterator value="readerTypeDtos" var="s">
   <li><a onClick="dropdownText(this)"><s:property value="#s.typeName" /></a><input  type="hidden" value="<s:property value="#s.id" />" /></li>
  
  </s:iterator>
	 
  </ul>
</div>
                                                	
                                                	
                                                </td>
                                            
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
 <div class="col-xs-4 ">
   <img id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;" src="<c:url value='/lib/images/default_pic.gif' />">
	 </div>
	 <div class="col-xs-8">
  <input class="form-control" type="file" id="upLoadFile"  onchange="preview()">

	 </div>
	 
	 
  </div>
  <div class="row">
	  <div class="col-xs-12">
		  <label class="label-control" style="align-content: center;color: #e31510">请上传96*128像素的照片</label>
	  	
	  </div>
  	
  </div>
  </div>
</div>
       	</div>
       	
       	
       	</div>

  		<div class="row" style="margin-bottom: 1%">
  		<div class="col-sm-2 col-sm-offset-9">
	<a onclick="addreader()" class="btn btn-lg btn-primary btn-block pull-right"><i class="glyphicon glyphicon-plus
"></i>新建</a>
  		
  		</div>
  		</div>	
  			
  		</div>
  		
  	</div>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
     <!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
		
		//下拉js
	  function dropdownText(as){
		  var oldtext=as.text;
		  var selectt=$("#dropdownSelectText");
		  
// 		  as.innerHTML=selectt.text();
		  selectt.text(oldtext);
		  $("#typeId").val($(as).next().val());
	  }
		//预览图加载
		function preview(){
var formData = new FormData();
	     	
	    	formData.append("uploadFile",$("#upLoadFile")[0].files[0]);
	    	//项目中所需的数据（无关上传）
	    	formData.append("pic","preview/default.jpg");
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
					var json=eval(data);
					if(json.result){
						$("#pic").attr("src" , "<c:url value='/people_pic/preview/default.jpg' />?xxx=" + new Date().getTime());
					}
					else{
						alert("请上传符合要求的文件")
					}
					
	            		
			
					
					
				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
	    		
	    	});
			
			
			
			
		}
		
	function addreader(){
	 	
		$.ajax({
			url :"<c:url value='/admin/reader_control/json_addReader.action' />",
			type : "POST",
			data :{
				"readerDto.usersName" : $("#re_name").val(),
				"readerDto.idCard" : $("#id_card").val(),
				"readerTypeDto.id" : $("#typeId").val()
				
			},
			async :true,
			cache : false,
			success :function(data){
				var json=eval(data);
				if(json.result){
					if($("#upLoadFile")[0].files[0]!=undefined){
						uploadPic(json.pic);
					}else{
						 alert(json.info);
						alert("无图片上传")
						
					}
					 
					
						
						 alert(json.userName+"读者\n用户名:"+json.account+"\n密码 :"+json.passwd+"\n身份证号："+json.id_card+"\n")
						 location.reload();   
				}
				else{
					alert(json.info);
				}
				
			},error: function(){
				window.top.location='<c:url value='/error.jsp'/>';
			}
		});
		
		
		
	}
		
	//上传图片
	    function uploadPic(pic){
	    	var formData = new FormData();
	     	
	    	formData.append("uploadFile",$("#upLoadFile")[0].files[0]);
	    	//项目中所需的数据（无关上传）
	    	formData.append("pic",pic);
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
					
					if(json.result){
						alert("添加成功,图片上传成功");
	            		$("#pic").attr("src" , "<c:url value='"+json.pic+"' />?xxx=" + new Date().getTime());
					}
					else{
						alert("添加成功，但是上传图片失败");
					}
			
					
					
				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
	    		
	    	});
	    }
		  </script>
	  
  </body>
</html>