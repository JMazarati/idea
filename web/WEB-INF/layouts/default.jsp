<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JAVA IT WEB APP</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/reply_to_comment.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/admin.css" />
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/pict/Lumier.jpg"/>
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/pict/idea.png"/>

</head>
<body class="spring">

<div class="container">
    <div class="header"><tiles:insertAttribute name="header" ignore="true" /></div>
    <div class="sidebar"><tiles:insertAttribute name="menu" ignore="true" /></div>
    <div class="content"><tiles:insertAttribute name="body" /></div>
    <div class="footer"><tiles:insertAttribute name="footer" ignore="true" /></div>
</div>
</body>
</html>
