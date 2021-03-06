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
        <title>商城</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap -->
        <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
        <link rel="stylesheet" media="screen" href="css/bootstrap-theme.min.css">

        <!-- Bootstrap Admin Theme -->
        <link rel="stylesheet" media="screen" href="css/bootstrap-admin-theme.css">

        <!-- Custom styles -->
        <style type="text/css">
			
			@font-face
{
font-family: myFirstFont;
src: url('fonts/Ubuntu-Medium.ttf');/* IE9+ */
}
			
            .alert{
                margin: 0 auto 20px;
            }
/*			.frist{text-align:center;font-family:Microsoft YaHei}font-family: myFirstFont;*/
/*src: url('Sansation_Light.ttf'),*/
			.frist {text-align:center;font-family: myFirstFont;color:#004681;font-weight: 200 }

			.frist1{font-family:myFirstFont; color:#0099D3 }
			.radioText{font-family:myFirstFont;}
				
			#all{background-image: url(images/login.jpg);
			background-position:center;
			}
			
			#formId{max-width: 500px}
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
                  
                    <form id="formId" method="post" action="about.html" class="bootstrap-admin-login-form">
                        <h1 class="frist" id="labelfrist">商城</h1>
                        <div class="form-group has-error">
                            <label class="sr-only" for="exampleInputAmount"></label>
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon  glyphicon-user" aria-hidden="true"></span></div>
                            <input class="form-control" type="text" name="email" placeholder="学号/工号">
                        </div>
						</div>
                        <div class="form-group">

                        <label class="sr-only" for="exampleInputAmount"></label>
    <div class="input-group"> 
      <div class="input-group-addon"><span class="glyphicon  glyphicon-lock" aria-hidden="true"></span></div>
      <input type="password" class="form-control" id="exampleInputAmount" placeholder="密码">
      
    </div>
                        </div>
    
                        
                        
                        
                        
                         <div class="form-group">
<!--                         <div class="row">-->
<!--                         	<div class="col-xs-4">-->
                         	<label class="sr-only" for="vertiy"></label>
    <div class="input-group"> 
      <div class="input-group-addon"><span class="glyphicon  glyphicon-lock" aria-hidden="true"></span></div>
   
      <input class="form-control" type="text" id="vertiy" name=" vertiy" placeholder="输入验证码">
    </div>
                         	
                         	
                         		
								
<!--                         	</div>-->
                         
<!--                         </div>-->
   <!--验证码-->
  </div>
                       
                   <!--                         <div class="form-group ">
                                           <div class="row">
                                            <div class="col-xs-8">
                                            		<div class="col-xs-4" >
                         	
								<img src="images/123.png">
                         	</div>
                         	<div class="col-xs-8" style="vertical-align:bottom" >
<!--							<a  class="btn btn-primary btn-sm">不清晰，换一张</a>-->
                      <!--  	<a style="text-align: right; ">不清晰，换一张</a>
                         	</div>
                                            	
                                            	
                                            	
                                            </div>
                                             <div class="col-xs-4">
                           <div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
    <font class="radioText">管理员</font>
  </label>
</div>
              <div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
  <font class="radioText"> 读者</font>
  </label>
</div>   
           
											   </div>
                     </div>          
            
                                          
                           
                           
                        </div> -->
                        
                        
                        
                        
                        
                        
                        <!-- <div class="form-group " style="text-align: center"> -->
						<!-- <font color="#EA2000" >如果忘记密码 ，请携带有效证件前往图书馆办公室找回密码</font> -->
                       <!-- </div> -->
<!--
                       <div class="form-group " style="text-align: center">
						<font color="#EA2000" >如果忘记密码 ，请携带有效证件前往图书馆办公室找回密码</font>
                       </div>
-->
                        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
                    
                    </form>
   
                </div>
            </div>
        </div>
        
        
        
        

        <script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
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
        </script>
    </body>
</html>
