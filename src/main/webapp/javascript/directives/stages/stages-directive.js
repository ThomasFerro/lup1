(function() {
    angular.module('lup1')
    .directive('stages', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/stages/stages.html'
      };
    });
}
)();
