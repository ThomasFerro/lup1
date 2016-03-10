(function(){
  angular.module('lup1', ['ngRoute','angular-jwt','ngStorage'])
  .run(["$rootScope", "Access", "$location",
        function($rootScope, Access, $location) {

          $rootScope.$on("$routeChangeError", function(event, current, previous, rejection) {
            if (rejection == Access.UNAUTHORIZED || rejection == Access.FORBBIDEN) {
              $location.path("/login");
            }
          });

        }]);
})();
