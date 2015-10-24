<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/topMenu.js" var="topMenu"/>
<script src="${topMenu}"></script>

<spring:url value="/main/main.js" var="mainJS"/>
<script src="${mainJS}"></script>

<spring:url value="/app.js" var="app"/>
<script src="${app}"></script>
