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
    <title>个人信息</title>

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
  	<div id="contain" class="container-fluid">
  	
  		<div class="row">
  		<div class="col-sm-10 col-sm-offset-1">
  					<div class="row">      
       <div class="col-sm-4 col-sm-offset-4">
       <font style="text-align:left;font-size:50px;font-family:titie_1;color: #e31510 ">个人信息</font>
   
       </div>
    
       
       </div>
 
       	<div class="row"> 

       	<div class="col-sm-6">
       	
           
                                    <table class="table table-bordered">
                                        
                                        <tbody>
                                            <tr>
                                                <td>姓名</td>
                                                <td>${reader.usersName }</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <td>身份证</td>
                                                <td>${reader.idCard }</td>
                                             <td></td>
                                            </tr>
                                            <tr>
                                                <td>账号</td>
                                                <td>${reader.account }</td>
                                            <td></td>
                                            </tr>
                                               <tr>
                                                <td>用户类型</td>
                                                <td>${reader.readerTypeDto.typeName }</td>
                                               <td></td>
                                            </tr>
                                               <tr>
                                                <td>状态：</td>
                                                <td>
                                                <c:if test="${reader.risStatus }">
                                               	 正常
                                                </c:if>
                                                
                                                
                                                


                                                </td>
                                           <td class="actions">
                                        
                                                    <a  onclick="modify_status()">
                                                        <button class="btn btn-sm btn-danger">
                                                            <i class="glyphicon glyphicon-true"></i>
                                                            停用
                                                        </button>
                                                    </a>
												   </td>
                                            </tr>
                                         
                                        </tbody>
                                    </table>
                              
                                
                                

                           
       	</div>
       	       	<div class="col-sm-6">
       		<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 style="font-family: title_1" class="panel-title">照片</h3>
  </div>
  <div class="panel-body">
  
 <div class="col-xs-4 col-xs-offset-4">
   <img  data-src="holder.js/96x128" alt="96*128" style="width: 96px; height: 128px;" src="<c:url value='/people_pic/${reader.pic}' />"  onerror="this.src='<c:url value='/lib/images/default_pic.gif' />'">
	 </div>
  </div>
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
    <script type="text/javascript">
    function modify_status(){
    	  //利用对话框返回的值 （true 或者 false）  
        if (confirm("你确定停用该账号（停用后需联系工作人员后方可登录）？")) {  
           
        	$.ajax({
            	url:"<c:url value='/users/ajax_modify_is_status.action' />",
        		type:"POST",
        		async:true,
        		cache:false,
        		success:function(data){	
        		
        	
                    	window.top.location='<c:url value='/login.jsp'/>';
                  
        		}
            })
        	
        }  
        else {  
            
        }  
    	
    }
    
    </script>
  </body>
</html>