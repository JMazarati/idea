<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="label_delete" var="labeldelete"/>
<spring:message code="label_update" var="labelupdate"/>
<spring:message code="label_button_submit" var="label_button_submit"/>
<spring:message code="label_comments" var="label_comments"/>
<spring:message code="label_reply" var="label_reply"/>
<spring:message code="label_comment_idea" var="label_comment_idea"/>

<%--<div id="delete_update" class="update-idea">--%>

    <%--<sec:authorize access="!isAnonymous()">--%>
        <%--<sec:authentication property="principal.username" var="username"/>--%>
    <%--</sec:authorize>--%>
    <%--<sec:authorize access="hasRole('ROLE_USER')">--%>
        <%--<c:if test="${username eq check.username}">--%>
            <%--<a href="/editidea/${check.id}"><span id="glyphicons" class="glyphicon glyphicon-edit" aria-hidden="true"/>${labelupdate}</a><br/>--%>
            <%--<a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();"><span id="glyphicons" class="glyphicon glyphicon-trash" aria-hidden="true"/>${labeldelete}--%>
            <%--</a>--%>
        <%--</c:if>--%>
    <%--</sec:authorize>--%>
    <%--<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">--%>
        <%--<a href="/editidea/${check.id}"><span id="glyphicons" class="glyphicon glyphicon-edit" aria-hidden="true"/>${labelupdate}</a>--%>
        <%--<a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();"><span id="glyphicons" class="glyphicon glyphicon-trash" aria-hidden="true"/>${labeldelete}--%>
        <%--</a>--%>
    <%--</sec:authorize>--%>
<%--</div>--%>

