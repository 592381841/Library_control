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
    <title>检索页面</title>

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
	  #search_content{
		  margin-top: 1%;
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
  		<div id="search_content" class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
      
      <form class="form-horizontal">
  <div class="form-group">
   <div class="col-sm-2">
   <div class="btn-group">
  <button class="btn btn-primary  dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    
    <font id="dropdownSelectText">书名</font> <span class="caret"></span>
    
  </button>

  <ul class="dropdown-menu">
  <li><a onClick="dropdownText(this)">书名</a><input type="hidden"  value="bookInfoDto.bookName" /></li>
	  <li><a onClick="dropdownText(this)">书码</a><input type="hidden"  value="bookInfoDto.bookId" /></li>
	  <li><a onClick="dropdownText(this)">作者名</a><input type="hidden"  value="bookInfoDto.bookWriter" /></li>
  </ul>
    <input type="hidden" id="dropdownSelectInput" value="bookInfoDto.bookName" />
</div></div>
<!--    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
    <div class="col-sm-5">
      <input type="text" autocomplete="off" class="form-control"  id="searchInput" data-provide="typeahead" data-items="8"  placeholder=" 请输入内容">
    </div>
     <div class="col-sm-5">
     <a onclick="searchBookInfo()" class="btn btn-primary btn-block pull-right">检索</a>
    </div>
  </div>
		  </form>
   
      
    
       
       </div>
       
       
       	       <div  class="row">
  		       <table class="table bootstrap-admin-table-with-actions">
                                        <thead>
                                            <tr>
                                               <th>书名</th>
                                                <th>唯一标识码</th>
                                                 <th>书码</th>
                                                <th>作者</th>
                                                <th>类型</th>
                                                <th>出版社</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                         
                                          
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
  		
  

<script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    
    	  <script src="<c:url value='/lib/js/bootstrap-typeahead.js' />">
		  
	  	
	  
	  
	  </script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script type="text/javascript">
		
    
    function load_list(data){
    	var jsonText=eval(data);
    				//清空list数据
    				page=jsonText.pageInfo.page;
                   $("#list_tbody").children("tr").remove();
                   	//删除分页
    					$("#list_page").children("li").remove();
    					
                      //本来可用json
                      if(jsonText.bookInfoDtos==null){
                      }
                      else{

                       loadPage(jsonText.pageInfo.count);
                       var LAlist=jsonText.bookInfoDtos
                       for(var i=0;i<LAlist.length;i++){
                    	   var dto=LAlist[i];
                    	   var str1=$("<tr></tr>");
    						str1.append("<td><a  href='<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=false&bookInfoDto.bookId="+dto.bookId+"'>"+dto.bookName+" </a></td><td>"+dto.id+" </td><td>"+dto.bookId+" </td><td>"+dto.bookWriter+" </td><td>"+dto.bookTypeDto.bookTypeName+" </td><td>"+dto.bookPress+" </td>")
                    	  
    						 var str2= $("<td class='actions'></td>");  
       	            	 str2.append("<a onclick='delete_book("+dto.id+" )'><button class='btn btn-sm btn-danger'><i class='glyphicon glyphicon-circle-arrow-down'></i>下架</button></a> ");
       	            	if(!dto.bookUnuse){
       	            		str2.append("<a onclick='unuse_book("+dto.id+" )'><button class='btn btn-sm btn-primary'><i class='glyphicon glyphicon-floppy-disk'></i>闲置</button></a>");
       	            	}
       	            	str1.append(str2);
                		
                    	   $("#list_tbody").append(str1);
                       }
                       
                      
                      }
    }

    
    
    
		//下拉js
	  function dropdownText(as){
		  var oldtext=as.text;
		  var selectt=$("#dropdownSelectText");
// 		  alert($(as).next().val());
		  $("#dropdownSelectInput").val($(as).next().val());
		 
		  selectt.text(oldtext);
	  }
	  
		//联想语法
		    
    
   $('#searchInput').typeahead({
	   
    source : function (query, process) {
    	var queryName =$("#dropdownSelectInput").val();
    	  var parameter =null;
    	if(queryName=="bookInfoDto.bookName"){
    		parameter={ "bookInfoDto.bookName" :query};
    	}
    	else if(queryName=="bookInfoDto.bookId"){
    		parameter={ "bookInfoDto.bookId" :query};
    	}
    	else if(queryName=="bookInfoDto.Id"){
    		parameter={ "bookInfoDto.Id" :query};
    	}
    	else if(queryName=="bookInfoDto.bookWriter"){
    		parameter={ "bookInfoDto.bookWriter" :query};
    	}
       
       if(parameter==null)return;
        $.ajax({
            cache: false,
            type: "POST",
            data:parameter, 
            url:"<c:url value='/admin/book_control/json_findKeyBookInfo.action'/>",
            async:true,
            success:function (data){
         	   var jso=eval(data);
         	  
         	  
         		   
                        	 process(jso.resultList);
                     
         	   
           
            },error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
        });

       
    }
});
	  

	//分页
		  
