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
            <b>${labelupdate}</b><br/><b>${labeldelete}</b>
        </c:if>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <b>${labelupdate}</b><br/><b>${labeldelete}</b>
    </sec:authorize>
</div>

<div id="idea" class="showIdea">
    <p color="red">${err}</p><br/>

                <p id="title" class="">${check.caption}</p>
                <ul class="info">
                    <li id="checkID" class="">ID: ${check.id}</li>
                    <li class="" id="username">By ${check.username}</li>
                    <li id="date" class="">added ${check.date_create}</li>
                    <li id="category"> Category: ${check.category}</li>
                    <li id="tags"> Tags: </span> ${check.tags}</li>
                </ul>
</div>

        <div class="contentOfIdea">
            <%--Media--%>
            <%--<div id="mediaItems">--%>
                <%--<c:if test="${check.pict.length()>1}">--%>

                    <%--<img src="/resources/upload_image/${check.pict}" width="560"/> <br/>--%>

                <%--</c:if>--%>
                <%--<c:if test="${check.video.length()>1}">--%>

                    <%--<iframe width="560" height="315" src="https://www.youtube.com/embed/${check.video}" frameborder="0"--%>
                            <%--allowfullscreen></iframe>--%>
                    <%--<br/>--%>

                <%--</c:if>--%>
            <%--</div>--%>
                <div id="imgItems">
                    <c:if test="${check.pict.length()>1}">

                        <img src="/resources/upload_image/${check.pict}" />

                    </c:if>
                </div>

                <div class="videoItems">
                    <c:if test="${check.video.length()>1}">

                        <iframe width="560" height="315" src="https://www.youtube.com/embed/${check.video}" frameborder="0"
                                allowfullscreen></iframe>

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

        <div class="" id="">
            <div class="" id="rating"> RATING: ${check.rating}</div>
            <div class="text-center " id="likes">
                <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">${check.count_like}</span>
                <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true" >${check.count_dislike}</span>
            </div>

        </div>

        <div id="comments">

            <c:if test="${not empty child}">
                <c:forEach items="${child}" var="child">

                    <c:if test="${child.id eq child.parentLink}">

                        </c:if>
                    <c:if test="${child.id ne child.parentLink}">
                        <c:forEach var="cycle" begin="0" end="${child.depth}">

                        </c:forEach>
                    </c:if>
                <div class="comment">
                    <p><span class="comment-user_name">${child.userLink}</span>
                        <span class="comment-date">${child.dateComment}</span>
                    </p>
                    <p>${child.note}</p>
                    <sec:authorize access="!isAnonymous()">
                        <%--<button onclick="setCommentId(${child.id})">reply</button>--%>
                        <p><span class="reply_to" onclick="setCommentId(${child.id})">Reply</span></p>
                    </sec:authorize>
                </div>

                </c:forEach>
            </c:if>
        </div>


        <sec:authorize access="!isAnonymous()">

        <form:form method="post" action="${pageContext.servletContext.contextPath}/addcomments" id="f2">
            <form:input type="hidden" path="ideaLink" value="${check.id}"/>
            <form:input type="hidden" path="userLink" value="${username}"/>
            <form:input id="parentLink" type="hidden" path="parentLink" value=""/>

                <p>Введите комментарий:</p>
                <form:textarea id="commentField" path="note" maxlength="128"/>
                <input type="submit" value="Submit"/>
                <span class="error"><form:errors path="note"/></span>

        </form:form>
        <button onclick="setCommentId(0)">root</button>


        </sec:authorize>

<p> ${messageviewidea} </p>