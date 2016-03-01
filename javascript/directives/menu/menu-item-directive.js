(function() {
    angular.module('lup1')
    .directive('menuItem', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/menu/menu-item.html'
      };
    });
}
)();
