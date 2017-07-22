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
    <title>管理员信息管理</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">
<style type="text/css">
html { overflow-x:hidden; }
	  
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
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">管理员信息管理</font>
   
       </div>
        </div>
			<div class="row" style="margin-left: 1%"> 
			<div class="col-sm-12"> 
			<form class="form-inline">
			<div class="form-group">
    <label class="sr-only"></label>
	  <a href="<c:url value='/admin/manager_control/jump_add_admin_info.action' />"  class="btn btn-primary"><i class="glyphicon glyphicon-plus
"></i>添加管理员</a>
  </div>
  <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_modify_status(true)" class="btn btn-primary"><i class="glyphicon glyphicon-ok
 "></i>批量激活</a> 
  </div>
    <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_modify_status(false)" class="btn btn-danger"><i class="glyphicon glyphicon-remove
"></i>批量停用</a>
  </div>
    <div class="form-group">
    <label class="sr-only"></label>
	  <a onclick="more_delete_user()" class="btn btn-danger"><i class="glyphicon glyphicon-trash
 "></i>批量删除</a> 
  </div>
 
</form>
			
				</div>
					</div>
			
			<div class="row"> 
			<div class="col-sm-12" id="list_div">     

  		       <table class="table table-condensed ">
                                        <thead>
                                            <tr>
                                               <th><input type="checkbox" onchange="checkbox_Id_allChange()"  /></th>
                                                <th>账号</th>
                                                <th>昵称 </th>
                                                <th>书库/图书管理</th>
                                                <th>root</th>
                                                <th> 用户类型管理</th>
                                                <th>读者管理</th>
                                                <th>借阅管理</th>
												<th>状态</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                        <s:iterator value="libraryAdminDto" var="s">
                                         <tr>
                                         <td><input type="checkbox" class="checkbox_Id_list1" /></td>
                                         <td><s:property value="#s.account" /></td>
                                          <td><s:property value="#s.adminName" /></td>
                                          <td>
                                          <s:if test="#s.asrJu">
                                         <i class="glyphicon glyphicon-ok"></i>
                                          </s:if>
                                          <s:else>
                                          <i class="glyphicon glyphicon-remove"></i>
                                          </s:else>
                                          </td>
                                          
                                           <td>
                                          <s:if test="#s.aisRoot">
												<i class="glyphicon glyphicon-ok"></i>
                                          </s:if>
                                          <s:else>
                                           <i class="glyphicon glyphicon-remove"></i>
                                          </s:else>
                                          </td>
                                          
                                         
                                          <td>
                                          <s:if test="#s.readTypeJu">
                                         <i class="glyphicon glyphicon-ok"></i>
                                          </s:if>
                                          <s:else>
                                           <i class="glyphicon glyphicon-remove"></i>
                                          </s:else>
                                          </td>
                                          
                                          <td>
                                          <s:if test="#s.readerJu">
                                         <i class="glyphicon glyphicon-ok"></i>
                                          </s:if>
                                          <s:else>
                                           <i class="glyphicon glyphicon-remove"></i>
                                          </s:else>
                                          </td>
                                          
                                          <td>
                                          <s:if test="#s.libJu">
                                        <i class="glyphicon glyphicon-ok"></i>
                                          </s:if>
                                          <s:else>
                                         <i class="glyphicon glyphicon-remove"></i>
                                          </s:else>
                                          </td>
                                          
                                          <td>
                                          <s:if test="#s.aisStatus">
                                        	<font color="#428BCA"  >正常</font>
                                          </s:if>
                                          <s:else>
                                        <font color="#D9534F">停用</font>
                                          </s:else>
                                          </td>
                                          
                                        <td class="actions">
                                        
                                                           <a href="<c:url value='/admin/manager_control/jump_admin_info_setting.action' />?account=<s:property value='#s.account' />">
                                                        <button class="btn btn-sm btn-primary">

                                                           详情
                                                        </button>
                                                    </a>
                                         <s:if test="#s.aisStatus">
                                         <a onclick="modify_is_status(false,<s:property value='#s.account' />,<s:property value='#s.aisRoot' />,this)">
                                          
                                                        <button class="btn btn-sm btn-danger">

                                                      停用
                                                        </button>
                                                    </a>
                                                     </s:if>
                                                      <s:else>
                                         <a onclick="modify_is_status(true,<s:property value='#s.account' />,<s:property value='#s.aisRoot' />,this)">
                                                        <button class="btn btn-sm btn-primary">

                                                           激活
                                                        </button>
                                                    </a>
                                                       </s:else>
                                                     
                                         
                                                           <a onclick="delete_user(<s:property value='#s.account' />,<s:property value='#s.aisRoot' />)" >
                                                        <button class="btn btn-sm btn-danger">

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
	  

