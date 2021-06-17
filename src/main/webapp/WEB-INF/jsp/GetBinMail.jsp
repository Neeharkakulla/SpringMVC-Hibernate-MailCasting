
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


		<br><br><br><br>
		<h3>${ mail.subject}</h3>
		<p>from : ${ mail.sender}</p>
		<p>to : ${ mail.reciever} </p>
		<p>Date : <fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${mail.date}" /></p>
		<p style='white-space: pre-line' >Message <br><br>${ mail.message}</p>
