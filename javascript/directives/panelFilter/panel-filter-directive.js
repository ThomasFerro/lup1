(function() {
    angular.module('lup1')
    .directive('panelFilter', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/panelFilter/panel-filter.html'
      };
    });
}
)();
