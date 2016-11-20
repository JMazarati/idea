<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    Тут будут комментарии
</div>
<br />
<span style="color: #0e1aff; ">* Если какое-то поле идеи не заполнено, то оно отображаться на форме не будет</span>