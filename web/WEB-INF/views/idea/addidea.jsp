<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 07.11.2016
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    span.error {
        color: red;
    }
</style>
<form:form method="POST" action="${pageContext.servletContext.contextPath}/addideapost" enctype="multipart/form-data">
    <table>
        <tr>
            <td><form:label path="caption">caption</form:label></td>
            <td><form:input path="caption" maxlength="128"/></td>
        </tr>
        <tr>
            <td><form:label path="category">category</form:label></td>
            <td><form:select path="category" items="${cat}">


            </form:select></td>
        </tr>
        <tr>
            <td><form:label path="txt">txt</form:label></td>
            <td><form:input path="txt" /></td>
            <td><span class="error" ><form:errors path="txt" /></span></td>
        </tr>
        <tr>
            <td><form:label path="pict">picture</form:label></td>

            <td><input name="p" type="file"/></td>
            <td><span class="error" ><form:errors path="file" /></span></td>

        </tr>
        <tr>
            <td><form:label path="video">video YOUTUBE</form:label></td>
            <td><form:input path="video" maxlength="11"/></td>
        </tr>
        <tr>
            <td><form:label path="tags">tags</form:label></td>
            <td><form:input path="tags" maxlength="256"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
<span style="color: #0e1aff; ">* Если мы добавляем ссылку на видео в ютюбе, то вставлять надо только код видео. Выделено красным цветом.<br />
https://www.youtube.com/watch?v=</span><span style="color: #ff0003; ">rU1m-nyfK34</span><br />
<span style="color: #0e1aff; ">* Если мы добавляем несколько тегов, то разделять их надо запятыми</span>
