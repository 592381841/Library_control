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
    <title>管理员个人信息设置</title>

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
       <div class="col-sm-8 col-sm-offset-2">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">管理员个人信息设置</font>
   
       </div>
    
       
       </div>
 

       	
       	</div>
       	</div>
			<div class="row">
				<div class="col-sm-7 col-sm-offset-1">
				         		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">个人信息</h3>
  </div>
  <div class="panel-body">
<!--   <form id="myForm"> -->
  <div class="row">
  
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                        
                                         <tr>
                                                <td>账号</td>
                                                <td>${adminDto.account }</td>
                           							<td></td>
                                            </tr>
                                        
                                            <tr>
                                                <td>昵称</td>
                                                    <td><input type="text" class="form-control" value="${adminDto.adminName }" id="adminName" placeholder="输入昵称"></td>
                                  
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                   <td><input type="text" class="form-control" name="adminDto.idCard" id="id_card" value="${adminDto.idCard }" placeholder="身份证号码"></td>
                                                                 
													<td></td>
												 
                                            </tr>
                                            
                                           
                                               <tr>
                                                <td>状态：</td>
                                                <td>
                                                <c:choose>
                                                <c:when test="${adminDto.aisStatus }">
                                                <font color="#428BCA"  >正常</font>
                                                </c:when>
                                                <c:otherwise>
                                                <font color="#D9534F">停用</font>
                                                </c:otherwise>
                                                </c:choose>

                                          </td>

                                           <td class="actions" align="center">
                                        <c:choose>
                                        <c:when test="${ adminDto.aisStatus}">
                                        <a onclick="modify_is_status(false,${adminDto.account },${adminDto.aisRoot },this)">
                                          
                                                        <button class="btn btn-sm btn-danger">

                                                      停用
                                                        </button>
                                                    </a>
                                        </c:when>
                                        <c:otherwise>
                                         <a onclick="modify_is_status(true,${adminDto.account },${adminDto.aisRoot },this)">
                                                        <button class="btn btn-sm btn-primary">

                                                           激活
                                                        </button>
                                                    </a>
                                        
                                        </c:otherwise>
                                        </c:choose>   
 	
                                                   
                                                     
                                                          <a onclick="delete_user(${adminDto.account },${adminDto.aisRoot })" >
                                                        <button class="btn btn-sm btn-danger">

                                                           删除
                                                        </button>
                                                    </a>
												   </td>
                                            </tr>
                                            <tr>
                                            	<td>密码</td>
                                            	   <td class="actions" align="center">
                                            	       <a onclick="modify_passwd(${adminDto.account })" >
                                                        <button class="btn btn-sm btn-danger">

                                                           重置密码
                                                        </button>
                                                    </a>
                                            	   
                                            	   
                                            	   
                                            	   </td>
                                            	   <td></td>
                                            
                                            </tr>
                
     
                                        </tbody>
                                    </table>
  
  
  </div>
  
   <div class="row">
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
                                           
                                                                     <tr align="left">
              
                                         
                                                
                                               <td>
                                               <c:choose>
                                               <c:when test="${adminDto.asrJu }">
                                               <input type="checkbox"  class="checkbox_Id_list" checked="checked" />
                                               </c:when>
                                               <c:otherwise>
                                                <input type="checkbox"  class="checkbox_Id_list"  />
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                               
                                               <td>
                                               <c:choose>
                                               <c:when test="${adminDto.aisRoot }">
												<i class="glyphicon glyphicon-ok"></i>
                                               </c:when>
                                               <c:otherwise>
                                                <i class="glyphicon glyphicon-remove"/></i>
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
 
                                              
                                                <td>
                                               <c:choose>
                                               <c:when test="${adminDto.readTypeJu }">
                                               <input type="checkbox"  class="checkbox_Id_list" checked="checked" />
                                               </c:when>
                                               <c:otherwise>
                                                <input type="checkbox"  class="checkbox_Id_list"  />
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                                <td>
                                               <c:choose>
                                               <c:when test="${adminDto.readerJu }">
                                               <input type="checkbox"  class="checkbox_Id_list" checked="checked" />
                                               </c:when>
                                               <c:otherwise>
                                                <input type="checkbox"  class="checkbox_Id_list"  />
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                            <td>
                                               <c:choose>
                                               <c:when test="${adminDto.libJu }">
                                               <input type="checkbox"  class="checkbox_Id_list" checked="checked" />
                                               </c:when>
                                               <c:otherwise>
                                                <input type="checkbox"  class="checkbox_Id_list"  />
                                               </c:otherwise>
                                               
                                               </c:choose>
                                               </td>
                                            </tr>
                                           
                                        </tbody>
                                    </table>
									</div>
<!-- 									</form> -->
									<div class="row">
									<div class="col-sm-3 col-sm-offset-9">
									                                                    <a onclick="modify_admin_info()">
                                                        <button class="btn btn-block btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon- ok"></i>
                                                            修改
                                                        </button>
                                                    </a>
									
									
									</div>
									
									</div>
									</div>
					
					
				</div>
				
				
			</div>
  	<!-- 照片模块暂时不写-->
  	
  	       	       	<div class="col-sm-3">
       		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">照片</h3>
  </div>
  <div class="panel-body">
 
  <div align="center" class="row">
  	
	 <img  id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;"  onerror="this.src='<c:url value='/lib/images/default_pic.gif' />'">
	
		 
 <div class="row" style="margin-top: 3%">
	  <div class="col-xs-10 col-xs-offset-1">
	  <div class="form-horizontal">
	  <div class="row">
    <div class="col-xs-10 col-xs-offset-1"  >
     <input class="form-control" id="upLoadFile" name="uploadFile" type="file" onchange="preview()">
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
	 

	
	 


