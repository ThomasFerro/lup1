(function() {
    angular.module('lup1')
    .directive('bulletin', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/bulletin/bulletin.html'
      };
    });
}
)();
