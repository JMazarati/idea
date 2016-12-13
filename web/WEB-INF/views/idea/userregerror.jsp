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
<spring:message code="error_6" var="e6" />
<c:if test="${error==0}" >
    <b>Вернуться на главную</b>
    <a href="/">Вернуться на главную</a>
</c:if>
<c:if test="${error==1}" >
    <b>ИМЯ ЗАНЯТО</b>
    <a href="/reg">Попробовать еще раз</a>
</c:if>
<c:if test="${error==2}" >
    <b>Идея отсутствует либо была удалена до Вас</b>
    <a href="/addidea">Создать идею?</a>
</c:if>

<c:if test="${error==3}" >
    <iframe src="//coub.com/embed/4jfpl?muted=false&autostart=true&originalSize=false&startWithHD=false" allowfullscreen="true" frameborder="0" width="498" height="314"></iframe><br />
    Не возможно записать данные в базу. Попробуйте позже.<br>
    ${message}

</c:if>
<c:if test="${error==4}" >
    ПОЗДРАВЛЯЕМ! Теперь осталось только войти под своим паролем!

</c:if>
<c:if test="${error==5}" >
    Идея удалена!

</c:if>
<c:if test="${error==6}" >
    ${e6}

</c:if>
<c:if test="${error==7}" >
    У Вас нет прав на выполнение данной операции!

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