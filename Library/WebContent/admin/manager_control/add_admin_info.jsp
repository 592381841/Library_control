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
    <title>添加新管理员</title>

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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">添加新管理员</font>
   
       </div>
    
       
       </div>
 
       	<div class="row"> 

       	<div class="col-sm-7">
       	<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">基本信息</h3>
  </div>
  <div class="panel-body">
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td>昵称</td>
                                                    <td><input type="text" class="form-control" id="adminName" placeholder="输入昵称"></td>
                                              
                                                
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                   <td><input type="text" class="form-control" id="id_card" placeholder="身份证号码"></td>
                                            
                                            </tr>
                                            <tr>
                                            <td>账号</td>
                                            <td>${account }</td>
                                            </tr>
                                            <tr>
                                            <td>默认密码</td>
                                            <td>${newPasswd }</td>
                                            </tr>

                                               <tr>
                                                <td>状态：</td>
                                                <td> <div class="btn-group">
  <button class="btn btn-default  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <input type="hidden" id="aisStatus" value="true" />
    <font id="dropdownSelectText">激活</font> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
<!--	  <li><a onClick="">作者名</a></li>-->
	  <li><a onClick="dropdownText(this)">停用</a></li>
  </ul>
</div></td>
                                      
                                            </tr>
     
                                        </tbody>
                                    </table>
                              </div>
                              </div>
                              
                                
                                

                           
       	</div>
       	       	<div class="col-sm-5">
       		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">照片</h3>
  </div>
  <div class="panel-body">
  <div class="row">
 <div class="col-xs-4 ">

   <img  id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;" src="<c:url value='/lib/images/default_pic.gif' />">
	 </div>
	 <div class="col-xs-5">
	  <form >
  <input class="form-control" type="file" id="upLoadFile" onchange="preview()">
 </form>
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
                                                <th> 用户类型管理</th>
                                                <th>读者管理</th>
                                                <th>借阅管理</th>
                                         
											
                                            </tr>
                                        </thead>
                                        <tbody>
                                           
                                                                     <tr>
                                                                
                                         
                                                
                                               <td><input type="checkbox" class="checkbox_Id_list" /></td>
                                               <td><input type="checkbox" class="checkbox_Id_list" /></td>
                                               <td><input type="checkbox" class="checkbox_Id_list" /></td>
                                             <td><input type="checkbox" class="checkbox_Id_list" /></td>
                 
                                            </tr>
                                           
                                        </tbody>
                                    </table>
									</div>
					
					
				</div>
				
				
			</div>
  			
  		</div>
  		
  		<div class="row" style="margin-bottom: 1%">
  		<div class="col-sm-2 col-sm-offset-9">
  			<a onclick="add_admin()" class="btn btn-lg btn-primary btn-block pull-right"><i class="glyphicon glyphicon-plus
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
		  var old=$("#aisStatus");
		  if(old.val()=="true"){
			  old.val("false");
		  }else{
			  old.val("true");
		  }
		  
		  as.innerHTML=selectt.text();
		  selectt.text(oldtext);
	  }
		
	//新建
	    function add_admin(){
	    	var adminName=$("#adminName").val();
	    	var id_card=$("#id_card").val();
	    	var aisStatus=$("#aisStatus").val();
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
	   		  url:"<c:url value='/admin/manager_control/json_add_admin.action' />",
	   			type:"POST",
	   			data:{
	   				"adminDto.aisStatus" :aisStatus,
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
	   				if(jsonText.result){
	   					if($("#upLoadFile")[0].files[0]!=undefined){
							uploadPic(jsonText.pic);
						}else{
							 alert(jsonText.info);
							alert("无图片上传");
							
						}
	   					
	   					alert("账号信息\n 账号："+jsonText.account+"\n密码:"+jsonText.passwd+"\n呢称:"+jsonText.adminName+"\n身份证号:"+jsonText.id_card+"\n点击确认后，页面将自动关闭（请注意记录信息）");	   		
// 	   					window.location='<c:url value='/admin/manager_control/jump_manager_control.action' />';
	   				 location.reload();   
	   				}else{
	   				 alert(jsonText.info);
	   				}
	   				
	   				
	   		
	   				},error: function(){
	   				window.top.location='<c:url value='/error.jsp'/>';
	   			}
	   	  })
	    	
	    	
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