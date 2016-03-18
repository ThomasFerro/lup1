(function(){
	angular.module('lup1')
	.controller('FormationsController', [ '$http', function($http){
		var controller = this;
		controller.formations = [];

		$http.get('/models/tmp/formations.json').success(function(data){
			controller.formations = data;
			console.log(controller.formations);
		});
	}]);
})();
