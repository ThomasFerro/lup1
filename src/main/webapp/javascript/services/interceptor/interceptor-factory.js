(function(){
	angular.module('lup1')
	.factory('httpInterceptor', ['$q', '$location', '$localStorage', '$rootScope', function ($q, $location, $localStorage, $rootScope) {
		return {
            'request': function (config) {
                config.headers = config.headers;
                if ($localStorage.lup1) {
                    config.headers.Authorization = $localStorage.lup1;
                }
                return config;
            },
            'responseError': function (response) {
                if (response.status === 401 || response.status === 403) {
                	if($localStorage.lup1){
                        delete $localStorage.lup1;                		
                	}
                    $rootScope.$broadcast('unauthorized');
                }
                return $q.reject(response);
            }
        };
	}]);
})();