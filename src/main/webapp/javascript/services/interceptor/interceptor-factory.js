(function(){
	angular.module('lup1')
	.factory('httpInterceptor', ['$q', '$location', '$localStorage', '$rootScope', function ($q, $location, $localStorage, $rootScope) {
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
                if (response.status === 401 || response.status === 403) {
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