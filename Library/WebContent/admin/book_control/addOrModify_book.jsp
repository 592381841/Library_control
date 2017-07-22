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
      <c:choose>
      <c:when test="${ addOrmodify }">
    <title>添加书籍</title>
	</c:when>
	<c:otherwise>
	    <title>修改书籍</title>
	</c:otherwise>
	</c:choose>
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
   <c:choose>
      <c:when test="${ addOrmodify }">
  	<div id="contain" class="container-fluid">
  	</c:when>
  	<c:otherwise>
  	<div id="contain" class="container">
  	</c:otherwise>
  	</c:choose>
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
      <c:choose>
      <c:when test="${ addOrmodify }">
      <font style=" text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">添加新书籍</font>
      </c:when>
      <c:otherwise>
      
      <font style=" text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">书籍修改</font>
     <font style=" text-align:left;font-size:50px;color: #e31510 "><sub>--书码${bookInfoDto.bookId }</sub></font>
      </c:otherwise>
      </c:choose>
       
   
      
    
       
       </div>
       
 
       	<div class="row"> 

           							
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                           <tr>
                                           
											   <td><b>书名</b></td>
                                           	<td><input type="text" class="form-control" id="book_name" value="${bookInfoDto.bookName }" placeholder="输入书名"></td>
													
												<td><b>总数</b></td>
                                           		<td>
                                           	<c:if test="${addOrmodify }">
                                           			 <input type="text" class="form-control" id="book_num"   placeholder="输入书的总数">
                                           			 </c:if>
                                           			 <c:if test="${ not addOrmodify }">
                                           			 ${bookSum }
                                           			  </c:if>
                                           		</td>
                                           		
                                           </tr>
                                            <tr>
                                                <td><b>作者：</b></td>
                                                <td> <input type="text" class="form-control" id="writer" value="${bookInfoDto.bookWriter }" placeholder="输入作者名"></td>
                                                
                                                <td><b> 分类</b></td>
                                                <td> 
                                                <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font >${bookTypeDtos[0].bookTypeName }</font><input id="bookTypeID" type="hidden" value="${bookTypeDtos[0].botyId }" /> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" id="bookTypeCheck">
  
	  
	  <!-- 图书类型的下拉选项 -->
	  <c:forEach items="${bookTypeDtos }" var="bookTypeDto">
	   <li ><a onClick='dropdownText(this)'>${bookTypeDto.bookTypeName }</a><input type='hidden' value='${bookTypeDto.botyId}' /> </li>

	  </c:forEach>
	 
  </ul>
</div>
<a href="#" onclick="addBookType()">如果没有此分类，点击创建</a>
                                           </td>
                                            </tr>
                                         
                                            <tr>
                                                <td><b>出版社</b></td>
                                                <td> <input type="text" class="form-control" id="press"  value='${bookInfoDto.bookPress}' placeholder="输入出版社"></td>
                                           
                                           		 <c:if test="${ not addOrmodify }">
												<td><b>书码</b></td>
                                           		<td>
                                           	
                                           			 ${bookInfoDto.bookId }
                                           			
                                           		</td>
                                           		 </c:if>
                                           
                                           <c:if test="${  addOrmodify }">
                                           <td><b>书库/书架/编号</b></td>
                                                <td> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font >${stackRoomDtos[0].srName }</font><input type="hidden" value="${stackRoomDtos[0].id }" /> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
	<!-- 书库下拉框 -->	  
	  	  <c:forEach items="${stackRoomDtos }" var="stackRoomDto" >
	   <li ><a onClick="dropdownTextStackRoom(this)">${stackRoomDto.srName }</a><input type="hidden" value="${stackRoomDto.id }" /></li>

	  </c:forEach>
	 
  </ul>
</div> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="bookrackDtoFont">${bookrackDto.bookrName }</font><input id="bookrackDtoID" type="hidden" value="${bookrackDto.bookrackId }" />  <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" id="bookrackDtoCheck">
	<!-- 书架下拉项 -->
	    	  <c:forEach items="${bookrackDtos }" var="br">
<li><a onClick="dropdownTextBookrack(this)">${ br.bookrName}</a><input type="hidden" value="${ br.bookrackId}" /><input type="hidden" value="${br.brAddBookNumber+1 }" /> </li>
	  </c:forEach>
	 
  </ul>
