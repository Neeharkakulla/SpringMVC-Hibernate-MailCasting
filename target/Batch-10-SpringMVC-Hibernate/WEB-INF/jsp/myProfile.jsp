
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp"></jsp:include>

<style>
.main{
		width: 100vw;
		display: flex;
	}
	.main-col-1{
		display: inline-block;
		width: 30%;
		
		height: 100vh;
	}
	.main-col-2{
		width: 70%;
		display: inline-block;
		height: 100vh;
		
	}
	.main-col-2 h5{
	margin-left:12%;
	}
	
</style>
<div class="main">
	<%
				if(session.getAttribute("usermail")==null)
					response.sendRedirect("index");
			%>
	<div class="main-col-1"></div>	
	<div class="main-col-2">
	<br><br><br>						
				<span style="color:green">${newPassword }</span>
				<table border=4 cellspacing='4' cellpadding='5'>
				
				
				
				<tr>
				<td>Name</td>
				<td>${user.name}</td>
				</tr>
				
				<tr>
				<td>Email</td>
				<td>${user.email}</td>
				</tr>
				<tr>
				<td>Password</td>
				<c:if test="${success.equals('success')}">
				<td><span style='color: green;'>Success &#10003;</span></td>
				</c:if>
				<c:if test="${success.equals('Invalid')}">
				<td> 
				<form action='validate' method='post'>
				<input type='hidden' name='id' value="${user.id }" readonly>
				<input type='password' name='password'>&nbsp;&nbsp;
				<button >Validate</button>
				</form>
				<span style='color: red;'>Invalid</span></td>
				</c:if>
				<c:if test="${success.equals('')}">
				<td> 
				<form action='validate' method='post'>
				<input type='hidden' name='id' value="${user.id }" readonly>
				<input type='password' name='password'>&nbsp;&nbsp;
				<button >Validate</button>
				</form>
				</td>
				</c:if>
				</tr>
				<c:if test="${success.equals('success')}">
				<tr>
				<td>New Password</td>
				<td>
				 <form action='newPasswordRequest' method='post'>
					<input type='hidden' name='id' value="${user.id }" readonly>
					<input type='password' name='password'>&nbsp;&nbsp;
					<button >Change</button>
				</form>
				</td>
				</tr>
				</c:if>
				
				<tr>
				<td>Contact</td>
				<td>${user.contact}</td>
				</tr>
				
				<tr>
				<td>Country</td>
				<td>${user.country}</td>
				</tr>
				
				</table>
	
</div>	
</div>