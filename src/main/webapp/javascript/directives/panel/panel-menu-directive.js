(function() {
    angular.module('lup1')
    .directive('panelMenu', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/panel/panel-menu.html'
      };
    });
}
)();
