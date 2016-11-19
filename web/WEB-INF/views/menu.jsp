<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 05.11.2016
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<spring:url value="/myoffice" var="addContactUrl" />
<spring:url value="/viewidea" var="viewidealink" />
<spring:url value="/addidea" var="addidealink" />
<spring:url value="/reg" var="registrationlink" />
<spring:url value="/" var="homelink" />
<spring:message code="label_login" var="labellogin" />
<spring:message code="label_logout" var="labellogout" />
<spring:message code="label_welcome" var="labelwelcome" />
<spring:message code="menu_header_text" var="labelmenu" />
<spring:message code="label_link_1" var="labellink1" /><spring:message code="label_link_2" var="labellink2" /><spring:message code="label_link_3" var="labellink3" />
<spring:message code="label_username" var="labelusername" /><spring:message code="label_pwd" var="labelpwd" /><spring:message code="label_registration" var="labelregistration" />

<c:url value="/login" var="loginUrl" />
<h3>${menuHeaderText}</h3>

<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_OPERATOR', 'ROLE_ADMINISTRATOR')">
    <sec:authentication property="principal.username" var="username" />
    <a href="${addContactUrl}"><h3>${labelwelcome} ${username}</h3></a>

   <b>${labellogin}: </b> ${username}<b>--></b>
    <a href="<c:url value="/j_spring_security_logout"/>">${labellogout}</a>
    <br />
    <hr />
    <h2>${labelmenu}</h2>
    <a href=${viewidealink}>${labellink1}</a><br/>
    <a href=${addidealink}>${labellink2}</a><br/>
    <a href=${homelink}>${labellink3}</a><br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
    <a href="/">adminka</a><br/>
</sec:authorize>

<sec:authorize access="!isAnonymous()">

</sec:authorize>

<sec:authorize access="isAnonymous()">
    <div id="login">
        <form name="loginForm" action="${loginUrl}" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control" />
            <table>
                <caption align="left">${labellogin}</caption>
                <tr>
                    <td>${labelusername}</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>${labelpwd}</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input name="submit" type="submit" value="${labellogin}" /></td>
                </tr>
            </table>
        </form>
        <br />
        <a href=${registrationlink}>${labelregistration}</a>
    </div>
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <span style="color: red; ">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </span>
    </c:if>

    <hr />
    <h2>${labelmenu}</h2>
    <a href="/viewidea">${labellink1}</a><br/>
    <a href="/">${labellink3}</a>
</sec:authorize>
