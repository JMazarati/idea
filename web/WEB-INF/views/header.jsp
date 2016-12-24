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
<spring:url value="/myoffice" var="addContactUrl"/>
<spring:url value="/viewidea" var="viewidealink"/>
<spring:url value="/addidea" var="addidealink"/>
<spring:url value="/reg" var="registrationlink"/>
<spring:url value="/" var="homelink"/>
<spring:message code="label_login" var="labellogin"/>
<spring:message code="label_logout" var="labellogout"/>
<spring:message code="label_remember_me" var="label_remember_me"/>
<spring:message code="label_welcome" var="labelwelcome"/>
<spring:message code="menu_header_text" var="labelmenu"/>
<spring:message code="label_link_1" var="label_link_1"/>
<spring:message code="label_link_2" var="label_link_2"/>
<spring:message code="label_link_3" var="label_link_3"/>
<spring:message code="label_link_4" var="label_link_4"/>
<spring:message code="label_link_5" var="label_link_5"/>
<spring:message code="label_search" var="label_search"/>
<spring:message code="label_username" var="labelusername"/>
<spring:message code="label_pwd" var="labelpwd"/>
<spring:message code="label_registration" var="labelregistration"/>
<spring:message code="label_restore" var="labelrestore"/>
<spring:message code="message_fail_login" var="mfl"/>
<spring:message code="label_en" var="labelEn" />
<spring:message code="label_ru" var="labelRu" />
<c:url value="/login" var="loginUrl"/>

<div class="container">
<div class="row">
    <div class="col-xs-2">
        <a href="/index"><img id="logo" src="/resources/pict/logo.jpg" width="100"  height="130" alt="logo"></a>
    </div>
    <div class="col-xs-10">
        <div class="row">
            <div class="col-xs-6">
                <div class="text-center" id="containerHeader">J.A.V.A.I.T. ideas</div>
            </div>

            <div id="language" class="col-xs-2 text-center">
                <c:set var="prop_tag" value="<%=(request.getParameter("tag")) %>"/>
                <c:set var="prop_cat" value="<%=(request.getParameter("cat")) %>"/>
                <c:set var="prop_lang" value="<%=(request.getParameter("lang")) %>"/>

                <c:if test="${prop_tag eq null && prop_cat eq null}">
                    <a href="${currentUrl}?lang=en"><img src="/resources/pict/uk.png" alt=""/>${labelEn}</a>
                    <a href="${currentUrl}?lang=ru"><img src="/resources/pict/ru.png" alt=""/>${labelRu}</a>
                </c:if>
                <c:if test="${prop_tag ne null || prop_cat ne null}">

                    <c:set var="rquery" value="${pageContext.request.queryString}"/>
                    <c:if test="${prop_lang ne null}">
                        <c:set var="rquery" value='${rquery.replaceAll("&lang=en",null)}'/>
                        <c:set var="rquery" value='${rquery.replaceAll("&lang=ru",null)}'/>
                    </c:if>
                    <a href="?${rquery}&lang=en"><img src="/resources/pict/uk.png" alt=""/>${labelEn}</a>
                    <a href="?${rquery}&lang=ru"><img src="/resources/pict/ru.png" alt=""/>${labelRu}</a>
                </c:if>
            </div>

            <div class="col-xs-4">
                <%-- Login --%>
                <div class="pull-right" id="login1">
                    <sec:authorize access="isAnonymous()">
                        <button class="btn btn-default log-button" onclick="document.getElementById('form').style.display='block'" style="width:auto;">Login</button>
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
                                            <td><input type="checkbox" checked="checked">${label_remember_me}</td>
                                        </tr>
                                        <tr>
                                            <td><input name="submit" type="submit" class="btn btn-default" id="submitBtn" value="${labellogin}"/></td>
                                            <td><a href=${registrationlink}>${labelregistration}</a>&nbsp;<a href="/restore">${labelrestore}</a> </td>
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
                    ${mfl} &nbsp;<a href="/restore">${labelrestore}</a><br/><br/>
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                </span>
                        </c:if>
                    </sec:authorize>

                    <sec:authorize access="!isAnonymous()">
                        <sec:authentication property="principal.username" var="username"/>
                        <a id="welcome" href="/myoffice"><span>${labelwelcome}</span> <span class="user_name">${username}</span></a>
                        <a id="logout" href="<c:url value="/j_spring_security_logout"/>">${labellogout}</a>
                    </sec:authorize>
                    ${lmnl}
                </div>
            </div>
        </div>

        <%-- Menu --%>
        <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_OPERATOR')">
            <nav class="nav nav-bar" id="menu" role="navigation">
                <ul class="nav nav-tabs conmenu-ul">
                    <li role="presentation" id="tab1" class="${tabclass1}"><a href="/index">${label_link_3}</a></li>
                    <li role="presentation" id="tab2" class="${tabclass2}"><a href="/viewidea">${label_link_1}</a></li>
                    <li role="presentation" id="tab3" class="${tabclass3}"><a href="/addidea">${label_link_2}</a></li>
                    <li role="presentation" id="tab4" class="${tabclass4}"><a href="/myoffice">${label_link_4}</a></li>
                    <li class="pull-right">
                        <div class="form-group" id="search">
                            <input type="text" class="form-control" placeholder="${label_search}">
                        </div>
                    </li>

                </ul>
            </nav>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
            <nav class="nav nav-bar" id="menu" role="navigation">
                <ul class="nav nav-tabs conmenu-ul admin-tabs">
                    <li role="presentation" id="tab1Admin" class="${tabclass1}"><a href="/index">${label_link_3}</a></li>
                    <li role="presentation" id="tab2Admin" class="${tabclass2}"><a href="/viewidea">${label_link_1}</a></li>
                    <li role="presentation" id="tab3Admin" class="${tabclass3}"><a href="/addidea">${label_link_2}</a></li>
                    <li role="presentation" id="tab4Admin" class="${tabclass4}"><a href="/myoffice">${label_link_4}</a></li>
                    <li role="presentation" id="tab5Admin" class="${tabclass5}"><a href="/admin">${label_link_5}</a></li>
                    <li class="pull-right">
                        <div class="form-group" id="searchAdmin">
                            <input type="text" class="form-control" placeholder="${label_search}">
                        </div>
                    </li>
                </ul>
            </nav>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">
            <nav class="nav nav-bar" id="menu" role="navigation">
                <ul class="nav nav-tabs conmenu-ul">
                    <li role="presentation" id="tab1Anon" class="${tabclass1}"><a href="/index">${label_link_3}</a></li>
                    <li role="presentation" id="tab2Anon" class="${tabclass2}"><a href="/viewidea">${label_link_1}</a></li>
                    <li class="pull-right">
                        <div class="form-group" id="searchAnon">
                            <input type="text" class="form-control" placeholder="${label_search}">
                        </div>
                    </li>
                </ul>
            </nav>
        </sec:authorize>
    </div>
</div>
</div>