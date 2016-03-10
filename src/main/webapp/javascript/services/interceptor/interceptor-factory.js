(function(){
	angular.module('lup1')
	.factory('httpInterceptor', ['$q', '$location', '$localStorage', '$rootScope', 'Access', function ($q, $location, $localStorage, $rootScope, accessFactory) {
		return {
            'request': function (config) {
                config.headers = config.headers;
                if ($localStorage.lup1) {
                	var token = $localStorage.lup1.replace(/"/g, "");
                    config.headers.Authorization = token;
                }
                return config;
            },
            'responseError': function (response) {
                if (response.status === accessFactory.FORBIDDEN || response.status === accessFactory.UNAUTHORIZED) {
                	if($localStorage.lup1){
                        delete $localStorage.lup1;                		
                	}
                    $rootScope.$broadcast('unauthorized');
                    $location.path('/login');
                }
                return $q.reject(response);
            }
        };
	}]);
})();