</div> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="brAddBookNumber" >
    ${bookrackDto.brAddBookNumber+1 }
    </font> 
  </button>
  <ul class="dropdown-menu">
<!-- 书架中当前图书所放置的编号下拉项（一般是没有选择的） -->

  </ul>
</div></td>
</c:if>
                                            </tr>
                             
                                             
                               </tbody>
                                    </table>
                                     <c:if test="${not  addOrmodify }">
                                    <table class="table table-bordered">
                                                   
                                             	<tr>
                                             	<td>
                                             	藏书信息：(书库/书架/序号)
                                             	</td>
                                             	<td>
                                             	 ${bookInfoDto.collectInfo }
                                             	
                                             	 
                                             	</td>
                                             	</tr>
                                             	
                                             	 <c:if test="${not  bookUnuse }">
                                             	<tr>
                                             	<td><b>修改藏书信息（若无正确选择则无不会修改藏书信息）</b></td>
                                                <td> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font ></font><input type="hidden" value="" /> <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
	<!-- 书库下拉框 -->	  
	  	  <c:forEach items="${stackRoomDtos }" var="stackRoomDto" >
	   <li ><a onClick="dropdownTextStackRoom(this)">${stackRoomDto.srName }</a><input type="hidden" value="${stackRoomDto.id }" /></li>

	  </c:forEach>
	 
  </ul>
</div> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="bookrackDtoFont"></font><input id="bookrackDtoID" type="hidden" value="" />  <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" id="bookrackDtoCheck">
	<!-- 书架下拉项 -->

	 
  </ul>
</div> <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <font id="brAddBookNumber" >
    
    </font> 
  </button>
  <ul class="dropdown-menu">
<!-- 书架中当前图书所放置的编号下拉项（一般是没有选择的） -->

  </ul>
</div></td>
                                             	</tr>
                                             	</c:if>
                                           
                                    </table>
                                </c:if>
                                
                                

                           
       	</div>
     	       		
     	       		       	<div class="row">
       		      	     		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">简介</h3>
  </div>
  <div class="panel-body" style="font-size: 10px">
   <textarea id="bookIntroduce"    style="width:100%;resize: none; min-height: 300px" class="form-control" placeholder=" 请输入图书馆简介" >
<c:if test="${not addOrmodify }">${bookInfoDto.bookIntroduce }</c:if>
</textarea>
  </div>
</div>
</div>
				<div class="row" style="margin-bottom: 1%"> 
			<div class="col-sm-12 ">
			<c:choose>
			<c:when test="${addOrmodify }">
			<a onclick="add_bookInfo()" class="btn btn-primary btn-lg pull-right"> <i class="glyphicon glyphicon-plus
"></i>添加</a> 
			</c:when>
			<c:otherwise>
			<a onclick="modify_bookInfo()" class="btn btn-primary  "> <i class="glyphicon glyphicon-pencil
