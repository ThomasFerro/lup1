(function(){
	angular.module('lup1')
	.controller('StudentMarkerController', ['$routeParams','student', function($routeParams,studentFactory){
		var controller = this;
		controller.formation = $routeParams.formation;
		controller.annee = $routeParams.annee;
		studentFactory.getByPromotion(controller.formation,controller.annee).then(function(data) {
			controller.students = data;
			console.log(controller.students);
		});
	}]);
})();
