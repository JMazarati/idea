<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 18.12.2016
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<spring:message code="label_button_reset" var="label_button_reset"/>
<spring:message code="label_button_submit" var="label_button_submit"/>
<spring:message code="label_registration" var="label_registration"/>
<spring:message code="label_email" var="label_email"/>
<spring:message code="label_username" var="label_username"/>
<spring:message code="label_login" var="label_login"/>
<spring:message code="label_pwd" var="label_pwd"/>
<spring:message code="label_pwd_confirm" var="label_pwd_confirm"/>
<form:form method="POST" action="${pageContext.servletContext.contextPath}/restorepost">
    <style type="text/css">
        span.error {
            color: red;
        }
    </style>
    <table class="form-horizontal" id="registration">
        <tr>
            <td><form:label path="username">${label_username}</form:label></td>
            <td><form:input path="username"  class="form-control" maxlength="50" htmlEscape="true"/></td>
            <td><span class="error" >
                <form:errors path="username" element="div"/>
                </span>
            </td>
        </tr>
        <tr>
            <td><form:label path="useremail">${label_email}</form:label></td>
            <td><form:input path="useremail"  class="form-control" maxlength="50" htmlEscape="true"/></td>
            <td><span class="error" ><form:errors path="useremail" /></span></td>
        </tr>

        <tr>
            <td colspan="2" id="button">
                <input type="submit" class="btn btn-default" value="${label_button_submit}"/>
                <input type="reset" class="btn btn-default reset" value="${label_button_reset}"/>
            </td>
        </tr>
    </table>

</form:form>