<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html class="bootstrap-admin-vertical-centered">
    <head>
        <title>${li_name }</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">    
        <!-- Bootstrap -->
        <link rel="stylesheet" media="screen" href="<c:url value='lib/css/bootstrap.min.css' />">
        <link rel="stylesheet" media="screen" href="<c:url value='lib/css/bootstrap-theme.min.css' />">

        <!-- Bootstrap Admin Theme -->
        <link rel="stylesheet" media="screen" href="<c:url value='lib/css/bootstrap-admin-theme.css' />">

        <!-- Custom styles -->
        <style type="text/css">
			
			@font-face
{
font-family: myFirstFont;
src: url('<c:url value='lib/fonts/Ubuntu-Medium.ttf' />');/* IE9+ */
}
			
            .alert{
                margin: 0 auto 20px;
            }
/*			.frist{text-align:center;font-family:Microsoft YaHei}font-family: myFirstFont;*/
/*src: url('Sansation_Light.ttf'),*/
			.frist {text-align:center;font-family: myFirstFont;color:#004681;font-weight: 200 }

			.frist1{font-family:myFirstFont; color:#0099D3 }
			.radioText{font-family:myFirstFont;}
				
			#all{background-image: url(<c:url value='lib/images/login.jpg' />);
			background-position:center;
			}
			
			#myForm{max-width: 500px}
/*			#labelfrist{background-color: #FF8A81}*/
        </style>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
           <script type="text/javascript" src="js/html5shiv.js"></script>
           <script type="text/javascript" src="js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body id="all" class="bootstrap-admin-without-padding">
        <div class="container">
            <div class="row">
                <div  class="col-lg-12">
                  
                    <form id="myForm" method="post" action="<c:url value='/common/login.action' />" class="bootstrap-admin-login-form">
                        <h1 class="frist" id="labelfrist">${li_name }</h1>
                        <div  id="usersNameDiv" class="form-group ">
                            <label class="sr-only" for="exampleInputAmount"></label>
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon  glyphicon-user" aria-hidden="true"></span></div>
                            <input onfocus="inputStatc(this)" class="form-control" type="text" id="usersName" name="usersName" value="${usersName }" placeholder="学号/工号">
                        </div>
						</div>
                        <div   class="form-group" id="passwdDiv">

                        <label class="sr-only" for="exampleInputAmount"></label>
    <div class="input-group"> 
      <div class="input-group-addon"><span class="glyphicon  glyphicon-lock" aria-hidden="true"></span></div>
      <input onfocus="inputStatc(this)" type="password" class="form-control" id="passwd" name="passwd" placeholder="密码">
      
    </div>
                        </div>
    
                        
                        
                        
                        
                         <div  id="vertiyCodeDiv" class="form-group">
<!--                         <div class="row">-->
<!--                         	<div class="col-xs-4">-->
                         	<label class="sr-only" for="vertiy"></label>
    <div class="input-group"> 
      <div class="input-group-addon"><span class="glyphicon  glyphicon-lock" aria-hidden="true"></span></div>
   
      <input class="form-control" onfocus="inputStatc(this)" type="text" id="vertiyCode" name="vertiyCode" placeholder="输入验证码">
    </div>
                         	
                         	
                         		
								
<!--                         	</div>-->
                         
<!--                         </div>-->
   <!--验证码-->
  </div>
                       
                                           <div class="form-group ">
                                           <div class="row">
                                            <div class="col-xs-8">
                                            		<div class="col-xs-4" >
                         			<a href="javascript:_change()">
								<img id="vCode" src="<c:url value='/common/download_verifyPic.action'/>"></a>
                         	</div>
                         	<div class="col-xs-8" style="vertical-align:bottom" >
<!--							<a  class="btn btn-primary btn-sm">不清晰，换一张</a>-->
                        	<a style="text-align: right; " href="javascript:_change()">不清晰，换一张</a>
                         	</div>
                                            	
                                            	
                                            	
                                            </div>
                                             <div class="col-xs-4">
                           <div class="radio">
  <label>
    <input type="radio" name="usersType" id="option1" value="0" checked>
      <font class="radioText"> 读者</font>
  </label>
</div>
              <div class="radio">
  <label>
    <input type="radio" name="usersType" id="option2" value="1">
<font class="radioText">管理员</font>
  </label>
</div>   
           
											   </div>
                     </div>          
            
                                          
                           
                           
                        </div>
                        
                        
                        
                        
                        
                        
                        <div class="form-group " style="text-align: center">
						<font color="#EA2000" >如果忘记密码 ，请携带有效证件前往图书馆办公室找回密码</font>
                       </div>



                        <a class="btn btn-lg btn-primary btn-block" onclick="login()">登录</a>
                     <div class="form-group " style="text-align: center">
						<font id="information" color="#EA2000" ></font>
                       </div>
                    </form>
   
                </div>
            </div>
        </div>
        
        
        
        

        <script type="text/javascript" src="<c:url value='lib/js/jquery-1.8.1.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='lib/js/bootstrap.min.js' />"></script>
        <script type="text/javascript">
       
            $(function() {
                // Setting focus
                $('input[name="email"]').focus();

                // Setting width of the alert box
                var alert = $('.alert');
                var formWidth = $('.bootstrap-admin-login-form').innerWidth();
                var alertPadding = parseInt($('.alert').css('padding'));

                if (isNaN(alertPadding)) {
                    alertPadding = parseInt($(alert).css('padding-left'));
                }

                $('.alert').width(formWidth - 2 * alertPadding);
            });
            
           if( "${information}"==3){
        	   $("#information").text("验证码错误")
        		$("#vertiyCodeDiv").addClass("has-error");
           }
           else if ( "${information}"==1){
        	   $("#information").text("账号或者密码错误")
       		$("#passwdDiv").addClass("has-error");
       		$("#usersNameDiv").addClass("has-error");
           }
           else if ( "${information}"==2){
        	   $("#information").text("账号或者密码不为空");
       		$("#passwdDiv").addClass("has-error");
       		$("#usersNameDiv").addClass("has-error");
           }
           else if ( "${information}"==4){
        	   alert("账号被禁用,请联系工作人员");
        	  

           }
           else if ( "${information}"==5){
        	   alert("注销成功");

           }
           <%session.removeAttribute("information"); %>
           function inputStatc(a){
        	   $(a).parent().parent().removeClass("has-error");
           }
            function _change() {
            		/*
            		1. 获取<img>元素
            		*/
            		
            		var ele = document.getElementById("vCode");
            		ele.src = "<c:url value='/common/download_verifyPic.action'/>?xxx=" + new Date().getTime();
            		
            	}
            function login(){
            	var usersName=$('#usersName').val().trim();
            	var passwd=$('#passwd').val().trim();
            	var vertiyCode=$('#vertiyCode').val().trim();
            	var info=$('#information');
            	$(info).text("");

            	
            	if (vertiyCode == null || vertiyCode == undefined || vertiyCode == '') { 
            		info.text("验证码不为空");
            		$("#vertiyCodeDiv").addClass("has-error");
            		$('#vertiyCode').val("");
  
            		} 
            	else if(vertiyCode.length!=4){
            		info.text("验证码错误");
            		$("#vertiyCodeDiv").addClass("has-error");
            		$('#vertiyCode').val("");
            	}
       
            	else if (usersName == null || usersName == undefined || usersName == ''|| passwd == null || passwd == undefined || passwd == '') { 
            		info.text("账号密码不为空");
            		$('#passwd').val("");
            		$("#passwdDiv").addClass("has-error");
            		$("#usersNameDiv").addClass("has-error");
            		$('#usersName').val("");
            	
            		} 
            	else{
            		$("#myForm").submit();
            	}
            	
            }
            
        </script>
    </body>
</html>
