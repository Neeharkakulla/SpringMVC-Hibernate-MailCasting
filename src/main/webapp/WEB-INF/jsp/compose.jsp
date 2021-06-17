<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp"></jsp:include>
		<style>
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
			
			.btn-send{
				background-color: transparent;
				border:  solid green;
				border-radius: 8px;
				height: 35px;
				
			}
			.btn-send:hover{
				background-color: green;
				color: white;
			}
			.cancel{
				background-color: transparent;
				color: red;
			}
			.cancel:hover{
				background-color: red;
				color: white;
			}
			.inputBox{
				width: 43vw;
				height: 40px;
				border: 1px solid;
				border-radius: 5px;
			}
		</style>
			<%
				if(session.getAttribute("usermail")==null)
					response.sendRedirect("index");
			%>
<div class="main">
	<div class="main-col-1">
		<h2>Welcome to Mailservices!</h2>
							<p>Here we are providing a free <b>Mail Service</b> for all the employees<br>
							you can now just login into your account and can send data to other<br> 
							employees for free! Let's Get Started!
						</p>
	</div>
	<div class="main-col-2">
		<div>
		</div>
		<h4>Compose Message</h4> 
		<form:form action="composeEmail" method="post" modelAttribute="mail" >
			<table> 
		 
		 <tr><td>From&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;${mail.sender} <form:hidden path="sender" /><br/></td></tr>
		 
	 <tr><td>To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <form:input path="reciever"/><br/></td></tr>
	 <tr><td>Subject:<br/></td></tr>
	 <tr><td><form:input path="subject" class="inputBox"/></td></tr>
	 <tr><td>Message:<br/></td></tr>
	 
	 <tr><td><form:textarea rows="10" cols="80" path="message"/><br/></td></tr>
	 <tr><td><input class="btn-send"type="submit" value="Send"/>&nbsp;&nbsp;&nbsp;<a class="cancel" href="home">cancel</a></td></tr>
	 
	 </table>
	 
	 </form:form>
						
										
	</div>
</div>