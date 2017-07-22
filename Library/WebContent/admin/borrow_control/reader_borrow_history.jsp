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
    <title>读者借阅历史记录</title>

   
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
  	<div id="contain" class="container">
  		<div class="row">
  			
						<div class="row">      
       <div class="col-sm-6 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">读者借阅历史记录</font>
   
       </div>
        </div>
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1">   
			                                     <div class="col-xs-2">
                           <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios1" onclick="modfiyType(this)" value="0" checked>
    <font class="radioText">全部</font>
  </label>
</div>
             </div>
             <div class="col-xs-2">
              <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios2" onclick="modfiyType(this)" value="1">
  <font class="radioText"> 未还</font>
  </label>
</div>   
           
											   </div>
											                <div class="col-xs-2">
              <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios3" onclick="modfiyType(this)" value="2">
  <font class="radioText">已还</font>
  </label>
</div>   
           
											   </div>
											   
				</div>
			</div>
			<div class="row"> 
			<div class="col-sm-6 col-sm-offset-1"> 
			  <table class="table bootstrap-admin-table-with-actions">
			  <tbody>
                                            <tr>
                                                <td>读者账号：</td>
                                                <td><s:property value="readerDto.account" /></td>
                                                <td>读者姓名：</td>
                                                <td><s:property value="readerDto.usersName" /></td>
                                          
                                      
                                            </tr>
                                          
                                        </tbody>
                                    </table> 
			  </div>
			    </div>
			
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1">    
			
			 
			              <table class="table bootstrap-admin-table-with-actions">
                                        <thead>
                                            <tr>
                                                 <th>单号</th>
                                                <th>图书唯一标识码</th>
                                                <th>书码号</th>
                                                <th>书名</th>
                                                 <th>作者</th>
                                                <th>出版社</th>
                                                <th>借期</th>
                                                 <th>还期</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                           <s:iterator value="borrowInfoDtos" var="s" >
                                <tr>
                                <td><s:property value='#s.id' /></td>
                                 <td><s:property value='#s.bookInfoDto.id' /></td>
                                  <td><s:property value='#s.bookInfoDto.bookId' /></td>
                             <td><s:property value='#s.bookInfoDto.bookName' /></td>
                               <td><s:property value='#s.bookInfoDto.bookWriter' /></td>
                                                <td><s:property value='#s.bookInfoDto.bookPress' /></td>
                                                <td><s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                                 <td><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                         
                                            </tr>
                             </s:iterator>
                                          
                                        </tbody>
                                    </table>
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
  	borrowInfoDtos=jsonData.borrowInfoDtos;
  	 if(borrowInfoDtos ==null)return;
  	 for(var i=0;i<borrowInfoDtos.length;i++){
  		 
  		 
  		 
  		 var s=borrowInfoDtos[i];
  		 var tr=$("<tr></tr>");
  		 $(tr).append("<td>"+s.id+"</td><td>"+s.bookInfoDto.id+"</td><td>"+s.bookInfoDto.bookId+"</td><td>"+s.bookInfoDto.bookName+"</td><td>"+s.bookInfoDto.bookWriter+"</td><td>"+s.bookInfoDto.bookPress+"</td><td>"+s.beginTime.replace("T"," ")+"</td> <td>"+s.endTime.replace("T"," ")+"</td>");
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
                  type: "GET",
                  url:"<c:url value='/admin/borrow_control/json_reader_page_borrow_history.action'/>?pageInfo.page="+page,
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
  	  
  function modfiyType(athis){
		$.ajax({
	        cache: false,
	        type: "POST",
	        data:{
	        	"borrowStatus" : $(athis).val(),
	        },
	        url:"<c:url value='/admin/borrow_control/json_modfiy_con_borrowInfo_status.action'/>",
	        async:true,
	        success:function (data){
	        	load_list(data);
	        }
	    });  
  }
  
    </script>
  </body>
</html>