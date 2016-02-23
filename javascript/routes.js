angular.module('lup1')
.config(function($routeProvider) {
  $routeProvider
  .when('/notes', {
    templateUrl: 'templates/pages/notes/index.html'
  })
  .when('/interventions', {
    templateUrl: 'templates/pages/interventions/index.html'
  })
  .when('/stages', {
    templateUrl: 'templates/pages/stages/index.html'
  })
  .when('/', {
    templateUrl: 'templates/pages/notes/index.html'
  })
  .otherwise({
    redirectTo: '/'
  });
});
