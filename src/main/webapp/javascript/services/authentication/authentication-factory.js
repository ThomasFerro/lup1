(function(){
	angular.module('lup1')
	.factory('Authentication', ['$http','$q','jwtHelper', '$localStorage',function ($http, $q, jwtHelper, $localStorage) {
		var security;

		function signin(username, password) {
			var deferred = $q.defer();
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
				security = {
					token : token,
					member : jwtHelper.decodeToken(token)
				};
                $localStorage.lup1 = JSON.stringify(security);
				deferred.resolve(security);
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
				console.log($localStorage.lup1);
				// probleme ici ! 
				var token = $localStorage.lup1.token;
				console.log(token);
				return token && !jwtHelper.isTokenExpired(token);
			}
			console.log("c'est pas gagné !");
			return false;
		}
		
		function getName(){
			if(isConnected()){
				return $localStorage.lup1.member.iss
			}
		}
		
		function getRoles(){
			if(isConnected()){
				var roles = $localStorage.lup1.member.roles;
				return roles.roles[0];
			}
		}
		
		return {
			signin: signin,
			logout: logout,
			getName: getName,
			isConnected: isConnected
		};
	}]);
})();