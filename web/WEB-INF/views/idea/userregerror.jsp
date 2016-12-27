<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 10.11.2016
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="error_0" var="e0" />
<spring:message code="error_1" var="e1" />
<spring:message code="error_2" var="e2" />
<spring:message code="error_3" var="e3" />
<spring:message code="error_4" var="e4" />
<spring:message code="error_5" var="e5" />
<spring:message code="error_6" var="e6" />
<spring:message code="error_7" var="e7" />
<spring:message code="error_8" var="e8" />
<spring:message code="label_link_6" var="label_link_6" />
<c:if test="${error==0}" >
    <b>${e0}</b>
    <a href="/">${e0}</a>
</c:if>
<c:if test="${error==1}" >
    <b>ИМЯ ЗАНЯТО</b>
    <a href="/reg">Попробовать еще раз</a>
</c:if>
<c:if test="${error==2}" >
    <b>${e2}</b>
    <a href="/addidea">${label_link_6}</a>
</c:if>

<c:if test="${error==3}" >
    <iframe src="//coub.com/embed/4jfpl?muted=false&autostart=true&originalSize=false&startWithHD=false" allowfullscreen="true" frameborder="0" width="498" height="314"></iframe><br />
    ${e3}<br>
    ${message}

</c:if>
<c:if test="${error==4}" >
   <p class="nothing"> ${e4} </p>

</c:if>
<c:if test="${error==5}" >
    <p class="nothing"><span id="glyphicons" class="glyphicon glyphicon-trash" aria-hidden="true"/> Идея удалена!</p>

</c:if>
<c:if test="${error==6}" >
    ${e6}

</c:if>
<c:if test="${error==7}" >
    ${e7}

</c:if>
<c:if test="${error==8}" >
    ${e8}

</c:if>
<c:if test="${error==400}" >
    <iframe src="//coub.com/embed/4jfpl?muted=false&autostart=true&originalSize=false&startWithHD=false" allowfullscreen="true" frameborder="0" width="498" height="314"></iframe><br />
    <b>ERROR 400.</b><br />
    <a href="/index">Go back to the main page</a>
</c:if>
<c:if test="${error==404}" >
    <iframe src="//coub.com/embed/4jfpl?muted=false&autostart=true&originalSize=false&startWithHD=false" allowfullscreen="true" frameborder="0" width="498" height="314"></iframe><br />
    <b>ERROR 404.</b>
    <a href="/index">Go back to the main page</a>
</c:if>
<c:if test="${error==500}" >
    <iframe src="//coub.com/embed/4jfpl?muted=false&autostart=true&originalSize=false&startWithHD=false" allowfullscreen="true" frameborder="0" width="498" height="314"></iframe><br />
    <b>ERROR 500.</b> <a href="/index">Go back to the main page</a>
</c:if>
<br />
${e}