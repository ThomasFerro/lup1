(function() {
	angular.module('lup1')
	.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
		.when('/login/', {
			templateUrl: 'templates/pages/login/index.html',
			controller: 'AuthenticationController'
		})
		.when('/formations/', {
			templateUrl: 'templates/pages/formations/index.html'
		})
		.when('/formations/:formation/:date/', {
			templateUrl:'templates/pages/formations/evaluations/index.html'
		})
		.when('/formations/bulletin', {
			templateUrl: 'templates/pages/formations/bulletin/index.html'
		})
		.when('/stages/', {
			templateUrl: 'templates/pages/stages/index.html'
		})
		.when('/absences/', {
			templateUrl: 'templates/pages/absences/index.html'
		})
		.when('/parametres/', {
			templateUrl: 'templates/pages/parametres/index.html'
		})
		.when('/home/', {
			templateUrl: 'templates/pages/home/index.html',
		})
		.when('/', {
			redirectTo: '/home'
		})
		.otherwise({
			redirectTo: '/'
		});
		
//		$httpProvider.interceptors.push(['$q', '$location', '$localStorage' ,'Authentication', function ($q, $location, $localStorage, authenticationFactory) {
//            return {
//                'request': function (config) {
//                    config.headers = config.headers || {};
//                    if ($localStorage.lup1) {
//                        config.headers.Authorization = $localStorage.lup1;
//                    }
//                    if(!authenticationFactory.isConnected()){
//                    	$location.path("/login");
//                    }
//                    return config;
//                },
//                'responseError': function (response) {
//                    if (response.status === 401 || response.status === 403) {
//                        delete $localStorage.lup1;
//                        $location.path('/login');
//                    }
//                    return $q.reject(response);
//                }
//            };
//        }]);
	}]);
}
)();
