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
    <title>读者个人信息设置</title>

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
  	<div id="contain" class="container">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
       <div class="col-sm-6 col-sm-offset-3">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">读者个人信息设置</font>
   
       </div>
    
       
       </div>
 
       	<div class="row"> 

       	<div class="col-sm-7">
       	
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td>姓名</td>
                                                    <td><input type="text" class="form-control" id="ad_name" value="<s:property value='readerDto.usersName' />" placeholder="输入姓名"></td>
                                                <td >
                                                   
												   </td>
                                                
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                   <td><input type="text" class="form-control" id="id_card" value='<s:property value='readerDto.idCard' />' placeholder="身份证号码"></td>
                                                <td >
                                                   
												   </td>
                                            </tr>
                                            <tr>
                                                <td>账号</td>
                                                <td><s:property value='readerDto.account' /></td>
                                            <td></td>
                                            </tr>
                                                                                         <tr>
                                                <td>读者类型</td>
                                                <td>
                                                    <input type="hidden" id="typeId" value='<s:property value='readerDto.readerTypeDto.id' />' >
                                                	   <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="dropdownSelectText"><s:property value='readerDto.readerTypeDto.typeName' /></font> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
  <s:iterator value="readerTypeDtos" var="s">
   <li><a onClick="dropdownText(this)"><s:property value="#s.typeName" /></a><input  type="hidden" value="<s:property value="#s.id" />" /></li>
  
  </s:iterator>
	 
  </ul>
</div>
                                                	
                                                	
                                                </td>
                                                   <td>
                                                    
												   </td>
                                            </tr>
                                              
                                               <tr>
                                                <td>状态：</td>
                                                <td>
                                                <s:if test="readerDto.risStatus">
                                               	<font color="#428BCA"  >正常</font>
                                               </s:if>
                                              <s:else>
                                                <font color="#D9534F">禁用</font>
                                               </s:else>
                                                
                                                </td>
                                           <td class="actions">
                                                    <s:if test="readerDto.risStatus">
                                               	<a onclick="modify_is_status(false,<s:property value='readerDto.account' />,this)">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-remove
"></i>
                                                           禁用
                                                        </button>
                                                    </a>
                                               </s:if>
                                              <s:else>
                                                <a onclick="modify_is_status(true,<s:property value='readerDto.account' />,this)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-ok
"></i>
                                                           激活
                                                        </button>
                                                    </a>
                                               </s:else>
												   </td>
                                            </tr>
                                            <tr>
                                            <td>密码：</td>
                                            <td>
                                            <a onclick="modify_reader_passwd()">
                                                        <button class="btn btn-sm btn-success">
                                                            <i class="glyphicon glyphicon-ok
"></i>
                                                           重置密码
                                                        </button>
                                                    </a>
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

    <img  id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;"   onerror="this.src='<c:url value='/lib/images/default_pic.gif' />'">
	 </div>
	 <div class="col-xs-5">
  <input class="form-control" id="upLoadFile" type="file" onchange="preview()">

	 </div>
	 
	 
	 	 <div class="col-xs-3">

		 <a class="pull-right