<!--    </div> -->
<!--   </div> -->
<!-- </div> -->
  			
  		</div>
  			
  			
  		</div>
  		


    </body>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
     <!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script type="text/javascript">
    
    
    //修改信息
    function modify_admin_info(){
    	var adminName=$("#adminName").val();
    	var id_card=$("#id_card").val();
    	if (adminName == null || adminName == undefined || adminName == '' || id_card == null || id_card == undefined || id_card == ''){
    		alert("昵称和身份证不为空");
    		return;
    	}
    	if(id_card.length!=18){
    		alert("身份证必须是18位");
    		return;
    	}
    	var bo=new Array() ;
    	var ju=$(".checkbox_Id_list");
    	for(var i=0;i<ju.length;i++){
    		if($(ju[i]).is(':checked')){
    			bo[i]=true;
    		}
    		else{
    			bo[i]=false;
    		}
    		}
    	 $.ajax({
   		  url:"<c:url value='/admin/manager_control/json_modify_admin_info.action' />",
   			type:"POST",
   			data:{
   				"adminDto.account" : "${adminDto.account }",
   				"adminDto.adminName" : adminName,
   				"adminDto.idCard" : id_card,
   				"adminDto.asrJu" : bo[0],
   				"adminDto.readTypeJu" : bo[1],
   				"adminDto.readerJu" : bo[2],
   				"adminDto.libJu" : bo[3]
   				
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
    
	
	//激活禁用用户
	function modify_is_status(modify_status,account,is_root,ElementThis){
		var is_me;
		if("${Liadmin.account}"==account){
			is_me=true;
			if (confirm("你确定停用自己（停用后想开启联系root后方可登录）？")) { 
				
			}
			else{
				return;
			}
			
		
		}
		else{
			is_me=false;
		}
		



	  $.ajax({
		  url:"<c:url value='/admin/manager_control/json_modify_admin_is_status.action' />",
			type:"POST",
			data:{
				modify_status : modify_status,
				account : account,
				is_me : is_me,
				is_root : is_root
			},
			async : true,
			cache : false,
			dateType : "json",
			success:function(data){	
				var jsonText=eval(data);
				alert(jsonText.info);
				if(jsonText.result){
				if(is_me){
					window.top.location='<c:url value='/login.jsp'/>';	
				}
				else{
					
					if(modify_status){
						$($(ElementThis).parent().prev().children()[0]).text("正常");
						$($(ElementThis).parent().prev().children()[0]).attr("color","#428BCA");
						$($(ElementThis).children()[0]).text("停用");
						$($(ElementThis).children()[0]).removeClass("btn-primary");
						$($(ElementThis).children()[0]).addClass("btn-danger");
						
						var s=$(ElementThis).attr("onclick").replace("true","false");
						$(ElementThis).attr("onclick",s);
					}else{
						$($(ElementThis).parent().prev().children()[0]).text("停用");
						$($(ElementThis).parent().prev().children()[0]).attr("color","#D9534F");
						$($(ElementThis).children()[0]).text("激活");
						$($(ElementThis).children()[0]).removeClass("btn-danger");
						$($(ElementThis).children()[0]).addClass("btn-primary");
						
						var s=$(ElementThis).attr("onclick").replace("false","true");
						$(ElementThis).attr("onclick",s);
						
					}
				}
						
					}
				},error: function(){
				window.top.location='<c:url value='/error.jsp'/>';
			}
	  })
  }
	
	
	 //删除用户
	 function delete_user(account,is_root){
		 
	
				if (confirm("你确定删除此用户？")) { 
					 $.ajax({
						  url:"<c:url value='/admin/manager_control/json_delete_an_admin.action' />",
							type:"POST",
							data:{
								users_type : true,
								account : account,
								
								is_root : is_root
							},
							async : true,
							cache : false,
							dateType : "json",
							success:function(data){
								var json =eval(data);
								alert(json.info);
								if(json.result){
// 									window.close()
window.location="<c:url value='/admin/manager_control/jump_manager_control.action' />"

								}

						
								
								
							},error: function(){
								window.top.location='<c:url value='/error.jsp'/>';
							}
							
					 });
				}
				else{
					return;
				}
		 
	
					}
    
    
    //重置密码
	function  modify_passwd(id){
		$.ajax({
			  url:"<c:url value='/admin/manager_control/json_reset_passwd.action' />",
				type:"POST",
				data:{
					account : id,
					
				
				},
				async : true,
				cache : false,
				dateType : "json",
				success:function(data){
					var json =eval(data);
					alert(json.info);

			
					
					
				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
				
		 });
	}
    //上传图片
    function uploadPic(){
    	var formData = new FormData();
//     	console.log($("#upLoadFile")[0].files[0]);
    	formData.append("uploadFile",$("#upLoadFile")[0].files[0]);
    	//项目中所需的数据（无关上传）
    	formData.append("pic","${adminDto.pic }");
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
            		$("#pic").attr("src" , "<c:url value='/people_pic/${adminDto.pic}' />?xxx=" + new Date().getTime());
				}
		
				
				
			},error: function(){
				window.top.location='<c:url value='/error.jsp'/>';
			}
    		
    	});
    }
    $(function(){
        $("#pic").attr("src" , "<c:url value='/people_pic/${adminDto.pic}' />?xxx="+ new Date().getTime());
    		});
    		

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
    
    
    </script>
    
</html>