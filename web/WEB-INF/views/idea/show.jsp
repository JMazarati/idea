<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:message code="message_viewidea" var="messageviewidea"/>
<spring:message code="label_delete" var="labeldelete"/>
<spring:message code="label_update" var="labelupdate"/>

<div id="delete_update">

    <sec:authorize access="!isAnonymous()">
        <sec:authentication property="principal.username" var="username"/>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
        <c:if test="${username eq check.username}">
            <a href="/editidea/${check.id}">${labelupdate}</a><br/>
            <a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();">${labeldelete}</a>
        </c:if>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <a href="/editidea/${check.id}">${labelupdate}</a><br/>
        <a href="/deleteIdea?id=${check.id}" onclick="return confirmDelete();">${labeldelete}</a>
    </sec:authorize>
</div>


<div id="idea">

    <font color="red">${err}</font><br/>
    <h1>
        ${check.username} added IDEA
        ID: ${check.id} <br/>
        Caption: ${check.caption} <br/>RATING: ${check.rating}
        <sec:authorize access="!isAnonymous()">
            <form:form action="${pageContext.servletContext.contextPath}/vote" method="post" id="f1"
                       commandName="rcommand">
                <input name="idea_link" type="hidden" value="${check.id}"/>
                <form:select path="rating" items="${votelist}"/>
                <input type="submit" value="Submit"/>
            </form:form>
        </sec:authorize>
        <br/>
    </h1>
    <%-- spring:message code="${check.category}" var="labelcategory"/ --%>
    Category: ${check.category} &nbsp;
    added ${check.date_create}<br />
    Tags:
    <%-- Выводим тэги как ссылки из backEnd-a--%>
    <c:forEach items="${tags_separated}" var="ts">
        <a href="/tags?tag=${ts}&sort=true">${ts}</a> &nbsp;
    </c:forEach>
    <br/>
    <c:if test="${check.txt.length()>1}">
        TEXT:    ${check.txt} <br/>
    </c:if>
    <c:if test="${check.pict.length()>1}">
        <img src="/resources/upload_image/${check.pict}" width="560"/> <br/>
    </c:if>
    <c:if test="${check.video.length()>1}">
        <iframe width="560" height="315" src="https://www.youtube.com/embed/${check.video}" frameborder="0"
                allowfullscreen></iframe>
        <br/>
    </c:if>
    <br/>
    Like:${check.count_like} DisLike:${check.count_dislike}<br/>
</div>
<br/><br/>
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

        <span style="font-size: x-small; "
              id="reply_span"><i> ${child.userLink} ${child.dateComment}</i>&nbsp;</span><br/>
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
            <span class="reply_to" onclick="setCommentId(${child.id})">Reply</span>
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
        <table>
            <tr>
                <td>Введите комментарий:</td>
                <td></td>
            </tr>
            <tr>
                <td><form:input id="commentField" path="note" maxlength="256"/></td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
            <tr>
                <td><span class="error"><form:errors path="note"/></span></td>
            </tr>
        </table>
    </form:form>
    <button onclick="setCommentId(0)">root</button>
    <br/>

</sec:authorize>
<br/>
${messageviewidea}