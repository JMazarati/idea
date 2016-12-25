<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label_index_title" var="label_index_title"/>

<h4 class="text-center page-header">${label_index_title}</h4>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <a href="/viewidea/${top5.get(0).id}"><img src="/resources/upload_image/${top5.get(0).pict}" alt="picture" class="thumbnail"/></a>
            <div>
                <p><c:if test="${top5.get(0).txt.length() > 0}">
                    ${top5.get(0).txt}
                </c:if>
                    <c:if test="${top5.get(0).txt.length() < 1}">
                        ${top5.get(0).caption}
                    </c:if>
                </p>
            </div>
        </div>
        <div class="item">
            <a href="/viewidea/${top5.get(1).id}"><img src="/resources/upload_image/${top5.get(1).pict}" alt="picture" class="thumbnail"/></a>
            <div>
                <p><c:if test="${top5.get(1).txt.length() > 0}">
                    ${top5.get(1).txt}
                </c:if>
                    <c:if test="${top5.get(1).txt.length() < 1}">
                        ${top5.get(1).caption}
                    </c:if>
                </p>
            </div>
        </div>

        <div class="item">
            <a href="/viewidea/${top5.get(2).id}"><img src="/resources/upload_image/${top5.get(2).pict}" alt="picture" class="thumbnail"/></a>
            <div>
                <p>
                    <c:if test="${top5.get(2).txt.length() > 0}">
                        ${top5.get(2).txt}
                    </c:if>
                    <c:if test="${top5.get(2).txt.length() < 1}">
                        ${top5.get(2).caption}
                    </c:if>
                </p>
            </div>

        </div>

        <div class="item">
            <a href="/viewidea/${top5.get(3).id}"><img src="/resources/upload_image/${top5.get(3).pict}" alt="picture" class="thumbnail"/></a>
            <div>
                <p>
                    <c:if test="${top5.get(3).txt.length() > 0}">
                        ${top5.get(3).txt}
                    </c:if>
                    <c:if test="${top5.get(3).txt.length() < 1}">
                        ${top5.get(3).caption}
                    </c:if>
                </p>
            </div>
        </div>

        <div class="item">
            <a href="/viewidea/${top5.get(4).id}"><img src="/resources/upload_image/${top5.get(4).pict}" alt="picture" class="thumbnail"/></a>
            <div>
                <p>
                    <c:if test="${top5.get(4).txt.length() > 0}">
                        ${top5.get(4).txt}
                    </c:if>
                    <c:if test="${top5.get(4).txt.length() < 1}">
                        ${top5.get(4).caption}
                    </c:if>
                </p>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

