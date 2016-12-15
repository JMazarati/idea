<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dmf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 07.11.2016
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="label_link_2" var="labellink2" />
<spring:message code="label_sort" var="label_sort" />
<spring:message code="label_sort_data" var="lsdate" />
<spring:message code="label_sort_rating" var="lsrating" />
<spring:message code="label_rating" var="label_rating" />
<spring:message code="label_date" var="label_date" />
<spring:message code="label_more" var="label_more" />
<spring:message code="error_nothing_found" var="enf" />
<spring:message code="label_categories" var="label_categories" />
<spring:message code="label_viewideas_title" var="label_viewideas_title" />

<div class="row">

    <div class="col-xs-10">
        <h4 class="page-header text-center">${label_viewideas_title}</h4>
        <h4 id="sort" class="text-center">${label_sort}: <a href="${date}">${lsdate}</a> <a href="${rating}">${lsrating}</a></h4>
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
                        <div id="rating" class="col-xs-10">
                            <div id="reviewLightbulb-input">
                                <input id="lightbulb-4" type="radio" name="reviewLightbulb"  value ="5"/>
                                <label title="gorgeous" for="lightbulb-4"></label>

                                <input id="lightbulb-3" type="radio" name="reviewLightbulb"  value ="4"/>
                                <label title="good" for="lightbulb-3"></label>

                                <input id="lightbulb-2" type="radio" name="reviewLightbulb"  value ="3"/>
                                <label title="regular" for="lightbulb-2"></label>

                                <input id="lightbulb-1" type="radio" name="reviewLightbulb"  value ="2"/>
                                <label title="poor" for="lightbulb-1"></label>

                                <input id="lightbulb-0" type="radio" name="reviewLightbulb" value ="1"/>
                                <label title="bad" for="lightbulb-0"></label>
                            </div>
                                ${list.rating}
                        </div>
                        <div id="view" class="col-xs-2"><a href="viewidea/${list.id}">${label_more}</a></div>
                    </div>
                </div>

            </c:forEach>

        </c:if>
        <c:if test="${empty list}">

            <img src="<c:url value="/resources/pict/10753921.jpg" />" /> <br />
            ${enf} <a href="/addidea">${labellink2}</a><br/>
        </c:if>
    </div>

    <%--Categories--%>
    <div id="list" class="list-group col-xs-2">

        <h4 class="list-group-item-heading text-center">${label_categories}</h4>
        <c:forEach items="${cat}" var="cat" >
           <a href="/category?cat=${cat.key}" class="list-group-item "><spring:message code="${cat.value}" /></a>
        </c:forEach>
    </div>
    <%--End of Categories--%>

</div>

<div>
    <a class="back-to-top glyphicon glyphicon-arrow-up" href="/viewidea" title="Top">Back to top</a>
</div>