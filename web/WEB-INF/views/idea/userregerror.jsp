<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 10.11.2016
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${error==0}" >
    <b>ИМЯ слишком мало</b>
    <a href="/reg">Попробовать еще раз</a>
</c:if>
<c:if test="${error==1}" >
    <b>ИМЯ ЗАНЯТО</b>
    <a href="/reg">Попробовать еще раз</a>
</c:if>
<c:if test="${error==2}" >
    <b>Пароли не совпадают, либо отсутствуют.</b>
    <a href="/reg">Попробовать еще раз</a>
</c:if>

<c:if test="${error==3}" >
    Не возможно записать данные в базу. Попробуйте позже.
</c:if>
<c:if test="${error==4}" >
    ПОЗДРАВЛЯЕМ! Теперь осталось только войти под своим паролем!

</c:if>
<c:if test="${error==5}" >
    Идея удалена!

</c:if>
<c:if test="${error==400}" >
    Ой! Что-то не получилось ничего сделать. <b>ERROR 400.</b><br />
    <a href="/index">Вернуться на главную</a>
</c:if>
<c:if test="${error==404}" >
    Ой! Не могу найти, но я таки старался!. <b>ERROR 404.</b><br />
    <a href="/index">Вернуться на главную</a>
</c:if>