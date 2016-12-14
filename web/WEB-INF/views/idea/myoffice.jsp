<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 06.11.2016
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication property="principal.username" var="username" />
<spring:message code="label_login" var="labellogin" />
<spring:message code="label_email" var="label_email" />
<spring:message code="label_contact_first_name" var="labelfirstname" />
<spring:message code="label_contact_last_name" var="labellastname" />
<spring:message code="label_contact_sex" var="label_contact_sex" />
<spring:message code="label_contact_birth_date" var="label_contact_birth_date" />
<spring:message code="label_contact_desc" var="labeldesc" />
<div id="myOffice">
    <b>${labellogin}:</b> ${username} <br/>
    <b>${label_email}:</b> <br/>
    <b>${labelfirstname}:</b>  <br/>
    <b>${labellastname}:</b>  <br/>
    <b>${label_contact_birth_date}:</b>  <br/>
    <b>${label_contact_sex}:</b>  <br/>
    <b>${labeldesc}:</b>  <br/><hr />
    ${txt}
</div>