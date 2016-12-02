<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    function setCommentId(k) {
        document.getElementById('parentLink').value = k;
    }
</script>

<div id="idea">
    <h1>
        ${check.username} added IDEA
        ID: ${check.id} <br/>
        Caption: ${check.caption} <br/>RATING: ${check.rating}<br/>
    </h1>
    Category: ${check.category} &nbsp; Tags:${check.tags}&nbsp;
    added ${check.date_create}
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


                <br/></c:if>

            <c:if test="${child.id ne child.parentLink}">

                <c:forEach var="cycle" begin="0" end="${child.depth}">
                    &nbsp;

                </c:forEach>
            </c:if>

            <span style="font-size: x-small; "><i> ${child.id} ${child.userLink}${child.dateComment}</i>&nbsp;${child.note} </span>
            <button onclick="setCommentId(${child.id})">reply</button>


            <br/>

        </c:forEach>
    </c:if>
</div>
<sec:authorize access="!isAnonymous()">
    <sec:authentication property="principal.username" var="username"/>
    <form:form method="post" action="${pageContext.servletContext.contextPath}/addcomments" id="f2">
        <form:input type="hidden" path="ideaLink" value="${check.id}"/>
        <form:input type="hidden" path="userLink" value="${username}"/>
        <form:input id="parentLink" type="hidden" path="parentLink" value=""/>
        <table>
            <tr>
                <td>Комментируем комент</td>
                <td>${clink}</td>
            </tr>
            <tr>
                <td><form:input path="note" maxlength="50"/></td>
                <td><input type="submit" value="Submit"/></td>

            </tr>
            <tr>

                <td><span class="error"><form:errors path="note"/></span></td>

            </tr>
        </table>
    </form:form>
    <button onclick="setCommentId(0)">root</button><br />

</sec:authorize>
<br/>
<span style="color: #0e1aff; ">* Если какое-то поле идеи не заполнено, то оно отображаться на форме не будет</span>