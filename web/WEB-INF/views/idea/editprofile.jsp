<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 17.12.2016
  Time: 20:24
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
<form:form method="POST" action="${pageContext.servletContext.contextPath}/editprofilepost">
    <form:hidden path="username"/>
    <form:hidden path="useremail"/>
    <table class="form-horizontal" id="registration">
            <%--
                    <tr>
                        <td><form:label path="useremail">${label_email}</form:label></td>
                        <td><form:input path="useremail"  class="form-control" maxlength="50"/></td>
                        <td><span class="error" ><form:errors path="useremail" /></span></td>
                    </tr>
            --%>
        <tr>
            <td><form:label path="userpwd">${label_pwd}</form:label></td>
            <td><form:input path="userpwd" type="password" class="form-control" maxlength="64" htmlEscape="true"/></td>
            <td><span class="error"><form:errors path="userpwd"/></span></td>
        </tr>
        <tr>
            <td><form:label path="userkpwd">${label_pwd_confirm}</form:label></td>
            <td><form:input path="userkpwd" type="password" class="form-control" maxlength="64" htmlEscape="true"/></td>
            <td><span class="error"><form:errors path="userkpwd"/></span></td>
        </tr>
        <tr>
            <td colspan="2" id="button">
                <input type="submit" class="btn btn-default" value="${label_button_submit}"/>
                <input type="reset" class="btn btn-default" value="${label_button_reset}"/>
            </td>
        </tr>
    </table>
</form:form>