(function () {
    "use strict";

    angular.module('droidwars.main', [])

        .config(['$stateProvider', function ($stateProvider) {

            $stateProvider.state('main', {
                url: '/',
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
                .state('about', {
                    url: '/about',
                    templateUrl: 'views/about.html',
                    controller: 'AboutCtrl'
                })
        }])

        .controller('MainCtrl', ['$scope', function ($scope) {

        }])

        .controller('AboutCtrl', ['$scope', function ($scope) {

        }])
})();