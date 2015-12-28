<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="droidwars">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title><spring:message code="app.title"/></title>

    <spring:url value="/assets/favicon.ico" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <spring:url value="/webjars/jquery/2.1.4/jquery.min.js" var="jquery"/>
    <script src="${jquery}"></script>

    <spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" var="bootstrapCss"/>
    <link rel="stylesheet" href="${bootstrapCss}">

    <spring:url value="/css/main.css" var="mainCss"/>
    <link rel="stylesheet" href="${mainCss}">

</head>
<body>

<jsp:include page="includes/topMenu.jsp"/>
<div class="container-fluid">
    <jsp:include page="includes/mainContainer.jsp"/>
</div>

<spring:url value="/webjars/angularjs/1.4.7/angular.js" var="angularjs"/>
<script src="${angularjs}"></script>
<spring:url value="/webjars/angular-i18n/1.4.7/angular-locale_ru.js" var="angularjsI18n"/>
<script src="${angularjsI18n}"></script>
<spring:url value="/webjars/angular-resource/1.4.7/angular-resource.min.js" var="angularResource"/>
<script src="${angularResource}"></script>
<spring:url value="/webjars/angular-translate/2.7.2/angular-translate.min.js" var="angularTranslate"/>
<script src="${angularTranslate}"></script>
<spring:url value="/webjars/angular-ui-router/0.2.15/angular-ui-router.min.js" var="uiRouter"/>
<script src="${uiRouter}"></script>
<spring:url value="/webjars/angular-sanitize/1.4.7/angular-sanitize.js" var="angularSanitize"/>
<script src="${angularSanitize}"></script>

<spring:url value="/webjars/angular-bindonce/0.3.3/bindonce.min.js" var="uiRouter"/>
<script src="${uiRouter}"></script>

<spring:url value="/lib/translate-once.js" var="translateOnce"/>
<script src="${translateOnce}"></script>

<jsp:include page="includes/i18n.jsp"/>

<jsp:include page="includes/appJs.jsp"/>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
