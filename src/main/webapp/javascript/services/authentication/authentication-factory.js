(function(){
	angular.module('lup1')
	.factory('Authentication', ['$q','jwtHelper', '$localStorage', '$injector',function ($q, jwtHelper, $localStorage, $injector) {
		function signin(username, password) {
			var deferred = $q.defer();
			var $http = $injector.get('$http');
			$http({
				method: "POST",
				url: "/api/authentication",
				headers: { 'Content-Type': 'application/json' },
				data: {
					login: username,
					password: password
				}
			}).then(function (result) {
				var token = result.headers().authorization;
                $localStorage.lup1 = JSON.stringify(token);
				deferred.resolve(token);
			}, function (error) {
				deferred.reject(error);
			});
			return deferred.promise;
		}

		function logout(){
			$localStorage.$reset();
		}
		
		function isConnected(){
			if($localStorage.lup1){
				var token = $localStorage.lup1;
				return token && !jwtHelper.isTokenExpired(token);
			}
			return false;
		}
		
		function getName(){
			if(isConnected()){
				return jwtHelper.decodeToken($localStorage.lup1).sub;
			}
		}
		
		function getRoles(){
			if(isConnected()){
				return jwtHelper.decodeToken($localStorage.lup1).roles;
			}
		}
		
		function hasRole(role){
			var roles = getRoles();
			 for (var i = 0; i < roles.length; i++) {
				if(roles[i] === role){
					return true;
				}
			 }
			 return false;
		}
		
		return {
			signin: signin,
			logout: logout,
			getName: getName,
			isConnected: isConnected,
			getRoles: getRoles,
			hasRole: hasRole
		};
	}]);
})();