"></i>修改</a> 




			</c:otherwise>
			
			</c:choose>
										

											
		
                                                    
                         
                                                     
                                                        
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
		
		//图书分类下拉js
	  function dropdownText(as){
		  var input=$(as).next();
		  var selecttext=$(as).parent().parent().prev().children(":first");
		  var selectInput= $(selecttext).next();
// 		  $(selectInput).val($(selectt).next().val());
// 		  as.innerHTML=selectt.text();
		 selectInput.val($(input).val());
		  selecttext.text(as.text);
// 		  $(as).parent().parent().children("[hidden='hidden']").removeAttr("hidden");
// 		  $(as).parent().attr("hidden","hidden");
	  }
		//书架下拉
		function dropdownTextBookrack(as){
			  var BookrackIDinput=$(as).next();
			  var BookrackAddBooknumber=$(as).next().next().val();
			  var selecttext=$(as).parent().parent().prev().children(":first");
			  var selectInput= $(selecttext).next();
				
			 selectInput.val($(BookrackIDinput).val());
			  selecttext.text(as.text);
			$("#brAddBookNumber").text(BookrackAddBooknumber);
			
				
// 				modifyBookrackDtoCheck($(BookrackIDinput).val());
				
		}
		//书库下拉
	  function dropdownTextStackRoom(as){
		  var input=$(as).next();
		  var selecttext=$(as).parent().parent().prev().children(":first");
		  var selectInput= $(selecttext).next();
		 selectInput.val($(input).val());
		  selecttext.text(as.text);
			$.ajax({
				  url:"<c:url value='/admin/book_control/json_modifyBookrackCheck.action' />",
					type:"POST",
					data:{
						id : $(selectInput).val(),
						"bookInfoDto.id" : "${bookInfoDto.id}"
					
					},
					async : true,
					cache : false,
					dateType : "json",
					success:function(data){
						var json =eval(data);
						if(json.bookrack!=null){
							var br=json.bookrack
							$("#bookrackDtoCheck").children().remove();
							for(var i=0;i<br.length;i++){
								var number=br[i].brAddBookNumber+1;
								if(i==0){
									$("#bookrackDtoFont").text(br[i].bookrName);
									$("#bookrackDtoFont").next().val(br[i].bookrackId);
									
									$("#brAddBookNumber").text(number);
									
// 									modifyBookrackDtoCheck(br[i].bookrackId);
								
									
								}
									$("#bookrackDtoCheck").append("<li><a onClick='dropdownTextBookrack(this)'>"+br[i].bookrName+"</a><input type='hidden' value='"+br[i].bookrackId+"' /><input type='hidden' value='"+number+"' /> </li>")
								
							}
						}

				
						
					},error: function(){
						window.top.location='<c:url value='/error.jsp'/>';
					}
					
			 });
	  }
		
		
		//新建
		function add_bookInfo(){
		var	book_name=$("#book_name").val();
		var	book_num=$("#book_num").val();
		var	writer=$("#writer").val();
		var	bookTypeID=$("#bookTypeID").val();
		var	press=$("#press").val();
		var bookIntroduce=$("#bookIntroduce").val();
		var	bookrackDtoID=$("#bookrackDtoID").val();
			$.ajax({
				  url:"<c:url value='/admin/book_control/json_addBookinfo.action' />",
					type:"POST",
					data:{
						"bookInfoDto.bookName" : book_name,
						"bookInfoDto.bookPress" :press,
						"bookInfoDto.bookWriter" :writer,
						"bookInfoDto.bookIntroduce" :bookIntroduce,
						"bookTypeDto.botyId" :bookTypeID,
						"bookSum" :book_num,
						"bookrackDto.bookrackId" :bookrackDtoID
					
					},
					async : true,
					cache : false,
					dateType : "json",
					success:function(data){
						var json =eval(data);
						alert(json.info);
						if(json.result){
							window.location="<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=true"
						}

				
						
					},error: function(){
						window.top.location='<c:url value='/error.jsp'/>';
					}
					
			 });
		}

		
		
		//修改
		function modify_bookInfo(){
		var	book_name=$("#book_name").val();
		var	writer=$("#writer").val();
		var	bookTypeID=$("#bookTypeID").val();
		var	press=$("#press").val();
		var	bookrackDtoID=$("#bookrackDtoID").val();
		alert(bookrackDtoID);
		var bookIntroduce=$("#bookIntroduce").val();
			$.ajax({
				  url:"<c:url value='/admin/book_control/json_modifyBookinfo.action' />",
					type:"POST",
					data:{
						"bookInfoDto.bookId" : "${bookInfoDto.bookId}",
						"bookInfoDto.bookName" : book_name,
						"bookInfoDto.bookPress" :press,
						"bookInfoDto.bookWriter" :writer,
						"bookInfoDto.bookIntroduce" :bookIntroduce,
						"bookTypeDto.botyId" :bookTypeID,
						"bookrackDto.bookrackId" :bookrackDtoID
					
					},
					async : true,
					cache : false,
					dateType : "json",
					success:function(data){
						var json =eval(data);
						alert(json.info);
						if(json.result){
							window.location=document.referrer;
							
						}
						
					},error: function(){
						window.top.location='<c:url value='/error.jsp'/>';
					}
					
			 });
		}


		function addBookType(){
			var name =prompt("请输入新的图书类型名");
			if(name!=null&&name.trim()!=""){
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
			 				alert("图书分类"+jsonText.info);
			 				if(jsonText.result){
			 					location.reload();
			 				}
						  
					  },error :function(){
						  window.top.location='<c:url value='/error.jsp'/>';
					  }
					  
				  })
			}
			else{
				alert("请填写正确参数");
			}
			
		}
	
	  
	  
	  </script>
  
  </body>
</html>