btn btn-primary" onclick="uploadPic()" ><i class="glyphicon glyphicon-ok"></i>提交</a>
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
			  <c:if test="${Liadmin.libJu }">
			<div class="col-sm-2 " >
					<a href="<c:url value='/admin/borrow_control/jump_reader_borrow_history.action' />?account=<s:property value='readerDto.account' />" target="_blank" class="btn btn-primary pull-left btn-block">查看借阅情况</a>
				
				
			</div>
			</c:if>
				<div class="col-sm-2 " >
					<a onclick="modifyReader()"  class="btn btn-primary pull-right btn-block">修改</a>
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
		  
		  selectt.text(oldtext);
		  $("#typeId").val($(as).next().val());
	  }
		
	//上传图片
	    function uploadPic(){
	    	var readerPic="${readerDto.pic}";
	    	var formData = new FormData();
//	     	console.log($("#upLoadFile")[0].files[0]);
	    	formData.append("uploadFile",$("#upLoadFile")[0].files[0]);
	    	//项目中所需的数据（无关上传）
	    	
	    	formData.append("pic",readerPic);
	    	
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
	            		$("#pic").attr("src" , "<c:url value='/"+json.pic+"' />?xxx=" + new Date().getTime());
					}
			
					
					
				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
	    		
	    	});
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
	    
		  //激活禁用
	    function modify_is_status(modify_status,account,ElementThis){
	    	
	    	


	      $.ajax({
	    	  url:"<c:url value='/admin/reader_control/json_modify_reader_is_status.action' />",
	    		type:"POST",
	    		data:{
	    			modify_status : modify_status,
	    			account : account,
	    		},
	    		async : true,
	    		cache : false,
	    		dateType : "json",
	    		success:function(data){	
	    			var jsonText=eval(data);
	    			alert(jsonText.info);
	    			if(jsonText.result){
	    			
	    				
	    				if(modify_status){
	    					$($(ElementThis).parent().prev().children()[0]).text("正常");
	    					$($(ElementThis).parent().prev().children()[0]).attr("color","#428BCA");
	    					$($(ElementThis).children()[0]).text("");
	    					$($(ElementThis).children()[0]).append(" <i class='glyphicon glyphicon-remove'></i>禁用");
	    					$($(ElementThis).children()[0]).removeClass("btn-primary");
	    					$($(ElementThis).children()[0]).addClass("btn-danger");
	    					
	    					var s=$(ElementThis).attr("onclick").replace("true","false");
	    					$(ElementThis).attr("onclick",s);
	    				}else{
	    					$($(ElementThis).parent().prev().children()[0]).text("禁用");
	    					$($(ElementThis).parent().prev().children()[0]).attr("color","#D9534F");
	    					$($(ElementThis).children()[0]).text("");
	    					$($(ElementThis).children()[0]).append("<i class='glyphicon glyphicon-ok'></i>激活");
	    					$($(ElementThis).children()[0]).removeClass("btn-danger");
	    					$($(ElementThis).children()[0]).addClass("btn-primary");
	    					
	    					var s=$(ElementThis).attr("onclick").replace("false","true");
	    					$(ElementThis).attr("onclick",s);
	    					
	    				}
	    			
	    					
	    				}
	    			},error: function(){	 
	    			window.top.location='<c:url value='/error.jsp'/>';
	    		}
	      })
	    }
		  //修改
		  function modifyReader(){
			  
			  $.ajax({
		    	  url:"<c:url value='/admin/reader_control/json_modify_reader.action' />",
		    		type:"POST",
		    		data:{
		    			"readerDto.readerTypeDto.id" :$("#typeId").val(),
		    			"readerDto.account" :<s:property value='readerDto.account' />,
		    			"readerDto.usersName" : $("#ad_name").val(),
		    			"readerDto.idCard" : $("#id_card").val(),
		    			
		    		},
		    		async : true,
		    		cache : false,
		    		dateType : "json",
		    		success:function(data){	
		    			var jsonText=eval(data);
		    			alert(jsonText.info);
		    			
		    			},error: function(){	 
		    			window.top.location='<c:url value='/error.jsp'/>';
		    		}
		      })
			  
			  
			  
		  }

		  //重置密码
		  function modify_reader_passwd(){
			  $.ajax({
		    	  url:"<c:url value='/admin/reader_control/json_reset_reader_passwd.action' />",
		    		type:"POST",
		    		data:{
		    			"account" :<s:property value='readerDto.account' />
		    			
		    		},
		    		async : true,
		    		cache : false,
		    		dateType : "json",
		    		success:function(data){	
		    			var jsonText=eval(data);
		    			alert(jsonText.info);
		    			
		    			},error: function(){	 
		    			window.top.location='<c:url value='/error.jsp'/>';
		    		}
		      })
		  }
	
		//当点击加载页面按钮，则加载分页栏
		  $(function(){
			  $("#pic").attr("src" , " <c:url value='/people_pic/${readerDto.pic}' />?xxx=" + new Date().getTime());
			 
		  	});
		 
		  
			</script>
  </body>
</html>