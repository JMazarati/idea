<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 07.11.2016
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Registration</h1>
<style type="text/css">
    span.error {
        color: red;
    }
</style>
<form:form method="POST" action="${pageContext.servletContext.contextPath}/adduser">

    <table>
        <tr>
            <td><form:label path="username">Login</form:label></td>
            <td><form:input path="username" maxlength="50"/></td>
            <td><span class="error" >
                <form:errors path="username" element="div"/>
                </span>

        </tr>
        <tr>
            <td><form:label path="useremail">Email</form:label></td>
            <td><form:input path="useremail" maxlength="50"/></td>
            <td><span class="error" ><form:errors path="useremail" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userpwd" >Password</form:label></td>
            <td><form:input path="userpwd" type="password" maxlength="64"/></td>
            <td><span class="error" ><form:errors path="userpwd" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userkpwd">Confirm password</form:label></td>
            <td><form:input path="userkpwd" type="password" maxlength="64"/></td>
            <td><span class="error" ><form:errors path="userkpwd" /></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </td>
        </tr>
    </table>

</form:form>