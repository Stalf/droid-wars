<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/logout" var="logoutUrl"/>
<spring:url value="/" var="homeUrl"/>

<div role="navigation" class="navbar navbar-default navbar-fixed-top"
     ng-controller="TopMenuCtrl" id="topMenu">

    <div class="navbar-header">
        <a class="navbar-brand" ui-sref="main"><span translate-once="app.title"></span></a>

    </div>

    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a ui-sref="main"><span translate-once="topMenu.main"></span></a></li>
            <li><a ui-sref="about"><span translate-once="topMenu.about"></span></a></li>
        </ul>
    </div>
</div>