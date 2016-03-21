(function(){
	angular.module('lup1')
	.factory('student', ['$http','$q', function ($http,$q) {
		return {
			'getAll': function () {
				var defer = $q.defer();
				$http.get('/api/etudiants/')
				.success(function(data){
					defer.resolve(data);
				})
				.error(function(data){
					defer.reject(data);
				});
				return defer.promise;
			},
			'getById': function (formation, annee, id) {
				var defer = $q.defer();
				$http.get('/api//formations/'+formation+'/annees/'+annee+'/etudiants/'+id)
				.success(function(data){
					defer.resolve(data);
				})
				.error(function(data){
					defer.reject(data);
				});
				return defer.promise;
			},
			'getByPromotion': function (formation,annee) {
				var defer = $q.defer();
				$http.get('/api/formations/'+formation+'/annees/'+annee+'/etudiants')
				.success(function(data){
					defer.resolve(data);
				})
				.error(function(data){
					defer.reject(data);
				});
				return defer.promise;
			}
		};
	}]);
})();
