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
                <a href="https://github.com/andrewdark/idea" target="_blank">GitHub</a>
            </div>


            <div class="col-md-12 text-center" id="copyright">&copy; Copyright J.A.V.A.I.T. 2016</div>

        </div>
    </div>
</footer>

