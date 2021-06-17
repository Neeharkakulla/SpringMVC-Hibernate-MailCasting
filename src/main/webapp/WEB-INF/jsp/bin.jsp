
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
		width: 40%;
		border:1px solid;
		height: 100vh;
	}
	.main-col-2{
		width: 60%;
		display: inline-block;
		
		border: 1px solid;
		height: 100vh;
		
	}
	
</style>
			<%
				if(session.getAttribute("usermail")==null)
					response.sendRedirect("index");
			%>
<div class="main">
			<div class="main-col-1">
				
					<div>
							<h2>Welcome to Mailservices!</h2>		
					</div>
					
							<div>
						
<h6>Welcome ${usermail}</h6>
								<c:if test="${mails.size()<=0}">
								<h5>Bin is empty</h5>
								</c:if>		
					<c:if test="${mails.size()>0}">
					<h6>Bin Box</h6>
					<table border=4 cellspacing='4' cellpadding='5'>
		
						<tr>
						<td>Reciever&nbsp;	&nbsp;</td>
						<td>SUBJECT</td><th>MESSAGE	&nbsp; 	&nbsp;</td>
						<td>DATE OF RECIEVING 	&nbsp;	&nbsp;</td>
						<td>Delete</td>
						<td>Retrive</td>
						</tr>
			<c:forEach var="m" items="${mails}">	
		
		
		<tr onclick='viewBinMail(${m.id})'>

		<c:if test="${m.reciever.length()>=5}">
			<td> ${ m.reciever.substring(0,5)}....</td>
			</c:if>
		<c:if test="${m.reciever.length()<5}">
			<td>${ m.reciever} </td>
		</c:if>
		<c:if test="${m.subject.length()>=5}">
			<td> ${ m.subject.substring(0,5)}....</td>
			</c:if>
		<c:if test="${m.subject.length()<5}">
			<td> ${ m.subject}....</td>
			</c:if>
		<c:if test="${m.message.length()>=5}">
			<td> ${ m.message.substring(0,5)}....</td>
			</c:if>
		<c:if test="${m.message.length()<5}">
			<td> ${ m.subject}....</td>
			</c:if>
			
		<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${m.date}" />  </td>
		
		<td><a href='deleteBinboxMail?id=${m.id}'> Delete</a></td>
		<td><a href='retriveMail?id=${m.id}'>retrive</a></td>
		
	</tr>
								
								</c:forEach>
								
								</table>
								</c:if>
							
						</div>
		</div>
			<div  class="main-col-2">
				<table>
					<tr>
						<td style="width: 25%;"></td>
						<td style="width: 65%;">
							<div id="viewBinBoxMail">
			
							</div>
						</td>
						<td style="width: 10%;"></td>
					</tr>
					
				</table>
				
			</div>
		
			
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

		function viewBinMail(id)
		{
			
			$.ajax({url: "getBinMail?id="+id, success: function(result){
				
				document.getElementById("viewBinBoxMail").innerHTML=result
    	}
	});
}




</script>				

			
	
