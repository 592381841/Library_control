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
    <title>读者管理</title>

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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">读者信息管理</font>
   
       </div>
        </div>
			<div class="row" style="margin-left: 1%"> 
			<div class="col-sm-12"> 
			<form class="form-inline">
			<div class="form-group">
    <label class="sr-only"></label>
	  <a href="<c:url value='/admin/reader_control/jump_add_reader_info.action' />" target="_blank" class="btn btn-primary"><i class="glyphicon glyphicon-plus
"></i>添加读者</a>
  </div>
  <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_modify_status(true)"  class="btn btn-primary"><i class="glyphicon glyphicon-ok
"></i>批量激活</a>
  </div>
    <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_modify_status(false)" class="btn btn-danger"><i class="glyphicon glyphicon-remove
"></i>批量禁用</a>
  </div>
    <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_delete_user()" class="btn btn-danger"><i class="glyphicon glyphicon-trash
"></i>批量删除</a>
  </div>
      <div class="form-group">
    <label class="sr-only"></label>
	  <a href="<c:url value='/admin/reader_control/keyword_user_search.jsp' />" class="btn btn-primary" ><i class="glyphicon glyphicon-zoom-in
"></i>人员检索页面</a>
  </div>
 
</form>
			
				</div>
					</div>
			
			<div class="row"> 
			<div class="col-sm-12">     


  		       <table class="table">
                                        <thead>
                                            <tr>
                                               <th><input type="checkbox" onchange="checkbox_Id_allChange()"  /></th>
                                                <th>账号</th>
                                                <th>姓名 </th>
                                                <th>身份证号</th>
                                                <th> 读者类型</th>
                                               
												<th>状态</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id='list_tbody'>
                                        <s:iterator var="s" value="readerDtos">
                                        
                                       
                                            <tr>
                                               <td><input type="checkbox" class="checkbox_Id_list1" /></td>
                                                <td><s:property value="#s.account"/></td>
                                                <td><s:property value="#s.usersName"/></td>
                                               <td><s:property value="#s.idCard"/></td>
                                               <td><s:property value="#s.readerTypeDto.typeName"/></td>
                                               <td>
                                               <s:if test="#s.risStatus">
                                               	<font color="#428BCA"  >正常</font>
                                               </s:if>
                                              <s:else>
                                                <font color="#D9534F">禁用</font>
                                               </s:else>
                                               </td>
                                                <td class="actions">
                                                <a href="<c:url value='/admin/reader_control/jump_reader_info_setting.action' />?account=<s:property value='#s.account' />" target="_blank">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-zoom-in
"></i>
                                                           详情
                                                        </button>
                                                    </a>
                                                
                                                     <s:if test="#s.risStatus">
                                               	<a onclick="modify_is_status(false,<s:property value='#s.account' />,this)">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-remove
"></i>
                                                           禁用
                                                        </button>
                                                    </a>
                                               </s:if>
                                              <s:else>
                                                <a onclick="modify_is_status(true,<s:property value='#s.account' />,this)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-ok
"></i>
                                                           激活
                                                        </button>
                                                    </a>
                                               </s:else>
                                                
                                                    
                                                    
                                                     
                                                           
                                                           <a onclick="delete_user(<s:property value='#s.account' />)">
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
    <script type="text/javascript">
		
		
		//用户复选框
		function checkbox_Id_allChange(){
	var list=$(".checkbox_Id_list1");

	for(var i=0;i<list.length;i++){
	//直接使用js
		if(	list[i].checked==false){
			list[i].checked=true;
		}
		else{
			list[i].checked=false;
		}


}
};
		
		
	  //分页
		  
