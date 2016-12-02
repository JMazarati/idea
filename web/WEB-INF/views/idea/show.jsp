<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="showIdea">
    <div class="header-panel">
        <div class="row row2">
            <div id="checkID" class="col-xs-2">ID: ${check.id}</div>
            <div id="title" class="col-xs-10">${check.caption}</div>
        </div>
        <div class="row row2">
            <div class="col-xs-9" id="username">By ${check.username}</div>
            <div id="date" class="col-xs-3">added ${check.date_create}</div>
        </div>
            <div id="category"> <span id="nameCategory">Category: </span>${check.category}</div>
            <div id="tags"> <span id="nameTags">Tags: </span> ${check.tags}</div>
        </div>


    <div class="contentOfIdea">
        <%--Media--%>
        <div id="mediaItems">
            <c:if test="${check.pict.length()>1}">

                <img src="/resources/upload_image/${check.pict}" width="560"/> <br/>

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
        <div class="col-xs-9" id="rating"> RATING: ${check.rating}</div>
        <div class="text-center col-xs-3" id="likes"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"/>${check.count_like}<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true" />${check.count_dislike}</div>


    </div>
<div class="comments">
    <div id="titleComments">Comments:</div>
    <div id="allComments">
        <div id="usernameOfComment">Username left a comment:</div>
        <div id="textOfComment">Text of comment</div>
        <div id="dateOfComment">posted 27.11.2016 23:11</div>
    </div>
    <div id="leaveComment" class="pull-right btn btn-default"><a href="">Leave a comment</a></div>
</div>

</div>