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
    <title>书架管理</title>

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
src:url('<c:url value='/lib/fonts/fanti_title.ttf' />')
				}
	  
	  
	  </style>
  </head>
  <body>
  	<div id="contain" class="container-fluid">
  		<div class="row">
  			
						<div class="row">      
       <div class="col-sm-4 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">书架管理</font>
       </div>
              <div class="col-sm-2 col-sm-offset-5">
       <font style="text-align:left;font-size:20px;font-family:titie_1;color: #e31510 ">书库名：${stackRoom.srName}</font>
       </div>
            <div class="col-sm-2 col-sm-offset-5">
       <font style="text-align:left;font-size:20px;font-family:titie_1;color: #e31510 ">书库地址：${stackRoom.srAddress}</font>
       </div>
       
        </div>
			<div class="row"> 
			<div class="col-sm-6 col-sm-offset-3">    
			  <table class="table table-condensed">
                                        <thead>
                                        	<tr>
                                        		<th>书架名</th>
                                        
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                            <td><input type="text" class="form-control" id="brName" placeholder=" 请输入书架名"></td>
                                         
											<td><a onclick="add_bookRack(${stackRoom.id})" class="btn btn-primary "> <i class="glyphicon glyphicon-plus"></i>添加新书架</a></td>
											</tr>
											
											</tbody>
				</table>	
			
			
				</div>
			</div>
			
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2" id="list_div">     
			
			           <table class="table table-bordered">
                                        <thead>
                                        	<tr>
                                        		<th>书架名</th>
<!-- 												<th>藏书数</th> -->
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                        <s:iterator value="bookrackDtos" var="s">
                                          <tr>
                                           <td><input class="form-control" type="text" value="<s:property value="#s.bookrName" />" placeholder="请输入书架名" /></td>
<%--                                           <td><font color="#e31510"><s:property value="#s.brBookNumber" /></font></td> --%>
                                       
                                        
                                                <td class="actions">
                                                    <a onclick="modify_bookRack(this,<s:property value='#s.bookrackId' />)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-pencil"></i>
                                                            修改
                                                        </button>
                                                    </a>
                                                          <a  onclick="delete_bookRack(<s:property value='#s.bookrackId' />)">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-trash"></i>
                                                            删除
                                                        </button>
                                                    </a>
                                                          <a target="black" href="<c:url value='/admin/stack_room_control/jump_bookrack_info.action' />?id=<s:property value='#s.bookrackId' />&stackRoom.id=<s:property value='stackRoom.id' />">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-zoom-in"></i>
                                                            书架详情
                                                        </button>
                                                    </a>
												</td>
                                                
                                            </tr>
                                        
                                        
                                        </s:iterator>
                                          

                                                    
                                              

                                                
                                              
                                  
     
                                        </tbody>
                                    </table>
                                    <nav style="text-align: right">
  <ul id="list_page" class=" pagination  pagination-sm">

  </ul>
</nav>


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
  
  //分页
  
//当页面加载时，默认为1（在JavaScript的最开始）
var page=1;
	  
// function load_list(data){
// }
//ajax处理manager列表
function load_list(data){
	var jsonText=eval(data);
				//清空list数据
				page=jsonText.pageInfo.page;
               $("#list_tbody").children("tr").remove();
               	//删除分页
					$("#list_page").children("li").remove();
					
                  //本来可用json
                  if(jsonText.bookrack==null){
                  //无数据的时候；添加blank_Div的，删除分页栏
//                   $("#list_div").append($("<div id='blank_div'  class='hero-unit'>  <h1>无任何数据</h1>  <p>No thing</p></div> "));
                  }
                  else{
                  //删除blank_Div的
//                    $("#blank_div").remove();  
                   loadPage(jsonText.pageInfo.count);
                   var LAlist=jsonText.bookrack
                   for(var i=0;i<LAlist.length;i++){
                	   var dto=LAlist[i];
                	   var str1=$("<tr></tr>");
						str1.append("<td><input type='text' class='form-control' value='"+dto.bookrName+"' placeholder='请输入书架名'></td>")
//                 	    str1.append("<td><font color='#e31510'>"+dto.brBookNumber+"</font></td>")                                              
                	    str1.append("<td class='actions'><a onclick='modify_bookRack(this,"+dto.bookrackId+")'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-pencil'></i>修改</button></a> <a  onclick='delete_bookRack("+dto.bookrackId+")'><button class='btn btn-sm btn-danger'><i class='glyphicon glyphicon-trash'></i>删除</button></a> <a target='black' href='<c:url value='/admin/stack_room_control/jump_bookrack_info.action' />?id="+dto.bookrackId+"&stackRoom.id=${stackRoom.id}'><button class='btn btn-sm btn-primary'><i class=’glyphicon glyphicon-zoom-in’></i>书架详情</button></a></td>");                                          
                	      
                	      
                        
                     
                      
                              
						
						
            		
                	   $("#list_tbody").append(str1);
                   }
                   
                  
                  }
}



