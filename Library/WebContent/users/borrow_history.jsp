<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>借阅记录</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/lib/css/bootstrap.min.css' />" rel="stylesheet">
     <link href="<c:url value='/lib/css/datepicker.css' />" rel="stylesheet" media="screen">
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
       <div class="col-sm-6 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">借阅记录</font>
   
       </div>
        </div>
        	<div class="row"> 
			<div class="col-sm-8 col-sm-offset-1"> 
			  <table class="table bootstrap-admin-table-with-actions">
			  <tbody>
                                            <tr>
                                                <td>起始时间：</td>
                                                <td> <input class="form-control" type="text" name="" value id="begin_time_input">
       </td>
                                                <td>终止时间：</td>
                                                <td> <input  class="form-control" type="text" name="" value id="end_time_input"></td>
                                          		<td><a class="btn btn-primary" onclick="modfiyTime()">确定时间范围</a></td>
                                      
                                            </tr>
                                          
                                        </tbody>
                                    </table> 
			  </div>
			    </div>
        
        
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1">   
			                                     <div class="col-xs-2">
                           <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" onclick="modfiyType(this)" id="all" value="1"  checked>
    <font class="radioText">全部</font>
  </label>
</div>
             </div>
             <div class="col-xs-2">
              <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" onclick="modfiyType(this)" id="borrow" value="2">
  <font class="radioText"> 借书</font>
  </label>
</div>   
           
											   </div>
											                <div class="col-xs-2">
              <div class="radio pull-left">
  <label>
    <input type="radio" name="optionsRadios" onclick="modfiyType(this)" id="return" value="3">
  <font class="radioText"> 还书</font>
  </label>
</div>   
           
											   </div>
											   
				</div>
			</div>
		
			
			<div class="row"> 
			<div class="col-sm-10 col-sm-offset-1">    
			
			 
			              <table class="table bootstrap-admin-table-with-actions">
                                        <thead>
                                            <tr>
                                               <th>读者账号</th>
                                               <th>读者名</th>
                                                
                                                <th>书码号</th>
                                               <th>书名</th>
                                                
                                                 <th>作者</th>
                                                <th>出版社</th>
                                                
                                                 
                                                 <th>操作</th>
                                                <th>操作时间</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody id="list_tbody">
                                        <s:iterator value="borrowOperationDtos" var="s">
                                            <tr>
                                            
                                              <td><s:property value="#s.borrowInfoDto.readerDto.account" /></td>
                                               <td><s:property value="#s.borrowInfoDto.readerDto.usersName" /></td>
												
												  <td><s:property value='#s.borrowInfoDto.bookInfoDto.id' /></td>
												<td> <a target="black" href="<c:url value='/admin/book_control/jump_addOrModify_book.action?addOrmodify=false&bookInfoDto.bookId=' /><s:property value='#s.borrowInfoDto.bookInfoDto.bookId' />"><s:property value='#s.borrowInfoDto.bookInfoDto.bookName' /></a></td>
                                              <td><s:property value='#s.borrowInfoDto.bookInfoDto.bookWriter' /></td>
                                              <td><s:property value='#s.borrowInfoDto.bookInfoDto.bookPress' /></td>
                                               
                                              
                                                <td >
                                                <s:if test="#s.operationType">
                                                	<font color='#428BCA'>借书</font>
                                                	</s:if>
                                                	<s:else>
                                                	<font color='#D9534F'>还书</font>
                                                	</s:else>
                                                
                                                </td>
                                                <td>
                                                
                                                <s:date  name="#s.operationTime" format="yyyy-MM-dd HH:mm:ss"/>
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
    <script src="<c:url value='/lib/js/bootstrap-datepicker.js' />"></script>
    <script type="text/javascript">
	  //此处为联动状态下的
$(function(){

  //订单时间input的日历控件
      // disabling dates
      
     
  
     
  
      
    var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
 
var checkin =  $("#begin_time_input").datepicker({
  onRender: function(date) {
    return date.valueOf() > now.valueOf() ? "disabled" : "";
  },
  format: "yyyy-mm-dd"
}).on("changeDate", function(ev) {
  
  if (ev.date.valueOf() != now.valueOf()) { 
        var newDate = new Date(ev.date)
        newDate.setDate(newDate.getDate() + 1);
            checkout.setValue(newDate);
    
  
 }
 
  checkin.hide();
   $("#end_time_input")[0].focus();
}).data("datepicker");
var checkout = $("#end_time_input").datepicker({
  onRender: function(date) {
    return date.valueOf() >= checkin.date.valueOf() && date.valueOf() <= now.valueOf()? "" : "disabled" ;
  },format: "yyyy-mm-dd"
}).on("changeDate", function(ev) {
  checkout.hide();
}).data("datepicker");
checkin.setValue(now);
checkout.setValue(now);
  }); 
  
function modfiyTime(){
	$.ajax({
        cache: false,
        type: "POST",
        data:{
        	'BeginTime' : $("#begin_time_input").val(),
        	"EndTime" : $("#end_time_input").val(),
        	"pageInfo.page" : 1
        },
        url:"<c:url value='/users/json_borrow_history_by_time.action'/>",
        async:true,
        success:function (data){
        	load_list(data);
        }
    });
	
}  


function modfiyType(athis){
// 	alert($(athis).val());  
	$.ajax({
        cache: false,
        type: "POST",
        data:{
        	"operationType" : $(athis).val(),
        	"pageInfo.page" : 1
        },
        url:"<c:url value='/users/json_borrow_history_by_type.action'/>",
        async:true,
        success:function (data){
        	load_list(data);
        }
    });
	
}  



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
	 borrowOperationDtos=jsonData.borrowOperationDtos;
	 if(borrowOperationDtos ==null)return;
	 for(var i=0;i<borrowOperationDtos.length;i++){
		 
		 
		 
		 var s=borrowOperationDtos[i];
		 var tr=$("<tr></tr>");
		 $(tr).append("<td>"+s.borrowInfoDto.readerDto.account+"</td><td>"+s.borrowInfoDto.readerDto.usersName+"</td><td><a target='black' href='<c:url value='/admin/book_control/jump_addOrModify_book.action' />?addOrmodify=false&bookInfoDto.bookId="+s.borrowInfoDto.bookInfoDto.bookId+"'>"+s.borrowInfoDto.bookInfoDto.bookName+" </a></td><td>"+s.borrowInfoDto.bookInfoDto.id+" </td><td>"+s.borrowInfoDto.bookInfoDto.bookWriter+" </td><td>"+s.borrowInfoDto.bookInfoDto.bookPress+" </td>");
		if(s.operationType){
			 $(tr).append("<td><font color='#428BCA'  >借书</font></td>");
		}else{
			 $(tr).append(" <td><font color='#D9534F'>还书</font></td>");
		}

		$(tr).append("<td>"+s.operationTime.replace("T"," ")+"</td>")
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
          url:"<c:url value='/users/json_page_borrow_history.action'/>",
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

	  
	  </script>
  </body>
</html>