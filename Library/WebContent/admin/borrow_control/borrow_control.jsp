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
    <title>图书借阅</title>

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
       <div class="col-sm-4 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">图书借阅</font>
   
       </div>
        </div>
        
          					<div class="row">      
      
    <form class="form-horizontal">
  <div class="form-group">
    <label for="book_id" class="col-sm-2 control-label">图书条形码</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="book_id" placeholder="请输入图像条形码">
    </div>
  </div>
  <div class="form-group">
    <label for="reader_id" class="col-sm-2 control-label">读者账号/条形码</label>
    <div class="col-sm-7">
      <input type="text" class="form-control" id="reader_id" placeholder="请输入读者账号">
    </div>
    <div class="col-sm-2">
       <a onclick="searchBookAndReader()" class="btn btn-primary btn-block">搜索</a>
    </div>
  </div>


</form>
   
      
    
       
       </div>
        
			
			
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1">   
			  <div class="panel panel-primary">
  <div class="panel-heading">搜索结果</div>
  <div class="panel-body">
   <div class="col-sm-8">
    <table class="table table-bordered">
                                        
                                        <tbody>
                                           <tr>
                                           <td> <b>读者账号</b></td>
                                           <td id="account"></td>
                                           
                                           <td><b>图书名</b></td>
                                            <td id="bookName"></td>
                                          
											</tr>
											<tr>
												<td><b>读者姓名</b></td>
                                           <td id="readerName"></td>
                                            <td><b>图书唯一标识码</b></td>
                                            <td id="bookuniqueId"></td>
                                            
                                            </tr>
												<tr>
												 <td><b>读者类型</b></td>
                                            <td id="readerType"></td>
												<td><b>作者</b></td>
                                            <td id="writer"></td>
                                            
											</tr>
												<tr>
												<td><b>状态</b></td>
                                            <td id="risStatus"></td>
                                             <td><b>出版社</b></td>
                                            <td id="press"></td>
											</tr>
											
											 <tr>
                                                <td><b></b></td>
                                                <td></td>
                                           <td><b>书库/书架/编号</b></td>
                                                <td id="localtion"></td>
                                            </tr>
                                           
                                            
		</tbody>
	  </table>
	  </div>
	  <input value=""  id="borrowID" type="hidden">
	    
 <div class="col-xs-3 col-xs-offset-1">
   <img  id="pic" data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;"  src="<c:url value='/lib/images/default_pic.gif' />" onerror="this.src='<c:url value='/lib/images/default_pic.gif' />'" />
	 </div>
    
  </div>
</div>

                                    </div>
                                    </div>    
                                    <div style="margin-bottom: 1%" class="row">
                                    	<div class="col-sm-2 col-sm-offset-1 " id="borrow_history">
									
								
								</div>
								<div class="col-sm-2 col-sm-offset-6" id="btn_modify">
								
									
									
								</div>
                                    	
                                    	
                                    	
                                    </div>  
  			
  		</div>
  		
  	</div>

 <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script type="text/javascript">
    	function searchBookAndReader(){
    		$.ajax({
    	  		url:"<c:url value='/admin/borrow_control/json_searchBookAndReader.action' />",
    	  		type : "POST",
    	  		 data:{
    	  			 "id" : $("#book_id").val(),
    	  			 "account" : $("#reader_id").val()	
    	  		 },
    	  		async : true,
    				cache : false,
    				dateType : "json",
    				success:function(data){
    					var json =eval(data);
    					$("#borrow_history").children().remove();
    					$("#btn_modify").children().remove();
    					if(json.result){
    						var flag =false;
    						if(json.readerDto!= undefined ){
    							reader=json.readerDto;
    							$("#pic").attr("src" , " <c:url value='/people_pic/"+json.readerDto.pic+"' />?xxx=" + new Date().getTime());
    							$("#readerName").text(reader.usersName);
    							$("#account").text(reader.account);
    							$("#readerType").text(reader.readerTypeDto.typeName);
    							if(reader.risStatus){
    								$("#risStatus").text("正常");
    								flag=true;
    							}else{
    								$("#risStatus").text("停用");
    							}
    							$("#borrow_history").append("<a href='<c:url value='/admin/borrow_control/jump_reader_borrow_history.action' />?account="+reader.account+"' target='_blank' class='btn btn-primary pull-left btn-block'>读者借阅记录</a>");
    									
    							
    						}
    						if(json.bookInfoDto != undefined){
    							var book=json.bookInfoDto
    							$("#bookuniqueId").text(book.id);
    							$("#bookName").text(book.bookName);
    							$("#writer").text(book.bookWriter);
    							$("#press").text(book.bookPress);
    							$("#localtion").text(json.stackRoom+"/"+json.bookRackName+"/"+book.bookrackNumber);
    							
    						}
    						if(json.bookInfoDto != undefined && json.readerDto!= undefined){
    							if(json.readerDto.risStatus && (!json.bookInfoDto.bookIsBorrow)){
    								$("#btn_modify").append("<a onclick='borrowBook()' class='btn btn-block btn-primary'>借书</a>");
    								
    							}else if(json.borrowInfoID!=null){
    								$("#borrowID").val(json.borrowInfoID);
    								$("#btn_modify").append("<a href='javascript:void(0)' onclick='returnBook()' class='btn btn-block btn-success '>还书</a>")
    								
    							}else{
    								alert("该书已经被借出");
    							}
    							
    							
    							
    							
    							
    						}
    						
    					}else{
    						alert(json.info);	
    					}
    			
    					
    					
    				},error: function(){
    					window.top.location='<c:url value='/error.jsp'/>';
    				}
    	  		
    	  	});
    		
    		
    	}
    	//借书
    	function borrowBook(){
    		$.ajax({
    	  		url:"<c:url value='/admin/borrow_control/json_borrow_book.action' />",
    	  		type : "POST",
    	  		 data:{
    	  			"id" : $("#bookuniqueId").text(),
   	  			 "account" : $("#account").text()
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
    	//还书
    	function returnBook(){
    		
    		$.ajax({
    	  		url:"<c:url value='/admin/borrow_control/json_return_book.action' />",
    	  		type : "POST",
    	  		 data:{
    	  			 "borrowInfo.id" : $("#borrowID").val()
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