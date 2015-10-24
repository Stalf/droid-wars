<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.droidwars.web.util.PropertiesUtils" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.Properties" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="messages" scope="request"/>
<c:set var="error" scope="request"/>

<script type="text/javascript">
    (function () {
        "use strict";
        angular.module('droidwars.i18n', ['pascalprecht.translate'])
                .factory('$translateUrlLoader', [
                    '$q',
                    '$http',
                    function ($q, $http) {
                        return function (options) {
                            if (!options || !options.url) {
                                throw new Error('Couldn\'t use urlLoader since no url is given!');
                            }
                            var deferred = $q.defer();
                            $http({
                                url: options.url,
                                params: {lang: options.key},
                                method: 'GET'
                            }).success(function (data) {
                                deferred.resolve(data);
                            }).error(function (data) {
                                deferred.reject(options.key);
                            });
                            return deferred.promise;
                        };
                    }
                ])
                .config(['$translateProvider', function ($translateProvider) {
                    <%
                        try {
                            Properties messageProperties = PropertiesUtils.getMessageProperties();
                            request.setAttribute("messages", new ObjectMapper().writeValueAsString(messageProperties));
                            request.setAttribute("error", false);

                        } catch (Exception e) {
                            request.setAttribute("error", true);
                        }
                    %>
                    <c:choose>
                    <c:when test="${error == true}">
                        throw new Error("Ошибка загрузки i18n");
                    </c:when>
                    <c:otherwise>
                        $translateProvider.translations('ru', ${messages});
                        $translateProvider.preferredLanguage('ru');
                        $translateProvider.useSanitizeValueStrategy('sanitize');
                    </c:otherwise>
                    </c:choose>
                }]);
    }());
</script>
