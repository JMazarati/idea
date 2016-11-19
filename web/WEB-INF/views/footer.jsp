<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 05.11.2016
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="label_en" var="labelEn" />
<spring:message code="label_ru" var="labelRu" />
<hr />
THIS IS FOOTER <br />

<a href="${currentUrl}?lang=en">${labelEn}</a>
<a href="${currentUrl}?lang=ru">${labelRu}</a>
