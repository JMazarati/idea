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
<h3 class="text-center page-header">Registration</h3>
<style type="text/css">
    span.error {
        color: red;
    }
</style>
<form:form method="POST" action="${pageContext.servletContext.contextPath}/adduser">

    <table class="form-horizontal">
        <tr>
            <td><form:label path="username">Login</form:label></td>
            <td><form:input path="username"  class="form-control" maxlength="50"/></td>
            <td><span class="error" >
                <form:errors path="username" element="div"/>
                </span>
            </td>
        </tr>
        <tr>
            <td><form:label path="useremail">Email</form:label></td>
            <td><form:input path="useremail"  class="form-control" maxlength="50"/></td>
            <td><span class="error" ><form:errors path="useremail" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userpwd" >Password</form:label></td>
            <td><form:input path="userpwd" type="password"  class="form-control" maxlength="64"/></td>
            <td><span class="error" ><form:errors path="userpwd" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userkpwd">Confirm password</form:label></td>
            <td><form:input path="userkpwd" type="password"  class="form-control" maxlength="64"/></td>
            <td><span class="error" ><form:errors path="userkpwd" /></span></td>
        </tr>
        <tr>
            <td colspan="2" id="button">
                <input type="submit" class="btn btn-default" value="Submit"/>
                <input type="reset" class="btn btn-default" value="Reset"/>
            </td>
        </tr>
    </table>

</form:form>