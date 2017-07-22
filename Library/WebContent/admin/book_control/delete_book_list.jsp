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
    <title>下架书籍</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
      <style type="text/css">
			@font-face{
				font-family: titie_1;
					src:url('<c:url value='/lib/fonts/fanti_title.ttf' />')
			}
			
			@font-face{
			font-family: ubunte;
			src: url('<c:url value='/lib/fonts/Ubuntu-Bold.ttf' />')
			}
		  
		  
		   #contain{
		  border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
		  box-shadow: 10px 10px 5px #888888;
	  }
		   #type{
	
		  box-shadow: 10px 10px 5px #888888;
	  }
		
	  </style>
  </head>
  <body>
  	<div class="container-fluid" style="margin-top: 2%">
  		<div class="row">
  	
  		
  		<div  class="col-sm-12">
  	
  	
 		       <div id="contain" class="row">
 		        <div class="col-sm-4 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">下架书籍</font>
       </div>
 		       
 		         	
  		
  		<div class="col-sm-4 col-sm-offset-4">
 <div class="col-sm-5"><font  style="font-family:ubunte ;font-size: 20px">数量：${ bookSum}</font></font></div>

</div>
 		       
  		       <table class="table ">
                                        <thead>
                                            <tr>
                                                <th>书名</th>
                                                <th>唯一标识码</th>
                                                 <th>书码</th>
                                                <th>作者</th>
                                                <th>类型</th>
                                                <th>出版社</th>
                  
                                            </tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                         
                                         <c:forEach var="s" items="${bookInfoDtos }">
                                          <tr>
												<td><a  href="<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=false&bookInfoDto.bookId=${s.bookId }">${s.bookName }</a></td>
                                                <td>${s.id }</td>
                                                <td>${s.bookId }</td>
                                                <td>${s.bookWriter }</td>
                                                <td>${s.bookTypeDto.bookTypeName }</td>
                                                <td>${s.bookPress }</td>
            
                                                
                                                
                                         
                                            </tr>
                                         </c:forEach>
                                           
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
	  
//当页面加载时，默认为1（在JavaScript的最开始）
var page=1;
// function load_list(data){
	
// }

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
                  //删除blank_Div的
            
                   loadPage(jsonText.pageInfo.count);
                   var LAlist=jsonText.bookInfoDtos
                   for(var i=0;i<LAlist.length;i++){
                	   var dto=LAlist[i];
                	   var str1=$("<tr></tr>");
						str1.append("<td><a  href='<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=false&bookInfoDto.bookId="+dto.bookId+"'>"+dto.bookName+" </a></td><td>"+dto.id+" </td><td>"+dto.bookId+" </td><td>"+dto.bookWriter+" </td><td>"+dto.bookTypeDto.bookTypeName+" </td><td>"+dto.bookPress+" </td>")                	  						            		
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
                }, 
                url:"<c:url value='/admin/book_control/json_page_delete_book_list.action'/>?page="+page,
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

	  
	  
	  </script>
  </body>
</html>