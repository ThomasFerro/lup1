(function(){
	angular.module('lup1')
	.controller('FormationsController', [ '$http', function($http){
		var controller = this;
		controller.formations = [];

		$http.get('/api/formations/').success(function(data){
			controller.formations = data;
			console.log(controller.formations);
		});
	}]);
})();
