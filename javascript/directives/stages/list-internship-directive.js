(function() {
  angular.module('lup1')
  .directive('listInternship', function() {
    return {
      restrict: 'E',
      templateUrl: 'templates/pages/stages/list-internship.html',
      controller: function() {
        this.active = 'module';

        this.setActive = function (buttonName) {
          this.active = buttonName;
        };

        this.isActive = function (buttonName) {
          return this.active === buttonName;
        };
      },
      controllerAs: 'viewController'
    };
  });
}
)();
