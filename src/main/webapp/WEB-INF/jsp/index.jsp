<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>


<html>
	<head>
		<title>Mail Services</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<style>
			.title-container{
				background-color: #f8f9fa65!important;
				height: 130px;
				
			}
			.title{
				
			    text-align: center;
				border: 5px solid #f8f9fa!important;
				background-color:  #f8f9faf5!important;
				border-radius: 3px;
			}
			.main{
				display: flex;
				width: 100vw;
			}
			.main-col-1{
				display: inline-block;
				width: 40%;
			}
			.main-col-2{
				display: inline-block;
				width: 60%;
				margin-left: 5vw;
				
			}
			.inputBox{
				width: 25vw;
				height: 30px;
				border-radius: 5px;
			}
			.btn-login{
				background-color: transparent;
				border:  solid green;
				border-radius: 8px;
				height: 35px;
				
			}
			.btn-login:hover{
				background-color: green;
				color: white;
			}
			.btn-signin{
				background-color: transparent;
				border:blue;
				height: 35px;
			}
			.btn-signin:hover{
				background-color: blue;
				color: white;
			}
		</style>
	</head>
	<body>
		
		
		
		
	 
		
		
		<div class="title-container">
			<br/>
			<div class="title">
				<h1><a href="#">Mail Casting Service</a></h1>							
			</div>
		</div>

		<br/>

		<div class="main">
			
		
					<div class="main-col-1">
							<h2>Welcome to Mailservices!</h2>
							<p>Here we are providing a free <b>Mail Service</b> for all the employees<br>
							you can now just login into your account and can send data to other<br> 
							employees for free! Let's Get Started!
						</p>
							
						</div>
						<div class="main-col-2">
						<span style="color:red">${serverMessage}</span>
										<h3 style="color:#007897;"> Login or Signup</h3><br>
										<div>
											
											
				<form:form action="login" method="post" modelAttribute="user">
			
						<table style="table-layout: fixed">

									<tr>
										<td>Email id :</td>
									</tr>
										<tr>
										<td> <form:input class="inputBox"  path="email"/><br></td>
										</tr>
			
									<tr><td>Password:</td></tr>
									<tr><td> <form:password class="inputBox" path="password"/><br></td></tr>
				
									<tr><td><br><input class="btn-login" type="submit" value="Sign in">&nbsp; &nbsp; &nbsp;  &nbsp;  &nbsp; 
										 <a href="register-page" class="btn-signin">Don't have an account?</a> </td></tr>
						</table>
				</form:form>

						</div>
						
						</div>
				</div>
		
			</body>

	

</html>