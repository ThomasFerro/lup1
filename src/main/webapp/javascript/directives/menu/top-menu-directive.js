(function() {
    angular.module('lup1')
    .directive('topMenu', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/menu/top-menu.html',
        controller:'AuthenticationController',
        controllerAs: 'authCtrl',
      };
    });
}
)();
