(function(){
	angular.module('lup1')
	.controller('BulletinController', [ '$http', '$routeParams', '$localStorage', function($http, $routeParams, $localStorage){
		var controller = this;
		controller.bulletin = [];
		controller.bulletin = "";
		controller.subjects ="";
		var student = $routeParams.student;
		var formation = $routeParams.formation;
		controller.annee = $routeParams.annee;
		controller.semestre = $routeParams.semestre;

		$http.get('/api/formations/'+formation).success(function(data){
			controller.formation = data;
			console.log(controller.formation);
		});
		$http.get('/api/formations/'+formation+'/annees/'+controller.annee+'/semestres/'+controller.semestre+'/bulletins/'+student).success(function(data){
			controller.bulletins = data;
			console.log(controller.bulletins);
		});
	}]);
}
)();
