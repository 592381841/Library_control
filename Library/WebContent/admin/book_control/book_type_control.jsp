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
    <title>图书分类控制</title>

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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">图书类型</font>
   
       </div>
        </div>
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2">    
			  <table class="table table-bordered">
                                        <thead>
                                        	<tr>
                                        		<th>图书类名</th>
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                            <td><input type="text" class="form-control"  placeholder=" 请输入图书类名"></td>
											<td><a onclick="addBookType(this)" class="btn btn-primary "> <i class="glyphicon glyphicon-plus"></i>添加新类型</a></td>
											</tr>
											
											</tbody>
				</table>	
			
			
				</div>
			</div>
			
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2">     
			
			           <table class="table table-condensed ">
                                        <thead>
                                        	<tr>
                                        		<th>图书类名</th>
                                        		<th>图书数</th>
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="bookTypeDtos" var="ty">
                                            <tr>
                                           
                                                <td><input class="form-control"  value="<s:property value='#ty.bookTypeName' />" />
                                                <input type="hidden"  value="<s:property value='#ty.botyId' />" />
                                                
                                                </td>
                                                  <td><s:property value="#ty.size" /></td>
                                              <td class="actions">
                         
                                                           <a onclick="modifyType(this)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-pencil"></i>
                                                           修改
                                                        </button>
                                                    </a>
                                                    <a onclick="DeleteType(this)">
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
  </body>
  <script type="text/javascript">
  //删除类型
  function DeleteType(athis){
	  var id=$($(athis).parent().prev().prev().children()[1]).val();
	  $.ajax({
		  url: "<c:url value='/admin/book_control/json_delete_book_type.action' />",
		  data :{
			  "bookTypeDto.botyId" : id
		  },
		  async :true,
		  type:"POST",
		  cache :false,
		  dateType : "json",
		  success:function(data){
			  var jsonText=eval(data);
 				alert(jsonText.info);
 				if(jsonText.result){
 					location.reload();
 				}
			  
		  },error :function(){
			  window.top.location='<c:url value='/error.jsp'/>';
		  }
		  
	  })
  }
  //修改类型
  function modifyType(athis){
	  var name=$($(athis).parent().prev().prev().children()[0]).val();
	  var id=$($(athis).parent().prev().prev().children()[1]).val();
	  $.ajax({
		  url: "<c:url value='/admin/book_control/json_modify_book_type.action' />",
		  data :{
			  "bookTypeDto.botyId" : id,
			  "bookTypeDto.bookTypeName" :name
		  },
		  async :true,
		  type:"POST",
		  cache :false,
		  dateType : "json",
		  success:function(data){
			  var jsonText=eval(data);
 				alert(jsonText.info);
 				if(jsonText.result){
 					location.reload();
 				}
			  
		  },error :function(){
			  window.top.location='<c:url value='/error.jsp'/>';
		  }
		  
	  })
	  
  }
  //添加图书类型
  function addBookType(athis){
	  var name=$($(athis).parent().prev().children()[0]).val();
	  $.ajax({
		  url: "<c:url value='/admin/book_control/json_add_book_type.action' />",
		  data :{
			  "bookTypeDto.bookTypeName" :name
		  },
		  async :true,
		  type:"POST",
		  cache :false,
		  dateType : "json",
		  success:function(data){
			  var jsonText=eval(data);
 				alert(jsonText.info);
 				if(jsonText.result){
 					location.reload();
 				}
			  
		  },error :function(){
			  window.top.location='<c:url value='/error.jsp'/>';
		  }
		  
	  })
	  
  }
  </script>
</html>