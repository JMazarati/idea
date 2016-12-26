<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    span.error {
        color: red;
    }
</style>
<spring:message code="message_addideavideo" var="message_addideavideo" />
<spring:message code="message_addideatags" var="message_addideatags" />
<spring:message code="label_button_submit" var="label_button_submit" />
<spring:message code="label_add_your_idea" var="label_add_your_idea" />
<spring:message code="label_title" var="label_title" />
<spring:message code="label_category" var="label_category" />
<spring:message code="label_text" var="label_text" />
<spring:message code="label_picture" var="label_picture" />
<spring:message code="label_video" var="label_video" />
<spring:message code="label_tags" var="label_tags" />


<form:form method="POST" action="${pageContext.servletContext.contextPath}/addideapost" enctype="multipart/form-data">
    <h4 class="text-center page-header">${label_add_your_idea}</h4>
    <table class="form-horizontal" id="tableAddIdea" >
        <tr>
            <td><form:label path="caption">${label_title}</form:label></td>
            <td><form:input htmlEscape="true" path="caption" class="form-control" maxlength="128"/></td>
            <td><span class="error" ><form:errors path="caption" /></span></td>
        </tr>
        <tr>
            <td><form:label path="category" >${label_category}</form:label></td>
            <td><form:select path="category" items="${cat}" id="sel">


            </form:select></td>
        </tr>
        <tr>
            <td><form:label path="txt">${label_text}</form:label></td>
            <td><form:textarea  class="form-control" path="txt" cols="30" rows="5" /></td>
            <td><span class="error" ><form:errors path="txt" /></span></td>
        </tr>
        <tr>
            <td><form:label path="pict">${label_picture}</form:label></td>
            <td><input name="p" type="file" value="hello file" /></td>
            <td><span class="error" ><form:errors path="file" /></span></td>

        </tr>
        <tr>
            <td><form:label path="video">${label_video}</form:label></td>
            <td><form:input path="video" class="form-control" maxlength="11"/>${message_addideavideo}</td>
            <td><span class="error" ><form:errors path="video" /></span></td>
        </tr>
        <tr>
            <td><form:label path="tags">${label_tags}</form:label></td>
            <td><form:input class="form-control" path="tags" maxlength="256"/>${message_addideatags}</td>
            <td><span class="error" ><form:errors path="tags" /></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" id="addBtn" class="btn btn-default" value="${label_button_submit}"/>
            </td>
        </tr>
    </table>
</form:form>