//当页面加载时，默认为1（在JavaScript的最开始）
var page=1;
//加载分页
function load_list(data){

	var jsonData=eval(data);
	//清空list数据
	page=jsonData.pageInfo.page;
	
   $("#list_tbody").children("tr").remove();
   	//删除分页
		$("#list_page").children("li").remove();
		 loadPage(jsonData.pageInfo.count);
		 readerDtos=jsonData.readerInfoDtos;
		 if(readerDtos ==null)return;
		 for(var i=0;i<readerDtos.length;i++){
			 var s=readerDtos[i];
			 var tr=$("<tr></tr>");
			 $(tr).append("<td><input type='checkbox' class='checkbox_Id_list1' /></td><td>"+s.account+"</td><td>"+s.usersName+"</td><td>"+s.idCard+"</td><td>"+s.readerTypeDto.typeName+"</td>");
			if(s.risStatus){
				 $(tr).append("<td><font color='#428BCA'  >正常</font></td>");
			}else{
				 $(tr).append(" <td><font color='#D9534F'>禁用</font></td>");
			}
			var btr=$("<td class='actions'></td>");
			$(btr).append("<a href='<c:url value='/admin/reader_control/jump_reader_info_setting.action' />?account="+s.account+"' target='_blank'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-zoom-in'></i>详情</button></a> ");
			if(s.risStatus){
				 $(btr).append("<a onclick='modify_is_status(false,"+s.account+",this)'><button class='btn btn-sm btn-danger'><i class='glyphicon glyphicon-remove'></i>禁用</button></a> ");
			}else{
				 $(btr).append("<a onclick='modify_is_status(true,"+s.account+",this)'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-ok'></i>激活</button> </a> ");
			}
			$(btr).append("<a onclick='delete_user("+s.account+")'><button class='btn btn-sm btn-danger'><i class='glyphicon glyphicon-trash'></i>删除</button></a> ");
	 $(tr).append(btr);
	 $("#list_tbody").append(tr);
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
                url:"<c:url value='/admin/reader_control/json_pageReaderInfo.action'/>",
                data :{
                	"pageInfo.page" : page
                },
                async:true,
                success:function (data){
                load_list(data);
                }
            });
};



//当点击加载页面按钮，则加载分页栏
$(function(){
loadPage("${pageInfo.count}");
	}); 
	  
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


//删除用户
function delete_user(account){
	

			if (confirm("你确定删除此用户？")) { 
				 $.ajax({
					  url:"<c:url value='/admin/reader_control/json_delete_reader.action' />",
						type:"POST",
						data:{
							account : account
							
						},
						async : true,
						cache : false,
						dateType : "json",
						success:function(data){
							var json =eval(data);
							alert(json.info);
							if(json.result){
								$.ajax({
							 		
					                cache: false,
					                type: "POST",
					                url:"<c:url value='/admin/reader_control/json_pageReaderInfo.action'/>",
					                data :{
					                	"pageInfo.page" :page
					                },
					                async:true,
					                success:function (data){
					                load_list(data);
					                }
					            });
								//重新加载页面
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
				
	
	
	
	
	
	
	
	

//获得array数组
	function findCheckId(){
		var array = new Array()//创建一个数组
		var list=$(".checkbox_Id_list1")

		for(var i=0;i<list.length;i++){
			if(	list[i].checked==true){
				array.push($(list[i]).parent().next().text());
			}
		

		
	}
		return array.toString();
	}
 
 
 
 
 //批量删除
 	function more_delete_user(){
//	 		alert(findCheckId());
 		if (confirm("你确定批量删除用户？")) { 
			 $.ajax({
				  url:"<c:url value='/admin/reader_control/json_more_delete_reader.action' />",
					type:"POST",
					data:{
						accountListStr : findCheckId(),
						"pageInfo.page" : page
					},
					async : true,
					cache : false,
					dateType : "json",
					success:function(data){
						var json =eval(data);
						alert(json.info);
						if(json.result){
							$.ajax({
						 		
				                cache: false,
				                type: "POST",
				                url:"<c:url value='/admin/reader_control/json_pageReaderInfo.action'/>",
				                data :{
				                	"pageInfo.page" :page
				                },
				                async:true,
				                success:function (data){
				                load_list(data);
				                }
				            });
							//重新加载页面
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
 //批量禁止/激活
 		function more_modify_status(modify_status){
 			if (confirm("你确定批量操作用户？")) { 
 			  $.ajax({
 				  url:"<c:url value='/admin/reader_control/json_modify_more_reader_is_status.action' />",
 					type:"POST",
 					data:{
 						modify_status : modify_status,
 						accountListStr : findCheckId(),
						"pageInfo.page" : page
 					},
 					async : true,
 					cache : false,
 					dateType : "json",
 					success:function(data){	
 						var jsonText=eval(data);
 						alert(jsonText.info);
 						if(jsonText.result){
 							$.ajax({
						 		
				                cache: false,
				                type: "POST",
				                url:"<c:url value='/admin/reader_control/json_pageReaderInfo.action'/>",
				                data :{
				                	"pageInfo.page" :page
				                },
				                async:true,
				                success:function (data){
				                load_list(data);
				                }
				            });
							//重新加载页面
 								
 							}
 						},error: function(){	 
 						window.top.location='<c:url value='/error.jsp'/>';
 					}
 			  })
 			}
 			else{
 				
 			}
 			
 		}
	
	

	  
	  
	  </script>
  </body>
</html>