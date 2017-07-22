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
    <title>书库管理</title>

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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">书库管理</font>
   
       </div>
        </div>
			<div class="row"> 
			<div class="col-sm-8 col-sm-offset-2">    
			  <table class="table table-condensed">
                                        <thead>
                                        	<tr>
                                        		<th>书库名</th>
                                        		<th>地址</th>

                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                            <td><input type="text" class="form-control" id="name" placeholder=" 请输入书库名"></td>
                                            <td><input type="text" class="form-control" id="address" placeholder=" 请输入 书库地址"></td>
											<td><a onclick="add_stack()" class="btn btn-primary "> <i class="glyphicon glyphicon-plus"></i>添加新书库</a></td>
											</tr>
											
											</tbody>
				</table>	
			
			
				</div>
			</div>
			
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1" id="list_div">     
			
			           <table class="table table-condensed ">
                                        <thead>
                                        	<tr>
<!--                                         		<th>编号</th> -->
                                        		<th>书库名</th>
                                        		<th>地址</th>
                                        		<th>操作</th>
                                        	</tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                        <s:iterator value="stackRoomDtos" var="s">
                                         <tr>

<%--                                                     <td><s:property value='#s.id' /></td> --%>
                                              
                                               <td><input type="text" class="form-control" value="<s:property value='#s.srName' />" placeholder=" 请输入书库名"></td>
                                                <td><input type="text" class="form-control" value="<s:property value='#s.srAddress' />" placeholder=" 请输入地址"></td>
                                                <td class="actions">
                                                    <a onclick="modifyStackRoom(this,<s:property value='#s.id' />)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-pencil"></i>
                                                            修改
                                                        </button>
                                                    </a>
                                                                     <a href="<c:url value='/admin/stack_room_control/jump_bookrack_control.action' />?id=<s:property value='#s.id' />">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-zoom-in"></i>
                                                            详情
                                                        </button>
                                                    </a>
                                                    
                                                    
                                                          <a onclick="deleteStackRoom(<s:property value='#s.id' />)">
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
	  
function load_flist(data){
	alert(data);
}
//ajax处理manager列表
function load_list(data){
	var jsonText=eval(data);
				//清空list数据
				page=jsonText.pageInfo.page;
               $("#list_tbody").children("tr").remove();
               	//删除分页
					$("#list_page").children("li").remove();
					
                  //本来可用json
                  if(jsonText.stackRooms==null){
                  //无数据的时候；添加blank_Div的，删除分页栏
//                   $("#list_div").append($("<div id='blank_div'  class='hero-unit'>  <h1>无任何数据</h1>  <p>No thing</p></div> "));
                  }
                  else{
                  //删除blank_Div的
            
//                    $("#blank_div").remove();  
                   loadPage(jsonText.pageInfo.count);
                   var LAlist=jsonText.stackRooms
                   for(var i=0;i<LAlist.length;i++){
                	   var dto=LAlist[i];
                	   var str1=$("<tr></tr>");
						str1.append("<td><input type='text' class='form-control' value='"+dto.srName+"' placeholder='请输入书库名'></td>")
                	    str1.append("<td><input type='text' class='form-control' value='"+dto.srAddress+"' placeholder='请输入地址'></td>")                                              
                	    str1.append("<td class='actions'><a onclick='modifyStackRoom(this,"+dto.id+")'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-pencil'></i>修改</button></a> <a href='<c:url value='/admin/stack_room_control/jump_bookrack_control.action' />?id="+dto.id+"'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-zoom-in'></i>详情</button></a> <a onclick='deleteStackRoom(+"+dto.id+")'><button class='btn btn-sm btn-danger'><i class='glyphicon glyphicon-trash'></i>删除</button></a></td>");                                          
                	      
                	                                                                       
            		
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
                	"pageInfo.page" :page
                },
 	 			dataType: "json",
                url:"<c:url value='/admin/stack_room_control/json_pageStackRoom.action'/>",
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
	
	//删除书库
	function deleteStackRoom(id){
		  $.ajax({
			  cache: false,
	          type: "POST",
	          data:{
	        	  "id" :id,
	        	  "pageInfo.page" :page
	          },
				dataType: "json",
	          url:"<c:url value='/admin/stack_room_control/json_deleteStackRoom.action'/>",
	          async:true,
	          success:function (data){
	        	  var s=eval(data);
	        	  alert(s.info);
	        	  if(s.result){
	        		   load_list(data);
	        	  }
	       
	          },error: function(){	 
					window.top.location='<c:url value='/error.jsp'/>';
				}
			  
		  })
	}
	
	//修改书库
	function modifyStackRoom(a,id){
		var address=$($(a).parent().prev().children()[0]).val();
		var name=$($(a).parent().prev().prev().children()[0]).val();
		
		  $.ajax({
			  cache: false,
	          type: "POST",
	          data:{
	        	  "stackRoom.id" :id,
	          	"stackRoom.srName" :name,
	          	"stackRoom.srAddress" :address

	          },
				dataType: "json",
	          url:"<c:url value='/admin/stack_room_control/json_modifyStackRoom.action'/>",
	          async:true,
	          success:function (data){
	        	  var s=eval(data);
	        	  alert(s.info);

	          },error: function(){	 
					window.top.location='<c:url value='/error.jsp'/>';
				}
			  
		  })
		
	}
  //添加书库
 function add_stack(){
	  var name=$("#name").val();
	  var address=$("#address").val();
	  $.ajax({
		  cache: false,
          type: "POST",
          data:{
          	"stackRoom.srName" :name,
          	"stackRoom.srAddress" :address,
          	"pageInfo.page" :page
          },
			dataType: "json",
          url:"<c:url value='/admin/stack_room_control/json_addStackRoom.action'/>",
          async:true,
          success:function (data){
        	  var d=eval(data)
        	  alert(d.info);
        	  if(d.result){
        		  load_list(data);
        	  }
         
          },error: function(){	 
				window.top.location='<c:url value='/error.jsp'/>';
			}
		  
	  })
	  
  }
  
  </script>
</html>