(function(){
	angular.module('lup1')
	.factory('fileManager', ['$http','$q', function ($http,$q) {
		return {
            'getFile': function (path) {
            	var defer = $q.defer();
            	$http.get(path).success(function(data) {
                    defer.resolve(data);
                });

                return defer.promise;
            }
        };
	}]);
})();