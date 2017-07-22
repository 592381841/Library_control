<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>当前图书借阅情况</title>

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
	</style>
  <body>
  	<div class="container-fluid">
  		<div class="row">
  			    <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="text-muted bootstrap-admin-box-title" style="font-family:titie_1 ">当前图书借阅情况</div>
                                </div>
                                <div class="bootstrap-admin-panel-content">
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
                                                 <th >操作</th>
                                                
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                             <s:iterator value="borrowInfoDtos" var="s" >
                                <tr>
                                <td><s:property value='#s.id' /></td>
                                 <td><s:property value='#s.bookInfoDto.id' /></td>
                                  <td><s:property value='#s.bookInfoDto.bookId' /></td>
                             <td><a  target="_black" href="<c:url value='/users/jump_book_info.action?bookInfoDto.id='/><s:property value='#s.bookInfoDto.id' />"><s:property value='#s.bookInfoDto.bookName' /> </a></td>
                               <td><s:property value='#s.bookInfoDto.bookWriter' /></td>
                                                <td><s:property value='#s.bookInfoDto.bookPress' /></td>
                                                <td><s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                                 <td><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                                 <s:if test="#s.boorowIsRenew">
                                                  <td></td>
                                                 </s:if>
                                                 <s:else>
                                                 
                                                  <td class="actions">
                                                    <input type="hidden" value="<s:property value='#s.id' />" />
                                                    <a onclick="renew(this)">
                                                        <button class="btn btn-sm btn-primary">
                                                            <i class="glyphicon glyphicon-time
"></i>
                                                           续借
                                                        </button>
                                                    </a>
                                                   
                                                
                                                </td>
                                                 
                                                 
                                                 
                                                 </s:else>
                                            </tr>
                             </s:iterator>
                                                 
                                               
                                              
                                            
                          
                                          
                                        </tbody>
                                    </table>
                                </div>
                            </div>
  			
  		</div>
  		
  	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
    <script type="text/javascript">
		function checkbox_Id_allChange(){
	var list=$(".checkbox_Id_list");

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

function renew(athis){

$.ajax({
    cache: false,
    type: "POST",
    data:{
    	"borrowInfo.id" : $(athis).prev().val()
    },
    url:"<c:url value='/users/json_renewBook.action'/>",
    async:true,
    success:function (data){
    	var jsontext=eval(data);
    	alert(jsontext.info);
    	if(jsontext.result){
    		location.reload();
    		
    	}    	
    }
});



}
	  </script>
  </body>
</html>