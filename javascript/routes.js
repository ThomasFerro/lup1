(function() {
  angular.module('lup1')
  .config(function($routeProvider){
    $routeProvider
    .when('/formations', {
      templateUrl: 'templates/pages/formations/index.html'
    })
    .when('/formations/bulletin', {
      templateUrl: 'templates/pages/formations/bulletin/index.html'
    })
    .when('/stages', {
      templateUrl: 'templates/pages/stages/index.html'
    })
    .when('/absences', {
      templateUrl: 'templates/pages/absences/index.html'
    })
    .when('/parametres', {
      templateUrl: 'templates/pages/parametres/index.html'
    })
    .when('/home', {
      templateUrl: 'templates/pages/home/index.html'
    })
    .when('/', {
      redirectTo: '/home'
    })
    .otherwise({
      redirectTo: '/'
    });
  });
}
)();
