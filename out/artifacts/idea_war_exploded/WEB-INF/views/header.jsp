<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 05.11.2016
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label_login" var="labellogin"/>
<spring:message code="label_logout" var="labellogout"/>
<spring:message code="label_welcome" var="labelwelcome"/>
<spring:url value="/reg" var="registrationlink"/>
<spring:message code="label_username" var="labelusername"/>
<spring:message code="label_pwd" var="labelpwd"/>
<spring:message code="label_registration" var="labelregistration"/>
<c:url value="/login" var="loginUrl"/>


<nav>
<div class="container">

<div class="row col-xs-3">
    <div id="containerHeader">
            <a class="logo" href="/">Logo</a>
          </div>
</div>

<div id="carousel-example-generic" class="carousel slide row col-xs-6" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="..." width="150px" height="150px" alt="...">
            <div class="carousel-caption">

            </div>
        </div>
        <div class="item">
            <img src="..." alt="...">
            <div class="carousel-caption">

            </div>
        </div>
        ...
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="icon-prev" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="icon-next" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>



<div class="pull-right row col-xs-3" id="login1">
    <sec:authorize access="isAnonymous()">
        <form name="loginForm" action="${loginUrl}" method="post">

            <input type="hidden" class="form-control" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table>
                <caption align="right">${labellogin}</caption>
                <tr>
                    <td>${labelusername}</td>
                    <td><input type="text" name="username" id="login" class="form-control"/></td>
                </tr>
                <tr>
                    <td>${labelpwd}</td>
                    <td><input type="password" name="password" id="password" class="form-control"/></td>
                </tr>
                <tr>
                    <td><input name="submit" type="submit" class="btn btn-default" id="submitBtn" value="${labellogin}"/></td>
                    <td><a href=${registrationlink}>${labelregistration}</a></td>
                </tr>
            </table>
        </form>

        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <span style="color: red; ">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </span>
        </c:if>
    </sec:authorize>
    <sec:authorize access="!isAnonymous()">
        <sec:authentication property="principal.username" var="username"/>
        <table>
            <tr>
                <td align="right"><a href="${addContactUrl}"><h3>${labelwelcome} ${username}</h3></a></td>
            </tr>
            <tr>
                <td align="right"><b>${labellogin}: </b> ${username}<b>--></b><a
                        href="<c:url value="/j_spring_security_logout"/>">${labellogout}</a></td>
            </tr>
        </table>
    </sec:authorize>
</div>
    </div>
</nav>
        ${lmnl}
