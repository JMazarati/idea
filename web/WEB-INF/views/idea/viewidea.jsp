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
<spring:message code="label_rating" var="label_rating" />
<spring:message code="label_date" var="label_date" />
<spring:message code="error_nothing_found" var="enf" />
<spring:message code="label_categories" var="label_categories" />
<spring:message code="label_viewideas_title" var="label_viewideas_title" />
<spring:message code="IT" var="IT" />
<spring:message code="Architecture" var="Architecture" />
<spring:message code="Business" var="Business" />
<spring:message code="Charity" var="Charity" />
<spring:message code="Design" var="Design" />
<spring:message code="House" var="House" />
<spring:message code="Health" var="Health" />
<spring:message code="Engineering" var="Engineering" />
<spring:message code="Internet" var="Internet" />
<spring:message code="Interior" var="Interior" />
<spring:message code="Art" var="Art" />
<spring:message code="Cookery" var="Cookery" />
<spring:message code="Love" var="Love" />
<spring:message code="Marketing" var="Marketing" />
<spring:message code="On_every_day" var="On_every_day" />
<spring:message code="Clothing" var="Clothing" />
<spring:message code="Rest" var="Rest" />
<spring:message code="Politics" var="Politics" />
<spring:message code="Celebration" var="Celebration" />
<spring:message code="Production" var="Production" />
<spring:message code="Travel" var="Travel" />
<spring:message code="Sundry" var="Sundry" />
<spring:message code="Joke" var="Joke" />
<spring:message code="Sex" var="Sex" />
<spring:message code="Social" var="Social" />
<spring:message code="Sport" var="Sport" />
<spring:message code="Equipment" var="Equipment" />
<spring:message code="Product" var="Product" />
<spring:message code="Photography" var="Photography" />
<spring:message code="Finances" var="Finances" />


<div class="row">

    <div class="col-xs-10">
        <h4 class="page-header text-center">${label_viewideas_title}</h4>
        <h4 id="sort" class="text-center">${label_sort}: <a href="${date}">${label_date}</a> / <a href="${rating}">${label_rating}</a></h4>
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
                        <div id="view" class="col-xs-2"><a href="viewidea/${list.id}">View more</a></div>
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
        <a href="#" class="list-group-item active">${IT}</a>
        <a href="#" class="list-group-item">${Architecture}</a>
        <a href="#" class="list-group-item">${Business}</a>
        <a href="#" class="list-group-item">${Charity}</a>
        <a href="#" class="list-group-item">${Design}</a>
        <a href="#" class="list-group-item">${House}</a>
        <a href="#" class="list-group-item">${Health}</a>
        <a href="#" class="list-group-item">${Engineering}</a>
        <a href="#" class="list-group-item">${Internet}</a>
        <a href="#" class="list-group-item">${Interior}</a>
        <a href="#" class="list-group-item">${Art}</a>
        <a href="#" class="list-group-item">${Cookery}</a>
        <a href="#" class="list-group-item">${Love}</a>
        <a href="#" class="list-group-item">${Marketing}</a>
        <a href="#" class="list-group-item">${On_every_day}</a>
        <a href="#" class="list-group-item">${Clothing}</a>
        <a href="#" class="list-group-item">${Rest}</a>
        <a href="#" class="list-group-item">${Politics}</a>
        <a href="#" class="list-group-item">${Celebration}</a>
        <a href="#" class="list-group-item">${Production}</a>
        <a href="#" class="list-group-item">${Travel}</a>
        <a href="#" class="list-group-item">${Sundry}</a>
        <a href="#" class="list-group-item">${Joke}</a>
        <a href="#" class="list-group-item">${Sex}</a>
        <a href="#" class="list-group-item">${Social}</a>
        <a href="#" class="list-group-item">${Sport}</a>
        <a href="#" class="list-group-item">${Equipment}</a>
        <a href="#" class="list-group-item">${Product}</a>
        <a href="#" class="list-group-item">${Photography}</a>
        <a href="#" class="list-group-item">${Finances}</a>
    </div>
    <%--End of Categories--%>

</div>

<div>
    <a class="back-to-top glyphicon glyphicon-arrow-up" href="/viewidea" title="Top">Back to top</a>
</div>