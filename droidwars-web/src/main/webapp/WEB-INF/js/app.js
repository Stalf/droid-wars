(function () {
    "use strict";

    angular.module('droidwars', [
        'ui.router',
        'droidwars.i18n',
        'ngSanitize',
        'pascalprecht.translate',
        'pasvaz.bindonce',
        'droidwars.topMenu',
        'droidwars.main'
    ])

        .config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
        }])

})();