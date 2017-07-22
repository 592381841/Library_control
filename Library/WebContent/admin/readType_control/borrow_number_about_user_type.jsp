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
    <title>读者类型及借阅数</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">
<style type="text/css">
	  
	  	  #contain{
		  border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
		  box-shadow: 10px 10px 5px #888888;
		 
	  }
	@font-face{
				font-family: titie_1;
					src:url('<c:url value='/lib/fonts/fanti_title.ttf' />');
			}
	  
	  
	  </style>
  </head>
  <body>
  	<div id="contain" class="container-fluid">
  		<div class="row">
  			
						<div class="row">      
       <div class="col-sm-6 col-sm-offset-3">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">用户类型管理</font>
   
       </div>
        </div>
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2">    
			  <table class="table table-bordered">
                                        <thead>
                                        	<tr>
                                        		<th>读者类型</th>
                                        		<th>借阅数</th>
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                            <td><input type="text" class="form-control" id="type_name" placeholder=" 请输入类型名"></td>
                                            <td><input type="text" class="form-control" id="type_number" placeholder=" 请输入借阅数"></td>
											<td><a onclick="addReaderType()" class="btn btn-primary "> <i class="glyphicon glyphicon-plus"></i>添加新类型</a></td>
											</tr>
											
											</tbody>
				</table>	
			
			
				</div>
			</div>
			
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2">     
			
			           <table class="table table-bordered">
                                        <thead>
                                        	<tr>
                                        		<th>读者类型</th>
                                        		<th>借阅数</th>
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="readerTypeDtos" var="s">
                                        <tr>
                                                <td><input type="text" class="form-control" value="<s:property value='#s.typeName'/>" placeholder=" 请输入分类名"></td>
                                                    <td><input type="text" class="form-control"  value="<s:property value="#s.borrow"/>" placeholder=" 请输入数量"></td>
                                                
                                              <td class="actions">
                         
                          								   <a onclick="modify_readerType(<s:property value="#s.id"/>,this)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-trash"></i>
                                                           修改
                                                        </button>
                                                    </a>
                                                           <a onclick="delete_readerType(<s:property value="#s.id"/>)">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-trash"></i>
                                                           删除
                                                        </button>
                                                    </a>
                                                    
                                                   
                                                
                                                </td>
                                                
                                            </tr>
                                        </s:iterator>
                                            
                                        </tbody>
                                    </table>


                                    </div>
                                    </div>   
                                    
                                      
  			
  		</div>
  		
  	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
 <script type="text/javascript">
 //删除
 	function delete_readerType(id){
 		
 		 $.ajax({
			  url:"<c:url value='/admin/readType_control/json_deleteReaderType.action' />",
				type:"POST",
				data:{
					"readerTypeDto.id" :id	
				},
				async : true,
				cache : false,
				dateType : "json",
				success:function(data){
					var json =eval(data);
					alert(json.info);
					if(json.result){
						location.reload();
					}

				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
				
		 });
 		
 		
 	}
 	
 	//修改
 	function modify_readerType(id,athis){
 		var number=$($(athis).parent().prev().children()[0]).val();
 		var name=$($(athis).parent().prev().prev().children()[0]).val();
 		
 		 $.ajax({
			  url:"<c:url value='/admin/readType_control/json_modifyReaderType.action' />",
				type:"POST",
				data:{
					"readerTypeDto.id" :id,
					"readerTypeDto.typeName" :name,
					"readerTypeDto.borrow" :number	
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
 	//添加
 	function addReaderType(){
 		var name = $("#type_name").val();
 		var number = $("#type_number").val();
 		$.ajax({
			  url:"<c:url value='/admin/readType_control/json_addReaderType.action' />",
				type:"POST",
				data:{
					"readerTypeDto.typeName" :name,
					"readerTypeDto.borrow" :number	
				},
				async : true,
				cache : false,
				dateType : "json",
				success:function(data){
					var json =eval(data);
					alert(json.info);
					if(json.result){
						location.reload();
					}
	

				},error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
				
		 });
 		
 	}
 
 </script>
 
  </body>
</html>