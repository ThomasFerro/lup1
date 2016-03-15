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
    .when('/stages/ajouter/offre', {
      templateUrl: 'templates/pages/stages/ajouter/offre/index.html'
    })
    .when('/stages/ajouter/entreprise', {
      templateUrl: 'templates/pages/stages/ajouter/entreprise/index.html'
    })
    .when('/stages/offre', {
      templateUrl: 'templates/pages/stages/offre/index.html'
    })
    .when('/stages/offre/:id', {
      templateUrl: 'templates/pages/stages/offre/id/index.html'
    })
    .when('/stages/suivi/enseignant', {
      templateUrl: 'templates/pages/stages/suivi/enseignant/index.html'
    })
    .when('/stages/suivi/etudiant', {
      templateUrl: 'templates/pages/stages/suivi/etudiant/index.html'
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
