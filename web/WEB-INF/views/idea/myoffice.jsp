<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 06.11.2016
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="label_login" var="labellogin" />
<spring:message code="label_email" var="label_email" />
<spring:message code="label_contact_first_name" var="labelfirstname" />
<spring:message code="label_contact_last_name" var="labellastname" />
<spring:message code="label_contact_sex" var="label_contact_sex" />
<spring:message code="label_contact_birth_date" var="label_contact_birth_date" />
<spring:message code="label_contact_desc" var="labeldesc" />
<spring:message code="label_deleteuser" var="labeldeleteuser"/>
<spring:message code="label_updateuser" var="labelupdateuser"/>
<div id="delete_update">

    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="username"/>
            <a href="/editprofile"><span id="glyphicons" class="glyphicon glyphicon-edit"
                                                  aria-hidden="true"/>${labelupdateuser}</a><br/>
            <a href="/" onclick="return confirmDelete();"><span id="glyphicons" class="glyphicon glyphicon-trash"
                                                                                         aria-hidden="true"/>${labeldeleteuser}
            </a>
    </sec:authorize>
</div>
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
