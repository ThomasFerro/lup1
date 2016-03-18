(function(){
	angular.module('lup1')
	.controller('PromotionController', [ '$http', function($http){
		var controller = this;
		controller.data = "";

		$http.get('/models/tmp/promotion.json').success(function(data){
			controller.data = data;
			console.log(controller.data);
		});
	}]);
})();
