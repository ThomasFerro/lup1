(function(){
	angular.module('lup1')
	.factory('Access', ['$q','jwtHelper', '$localStorage','Authentication',function ($q, jwtHelper, $localStorage, authenticationFactory) {		
		var Access = {
				OK:200,
				UNAUTHORIZED:401,
				FORBIDDEN:403,
				hasRoles: function(role) {
					var deferred = $q.defer();
					if(authenticationFactory.isConnected() && authenticationFactory.hasRole(role)){
						deferred.resolve(Access.OK);
					}else{
						deferred.reject(Access.UNAUTHORIZED);
					}
					return deferred.promise;
				},
				isAnonymous:function(){
					var deferred = $q.defer();
					if(!authenticationFactory.isConnected()){
						deferred.resolve(Access.OK);
					}else{
						deferred.reject(Access.FORBIDDEN);
					}
					return deferred.promise;
				},
				isAuthenticated:function(){
					var deferred = $q.defer();
					if(authenticationFactory.isConnected()){
						deferred.resolve(Access.OK);
					}else{
						deferred.reject(Access.UNAUTHORIZED);
					}
					return deferred.promise;
				}
		}
		return Access;
	}]);
})();