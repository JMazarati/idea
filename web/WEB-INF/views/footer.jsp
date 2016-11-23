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

<footer id="foo" class="panel-footer navbar-fixed-bottom">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center" id="contacts">
                Contact us on
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
