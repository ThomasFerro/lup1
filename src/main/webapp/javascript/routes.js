(function() {
	angular.module('lup1')
	.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
		.when('/login/', {
			templateUrl: 'templates/pages/login/index.html',
			controller: 'AuthenticationController'
		})
		.when('/formations/', {
			templateUrl: 'templates/pages/formations/index.html',
			resolve: {
			      access: ["Access", function(Access) { return Access.hasRoles("responsable_formation"); }],
			}
		})
		.when('/formations/:formation/:date/', {
			templateUrl:'templates/pages/formations/evaluations/index.html',
			resolve: {
			      access: ["Access", function(Access) { return Access.hasRoles("responsable_formation"); }],
			}
		})
		.when('/formations/:formation/annees/:annee/semestres/:semestre/bulletins/:student', {
			templateUrl: 'templates/pages/formations/bulletin/index.html',
			controller : 'BulletinController',
			controllerAs : 'bulletinCtrl',
			resolve: {
			      access: ["Access", function(Access) { return Access.isAuthenticated(); }],
			}
		})
		.when('/stages/', {
			templateUrl: 'templates/pages/stages/index.html'
		})
		.when('/absences/', {
			templateUrl: 'templates/pages/absences/index.html'
		})
		.when('/parametres/', {
			templateUrl: 'templates/pages/parametres/index.html',
			resolve: {
			      access: ["Access", function(Access) { return Access.hasRoles("responsable_formation"); }],
			}
		})
		.when('/home/', {
			templateUrl: 'templates/pages/home/index.html',
			resolve: {
			      access: ["Access", function(Access) { return Access.isAuthenticated(); }],
			}
		})
		.when('/', {
			redirectTo: '/home',
			resolve: {
			      access: ["Access", function(Access) { return Access.isAuthenticated(); }],
			}
		})
		.otherwise({
			redirectTo: '/'
		});

		$httpProvider.interceptors.push('httpInterceptor');
	}]);
}
)();