//ajax处理manager列表
function load_list(data){
	var s=eval(data);
				//清空list数据
				page=s.pageInfo.page;
               $("#list_tbody").children("tr").remove();
               	//删除分页
					$("#list_page").children("li").remove();
					
                  //本来可用json
                  if(s.libraryAdminDto==null){
                  //无数据的时候；添加blank_Div的，删除分页栏
                
//                   $("#list_div").append($("<div id='blank_div'  class='hero-unit'>  <h1>无任何数据</h1>  <p>No thing</p></div> "));
                  }
                  else{
                  //删除blank_Div的
                
//                    $("#blank_div").remove();  
                   loadPage(s.pageInfo.count);
                   var LAlist=s.libraryAdminDto
                   for(var i=0;i<LAlist.length;i++){
                	   var dto=LAlist[i];
                	   var str1=$("<tr></tr>");
                	   str1.append(  "<td><input type='checkbox' class='checkbox_Id_list1' /></td>");
                	   str1.append("<td>"+dto.account+"</td>");
                	   str1.append("<td>"+dto.adminName+"</td>");
                	   if(dto.asrJu){
                		   str1.append("<td> <i class='glyphicon glyphicon-ok'></td>");
                	   }
                	   else{
                		   str1.append("<td><i class='glyphicon glyphicon-remove'></td>");
                	   }
                	   if(dto.aisRoot){
                		   str1.append("<td> <i class='glyphicon glyphicon-ok'></td>");
                	   }
                	   else{
                		   str1.append("<td><i class='glyphicon glyphicon-remove'></td>");
                	   }
                	 
                	   if(dto.readTypeJu){
                		   str1.append("<td> <i class='glyphicon glyphicon-ok'></td>");
                	   }
                	   else{
                		   str1.append("<td><i class='glyphicon glyphicon-remove'></td>");
                	   }
                	   if(dto.readerJu){
                		   str1.append("<td> <i class='glyphicon glyphicon-ok'></td>");
                	   }
                	   else{
                		   str1.append("<td><i class='glyphicon glyphicon-remove'></td>");
                	   }
                	   if(dto.libJu){
                		   str1.append("<td> <i class='glyphicon glyphicon-ok'></td>");
                	   }
                	   else{
                		   str1.append("<td><i class='glyphicon glyphicon-remove'></td>");
                	   }
                	   if(dto.aisStatus){
                		   str1.append("<td><font color='#428BCA'  >正常</font></td>");
                	   }
                	   else{
                		   str1.append("<td>   <font color='#D9534F'>停用</font></td>");
                	   }
                	   var str2=$("<td class='actions' ></td>");
                	   str2.append("<a href='<c:url value='/admin/manager_control/jump_admin_info_setting.action' />?account="+dto.account+" ' ><button class='btn btn-sm btn-primary'>详情</button></a> ");
                	  if(dto.aisStatus){
                		  str2.append("<a onclick='modify_is_status(false,"+dto.account+","+dto.aisRoot+",this)'><button class='btn btn-sm btn-danger'>停用</button></a> ")
                	  }else{
                		  str2.append("<a onclick='modify_is_status(true,"+dto.account+","+dto.aisRoot+",this)'><button class='btn btn-sm btn-primary'>激活</button></a> ")
                	  }
                	   str2.append("<a onclick='delete_user("+dto.account+","+dto.aisRoot+")' > <button class='btn btn-sm btn-danger'>删除 </button> </a>")
                	   str1.append(str2);
            
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
                url:"<c:url value='/admin/manager_control/json_page_managerInfo.action'/>",
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
						  url:"<c:url value='/admin/manager_control/json_delete_admin.action' />",
							type:"POST",
							data:{
								users_type : true,
								account : account,
								"pageInfo.page" : page,
								is_root : is_root
							},
							async : true,
							cache : false,
							dateType : "json",
							success:function(data){
								var json =eval(data);
								alert(json.info);
								if(json.result){
									 load_list(data);
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
// 	 		alert(findCheckId());
	 		if (confirm("你确定批量删除用户？（root和自己不被批量删除[尽管选择了]）")) { 
				 $.ajax({
					  url:"<c:url value='/admin/manager_control/json_more_delete_admin.action' />",
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
								 load_list(data);
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
	 			if (confirm("你确定批量停用用户？（将勾选的全部置为禁用，root和自己不被批量激活/禁止[尽管选择了]）")) { 
	 			  $.ajax({
	 				  url:"<c:url value='/admin/manager_control/json_modify_more_admin_is_status.action' />",
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
	 							 load_list(data);
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