<div id="idea" class="showIdea">

    <span color="red">${err}</span><br/>
    <div class="header-panel">
        <div class="row row2">
            <div id="checkID" class="col-xs-2">ID: ${check.id}</div>
            <div id="title" class="col-xs-10">${check.caption}</div>
        </div>
        <div class="row row2">
            <div class="col-xs-9" id="username">By ${check.username}</div>
            <div id="date" class="col-xs-3">added ${check.date_create}</div>
        </div>
        <div id="category"><span id="nameCategory">Category: </span>${check.category}</div>
        <div id="tags"><span id="nameTags">Tags: </span>
            <%-- Выводим тэги как ссылки из backEnd-a--%>
            <c:forEach items="${tags_separated}" var="ts">
                <a href="/tags?tag=${ts}&sort=true">${ts}</a> &nbsp;
            </c:forEach></div>
        <div id="delete_update" class="update-idea">

            <sec:authorize access="!isAnonymous()">
                <sec:authentication property="principal.username" var="username"/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <c:if test="${username eq check.username}">
                    <a href="/editidea/${check.id}"><span id="glyphicons" class="glyphicon glyphicon-edit" aria-hidden="true"/>${labelupdate}</a><br/>
                    <a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();"><span id="glyphicons" class="glyphicon glyphicon-trash" aria-hidden="true"/>${labeldelete}
                    </a>
                </c:if>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                <a href="/editidea/${check.id}"><span id="glyphicons" class="glyphicon glyphicon-edit" aria-hidden="true"/>${labelupdate}</a>
                <a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();"><span id="glyphicons" class="glyphicon glyphicon-trash" aria-hidden="true"/>${labeldelete}
                </a>
            </sec:authorize>
        </div>
    </div>

    <div class="contentOfIdea">
        <%--Media--%>
        <div id="mediaItems">
            <c:if test="${check.pict.length()>1}">

                <a href="#img1">
                    <img src="/resources/upload_image/${check.pict}"  class="thumbnail"/>
                </a>

                <!-- lightbox container hidden with CSS -->
                <a href="#_" class="lightbox" id="img1">
                    <img src="/resources/upload_image/${check.pict}" />
                </a>

            </c:if>
            <c:if test="${check.video.length()>1}">
                <iframe width="560" height="315" src="https://www.youtube.com/embed/${check.video}" frameborder="0"
                        allowfullscreen></iframe>
                <br/>
            </c:if>
        </div>
        <%--End of media   --%>
        <div id="textOfIdeaShow">
            <c:if test="${check.txt.length()>1}">
                <%--Text--%>
                ${check.txt}
            </c:if>
        </div>
    </div>
    <div class="row" id="row3">
        <div class="col-xs-8" id="rating">
            <div id="reviewLightbulb-input">
                <input id="lightbulb-4" type="radio" value="5" name="reviewLightbulb"/>
                <label title="gorgeous" for="lightbulb-4"></label>

                <input id="lightbulb-3" type="radio" value="4" name="reviewLightbulb"/>
                <label title="good" for="lightbulb-3"></label>

                <input id="lightbulb-2" type="radio" value="3" name="reviewLightbulb"/>
                <label title="regular" for="lightbulb-2"></label>

                <input id="lightbulb-1" type="radio" value="2" name="reviewLightbulb"/>
                <label title="poor" for="lightbulb-1"></label>

                <input id="lightbulb-0" type="radio" value="1" name="reviewLightbulb"/>
                <label title="bad" for="lightbulb-0"></label>
            </div>
            <sec:authorize access="!isAnonymous()">
                <form:form action="${pageContext.servletContext.contextPath}/vote" method="post" id="f1"
                           commandName="rcommand">
                    <input name="idea_link" type="hidden" value="${check.id}"/>
                    <form:select path="rating" items="${votelist}"/>
                    <input type="submit" value="Submit"/>
                </form:form>
            </sec:authorize>

            <div id="totalRating"> total rating:<fmt:formatNumber type="number" maxFractionDigits="2" value="${check.rating}" />

            </div>
        </div>

        <div class="text-center col-xs-4">


            <div class="col-xs-4" id="nameLikes"> like/dislike</div>
            <div class="col-xs-8">
                <sec:authorize access="isAuthenticated()">
                <table>
                    <tr>
                        <td>
                            <form:form method="post" action="${pageContext.servletContext.contextPath}/like" id="like" commandName="rcommand">
                                <input name="idea_link" type="hidden" value="${check.id}"/>
                                <input name="likeordislike" type="hidden" value="1"/>
                                <button class="likes" type="submit" >
                                    <img src="/resources/pict/like.jpg" width="19" height="30" alt="likes">${check.count_like}
                                </button>
                            </form:form>
                        </td>
                        <td>
                            <form:form method="post" action="${pageContext.servletContext.contextPath}/like" id="dislike" commandName="rcommand">
                                <input name="idea_link" type="hidden" value="${check.id}"/>
                                <input name="likeordislike" type="hidden" value="-1"/>
                                <button class="likes" type="submit">
                                    <img src="/resources/pict/dislike.jpg" width="19" height="30" alt="dislikes">${check.count_dislike}
                                </button>
                            </form:form>
                        </td>
                    </tr>
                </table>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <button class="likes" type="button" >
                        <img src="/resources/pict/like.jpg" width="19" height="30" alt="likes">${check.count_like}
                    </button>
                    <button class="likes" type="button">
                        <img src="/resources/pict/dislike.jpg" width="19" height="30" alt="dislikes">${check.count_dislike}
                    </button>
                </sec:authorize>
            </div>
        </div>
    </div>


    <div class="pull-left col-xs-12" id="titleComments">${label_comments}:
        <sec:authorize access="isAuthenticated()">
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span class="reply_to" onclick="setCommentId(0)">${label_comment_idea}</span>
        </sec:authorize>
    </div>
    <div id="comments">
        <c:if test="${not empty child}">
        <c:forEach items="${child}" var="child">
        <c:if test="${child.id eq child.parentLink}">
        </div>
        <br/>
        </c:if>
    <c:if test="${child.id ne child.parentLink}">
        <c:forEach var="cycle" begin="0" end="${child.depth}">
            &nbsp;
        </c:forEach>
    </c:if>

    <span id="reply_span"><i> ${child.userLink} ${child.dateComment}</i>&nbsp;</span><br/>
    <c:if test="${child.id ne child.parentLink}">
        <c:forEach var="cycle" begin="0" end="${child.depth}">
            &nbsp;
        </c:forEach>
    </c:if>
        ${child.note}
    <c:if test="${child.id eq child.parentLink}">

    <a href="javascript:look('${child.id}');" title="Смотреть HOLY WAR">HOLY WAR</a><br/>
    <div id="${child.id}" style="display: none;">
        </c:if>


        <sec:authorize access="!isAnonymous()">
            <span class="reply_to" onclick="setCommentId(${child.id})">${label_reply}</span>
        </sec:authorize>
        <br/>
        </c:forEach>
        </c:if>
    </div>

    <sec:authorize access="!isAnonymous()">
        <br/>
        <form:form method="post" action="${pageContext.servletContext.contextPath}/addcomments" id="f2">
            <form:input type="hidden" path="ideaLink" value="${check.id}"/>
            <form:input type="hidden" path="userLink" value="${username}"/>
            <form:input id="parentLink" type="hidden" path="parentLink" value=""/>
            <table id="inputComments">
                <tr>
                    Введите комментарий:
                </tr>
                <tr>
                    <form:textarea id="commentField" class="form-control" path="note" maxlength="256" rows="3" htmlEscape="true"/>
                </tr>
                <tr id="commentIdea" class="pull-right">
                    <td>

                    </td>
                    <td><input type="submit" class="btn btn-default" value="${label_button_submit}"/></td>
                </tr>
                <tr>
                    <td><span class="error"><form:errors path="note"/></span></td>
                </tr>
            </table>
        </form:form>
        <br/>

    </sec:authorize>
    <br/>
</div>
