(function() {
    angular.module('lup1')
    .directive('menuItem', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/menu/menu-item.html'
      };
    });
}
)();