//当页面加载时，默认为1（在JavaScript的最开始）
var page=1;



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
                type: "GET",
                url:"<c:url value='/admin/book_control/json_search_page_keyboardBookInfo.action'/>?pageInfo.page="+page,
                async:true,
                success:function (data){
                	  load_list(data);
                }
            });
};


function searchBookInfo(){
	var querys=$("#searchInput").val();
	var queryNames =$("#dropdownSelectInput").val();
	  var parameters =null;
	if(queryNames=="bookInfoDto.bookName"){
		parameters={ "bookInfoDto.bookName" :querys};
	}
	else if(queryNames=="bookInfoDto.bookId"){
		parameters={ "bookInfoDto.bookId" :querys};
	}
	else if(queryNames=="bookInfoDto.Id"){
		parameters={ "bookInfoDto.Id" :querys};
	}
	else if(queryNames=="bookInfoDto.bookWriter"){
		parameters={ "bookInfoDto.bookWriter" :querys};
	}
 
 if(parameters==null)return;
	$.ajax({
        cache: false,
        type: "POST",
        data:parameters, 
        url:"<c:url value='/admin/book_control/json_search_page_keyboardBookInfo.action'/>",
        async:true,
        success:function (data){
     	  
     	  load_list(data);
     	
        },error: function(){
				window.top.location='<c:url value='/error.jsp'/>';
			}
    });
	
	
}


//下架书籍
function delete_book(s){

	 $.ajax({
               cache: false,
               type: "POST",
               data:{
            	  "pageInfo.page" :page,
            	  "bookInfoDto.id" :s,
            	  "bookrackDto.bookrackId":0
               }, 
               url:"<c:url value='/admin/book_control/json_deleteBookInfo.action'/>",
               async:true,
               success:function (data){
            	   var jso=eval(data);
            	   alert(jso.info);
            	   if(jso.result){
            		   $.ajax({
                           cache: false,
                           type: "GET",
                           url:"<c:url value='/admin/book_control/json_search_page_keyboardBookInfo.action'/>?pageInfo.page="+page,
                           async:true,
                           success:function (data){
                           	load_list(data);
                           }
                       });
            	   }
              
               },error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
           });
}	  
	  
	  
//闲置书籍
function unuse_book(s){

	 $.ajax({
               cache: false,
               type: "POST",
               data:{
            	  "pageInfo.page" :page,
            	  "bookInfoDto.id" :s,
            	  "bookrackDto.bookrackId":0
               }, 
               url:"<c:url value='/admin/book_control/json_unuseBookInfo.action'/>",
               async:true,
               success:function (data){
            	   var jso=eval(data);
            	   alert(jso.info);
            	   if(jso.result){
            		   $.ajax({
                           cache: false,
                           type: "GET",
                           url:"<c:url value='/admin/book_control/json_search_page_keyboardBookInfo.action'/>?pageInfo.page="+page,
                           async:true,
                           success:function (data){
                           	load_list(data);
                           }
                       });
            	   }
               },error: function(){
					window.top.location='<c:url value='/error.jsp'/>';
				}
           });
}	  
	  

	
	  </script>
  </body>
</html>