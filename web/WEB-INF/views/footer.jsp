<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<spring:message code="label_copy" var="label_copy" />

<footer id="foo" class="panel-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center" id="contacts">
                ${label_copy}
                <a href="https://github.com/andrewdark/idea">GitHub</a>
            </div>
            <div id="language" class="col-md-12 text-center">
                <a href="${currentUrl}?lang=en">${labelEn}</a>
                <a href="${currentUrl}?lang=ru">${labelRu}</a>
            </div>
            <div class="col-md-12 text-center" id="copyright">&copy; Copyright J.A.V.A.I.T. 2016</div>

        </div>
    </div>
</footer>
<c:set var="prop_tag" value="<%=(request.getParameter("tag")) %>"/>
<c:set var="prop_cat" value="<%=(request.getParameter("cat")) %>"/>
<c:set var="prop_lang" value="<%=(request.getParameter("lang")) %>"/>

<c:if test="${prop_tag eq null && prop_cat eq null}">
    <a href="${currentUrl}?lang=en">${labelEn}</a>
    <a href="${currentUrl}?lang=ru">${labelRu}</a>
</c:if>
<c:if test="${prop_tag ne null || prop_cat ne null}">

    <c:set var="rquery" value="${pageContext.request.queryString}"/>
    <c:if test="${prop_lang ne null}">
        <c:set var="rquery" value='${rquery.replaceAll("&lang=en",null)}'/>
        <c:set var="rquery" value='${rquery.replaceAll("&lang=ru",null)}'/>
    </c:if>
    <a href="?${rquery}&lang=en">${labelEn}</a>
    <a href="?${rquery}&lang=ru">${labelRu}</a>
</c:if>
