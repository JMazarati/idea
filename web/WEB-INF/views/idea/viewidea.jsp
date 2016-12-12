<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 07.11.2016
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="label_link_2" var="labellink2" />
<spring:message code="error_nothing_found" var="enf" />
<h1>${labelcontactlist}</h1>
<c:if test="${not empty list}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>User name</th>
            <th>Caption of idea</th>
            <th>View</th>

        </tr>
        </thead>

        <tbody>
        <c:forEach items="${list}" var="list">
            <tr>
                <th><a href="viewidea/${list.id}">${list.id}</a></th>
                <th>${list.username}</th>
                <th>${list.caption}</th>
                <th><a href="viewidea/${list.id}">view</a></th>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</c:if>
<c:if test="${empty list}">

    <img src="<c:url value="/resources/pict/10753921.jpg" />" /> <br />
   ${enf} <a href="/addidea">${labellink2}</a><br/>

</c:if>