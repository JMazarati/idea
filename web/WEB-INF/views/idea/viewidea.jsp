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
<h1>${labelcontactlist}</h1>
<c:if test="${not empty list}">

    <c:forEach items="${list}" var="list">

        <div class="idea">
        <div class="row row1">
            <div id="tags" class="col-xs-4">${list.tags}</div>
            <div id="title" class="col-xs-8">${list.caption}</div>
        </div>
        <div class="row row1">
            <div id="username" class="col-xs-4">${list.username}</div>
            <div id="date" class="col-xs-8">${list.date_create}</div>
        </div>
        <div id="textOfIdea" class="text-center">${list.txt}</div>
        <div class="row row1">
            <div id="rating" class="col-xs-5">${list.rating}</div>
            <div id="comments" class="col-xs-5">Comments</div>
            <div id="view" class="col-xs-2"><a href="viewidea/${list.id}">View more</a></div>
        </div>
        </div>

        </c:forEach>

</c:if>
<c:if test="${empty list}">

    <img src="<c:url value="/resources/pict/10753921.jpg" />" /> <br />
   Ну введи хоть что нибудь! <a href="/addidea">${labellink2}</a><br/>

</c:if>