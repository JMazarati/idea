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
            <a class="logo" href="/index"><img src="/resources/pict/Lumier.jpg" width="50" height="75" alt="logo"></a>
</div>
    <div class="row col-xs-6">
        <div class="text-center" id="containerHeader">J.A.V.A.I.T. ideas</div>
    </div>

<div class="text-right row col-xs-3" id="login1">
    <sec:authorize access="isAnonymous()">

        <button class="btn btn-default" onclick="document.getElementById('form').style.display='block'" style="width:auto;">Login</button>
        <div id="form" class="modal">
        <form class="modal-content animate" name="loginForm" action="${loginUrl}" method="post">
            <span onclick="document.getElementById('form').style.display='none'" class="close" title="Close Modal">&times;</span>
            <div class="containerLogin">
            <input type="hidden" class="form-control" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table>
                <tr><caption align="right">${labellogin}</caption>
                    <td>${labelusername}</td>
                    <td><input type="text" name="username" id="login" class="form-control"/></td>
                </tr>
                <tr>
                    <td>${labelpwd}</td>
                    <td><input type="password" name="password" id="password" class="form-control"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="checkbox" checked="checked"> Remember me</td>
               </tr>
                <tr>
                    <td><input name="submit" type="submit" class="btn btn-default" id="submitBtn" value="${labellogin}"/></td>
                    <td><a href=${registrationlink}>${labelregistration}</a></td>
                </tr>
            </table>
                </div>
        </form>
        </div>

        <script>
            var modal = document.getElementById('form');

            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

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
