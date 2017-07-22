<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>书籍详情页</title>

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
	  
	  #contain{
		  border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
		  box-shadow: 10px 10px 5px #888888;
	  }
	</style>
  <body>
  	<div id="contain" class="container">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
      
       <font style=" text-align:left;font-size:50px;font-family:titie_1;color: #e31510 "><s:property value="bookInfoDto.bookName" /></font>
   
      <font class="pull_left"  style=" text-align:left;font-size:30px;color: #428BCA "><sub>唯一标识码-<s:property value="bookInfoDto.id" /></sub></font>
    
       
       </div>
       
 
       	<div class="row"> 

       
       	
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td><b>作者：</b></td>
                                                <td><s:property value="bookInfoDto.bookWriter" /></td>
                                                
                                                <td><b> 分类</b></td>
                                                <td><s:property value="bookInfoDto.bookTypeDto.bookTypeName" /></td>
                                            </tr>
                                         
                                            <tr>
                                                <td><b>出版社</b></td>
                                                <td><s:property value="bookInfoDto.bookPress" /></td>
                                           <td><b>书库/书架/编号</b></td>
                                                <td><s:property value="bookInfoDto.stackRoomName" />/<s:property value="bookInfoDto.bookRackName" />/<s:property value="bookInfoDto.bookrackNumber" /></td>
                                            </tr>
                                            <tr>
                                            <td><b>是否借出</b></td>
                                            <td>
                                            	<s:if test="bookInfoDto.bookIsBorrow">
                                            		借出
                                            	</s:if>
                                            	<s:else>
                                            		未借出
                                            	</s:else>
                                           
                                            
                                            
                                            </td>
                                            <td><b>书码</b></td>
                                            <td><s:property value="bookInfoDto.bookId" /></td>
                                            </tr>
                                              
                                        </tbody>
                                    </table>
                              
                                
                                

                           
       	</div>
     	       		
     	       		       	<div class="row">
       		      	     		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">简介</h3>
  </div>
  <div class="panel-body" style="font-size: 10px">
  <s:property value="bookInfoDto.bookIntroduce" />

</div>
</div>
</div>
     	       		
     	       		
      	       		<div class="row"> 
       	     
       		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">借期详情</h3>
  </div>
  <div class="panel-body">

							<table class="table table-bordered">

								<thead>
									<tr>
										<th>状态</th>
										<th>借期</th>
										<th>还期</th>

									</tr>

								</thead>
								<tbody>
									<s:if test="bookInfoDto.bookIsBorrow">
										<tr>
											<td><s:if test="borrowInfo.borrowStatus ==1">未还</s:if> <s:elseif
													test="borrowInfo.borrowStatus ==3">超期</s:elseif></td>
											<td><fmt:formatDate value="${ borrowInfo.beginTime}"/></td>
											<td><fmt:formatDate value="${ borrowInfo.endTime}"/></td>

										</tr>
									</s:if>
									<s:else>
									<tr>
											<td>未借出</td>
											<td>无</td>
											<td>无</td>

										</tr>
									
									
									</s:else>
								</tbody>
							</table>


						</div>
</div>
      	

       	
       	
       	
       	</div>
       	

       		
       		
       	</div>
  			
  		</div>
  			
  			
  		</div>
  		
  	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value='/lib/js/jquery-1.8.1.min.js' />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/lib/js/bootstrap.min.js' />"></script>
  </body>
</html>