//分页   动态加载函数（pagecount为后台计算·的总页数）
function loadPage(pagecount){

	var begin;
	pageCount=pagecount
	var end;
	$("#list_page").append("<li id='firstPage'><a  href='javascript:void(0)' onclick='pageClick(this)'>首页</a></li>");
	$("#list_page").append(" <li><a    href='javascript:void(0)' onclick='pageClick(this)'>尾页</a></li>");
	if(pageCount>1&&page!=pageCount){
	$("#firstPage").after(" <li><a href='javascript:void(0)' onclick='pageClick(this)'>下一页</a></li>");

	}
	if(pageCount<=5){
		begin=1;
		end=pageCount;
	}
	else{
			begin=page-2;
			end=page+2;
	
		if(begin<=0){
			begin=1;
			end=5;
		}
		else if(end>pageCount){
			end=pageCount;
			begin=pageCount-4; 
			
		}
	}
	for(;begin<=end;end--){
	if(end==page){
	$("#firstPage").after(" <li class='active'><a href='javascript:void(0)' onclick='pageClick(this)'>"+end+"</a></li>");
	
	}
	else{
	$("#firstPage").after(" <li><a href='javascript:void(0)' onclick='pageClick(this)'>"+end+"</a></li>");
	}
}

	if(pageCount>1&&page!=1){
	$("#firstPage").after(" <li><a href='javascript:void(0)' onclick='pageClick(this)'>上一页</a></li>");
	}
		
}


//为分页栏按钮添加事件

function pageClick(e){
	var pageButton=e.text;
	if(pageButton=="首页"){
			page=1;
		}
	else if(pageButton=="尾页"){
		page=pageCount
	
		}
	else if(pageButton=="下一页"){
			page+=1;
	}
	else if(pageButton=="上一页"){
				page-=1;
	}
	else{
		page=Number(e.text);
			
		}
	
 	 $.ajax({
                cache: false,
                type: "POST",
                data:{
                	"pageInfo.page" :page,
                	"stackRoom.id"  :"${stackRoom.id}"
                },
 	 			dataType: "json",
                url:"<c:url value='/admin/stack_room_control/json_pageBookrack.action'/>?",
                async:true,
                success:function (data){
                load_list(data);
                },error: function(){	 
    				window.top.location='<c:url value='/error.jsp'/>';
    			}
            });
};



//当点击加载页面按钮，则加载分页栏
$(function(){
loadPage("${pageInfo.count}");
	});
	
	//修改书架信息
	function modify_bookRack(a,id){
		var name=$($(a).parent().prev().prev().children()[0]).val();
		
		  $.ajax({
			  cache: false,
	          type: "POST",
	          data:{
	        	  "bookrackDto.bookrackId" :id,
	          	"bookrackDto.bookrName" :name

	          },
				dataType: "json",
	          url:"<c:url value='/admin/stack_room_control/json_modifyBookrack.action'/>",
	          async:true,
	          success:function (data){
	        	  var s=eval(data);
	        	  alert(s.info);

	          },error: function(){	 
					window.top.location='<c:url value='/error.jsp'/>';
				}
			  
		  })
		
	}
	//删除书架
	function delete_bookRack(id){

		  $.ajax({
			  cache: false,
	          type: "POST",
	          data:{
	        	  "id" :id,
	        	  "pageInfo.page" :page

	          },
				dataType: "json",
	          url:"<c:url value='/admin/stack_room_control/json_deleteBookrack.action'/>",
	          async:true,
	          success:function (data){
	        	  var s=eval(data);
	        	  alert(s.info);
	        	  if(s.result){
	        		  load_list(data);
	        	  }

	          },error: function(){	 
// 					window.top.location='<c:url value='/error.jsp'/>';
				}
			  
		  })
	}
	//添加书架
	function add_bookRack(stackroom){
		var bookrackSrname=$("#brName").val();
		  $.ajax({
			  cache: false,
	          type: "POST",
	          data:{
	        	  "id" :stackroom,
	        	  "bookrackDto.bookrName" :bookrackSrname

	          },
				dataType: "json",
	          url:"<c:url value='/admin/stack_room_control/json_addBookrack.action'/>",
	          async:true,
	          success:function (data){
	        	  var s=eval(data);
	        	  alert(s.info);
	        	  if(s.result){
	        		  $("#brName").val("");
	        		  load_list(data);
	        	  }

	          },error: function(){	 
	        	  
					window.top.location='<c:url value='/error.jsp'/>';
				}
			  
		  })
		
		
	}
	
	</script>
    
</html>