(function(){
	angular.module('lup1')
	.controller('BulletinController', [ '$http', '$routeParams', '$localStorage', function($http, $routeParams, $localStorage){
		var controller = this;
		controller.bulletin = [];
		controller.bulletin = "";
		controller.subjects ="";
		var student = $routeParams.student;
		var formation = $routeParams.formation;
		var annee = $routeParams.annee;
		var semestre = $routeParams.semestre;
		$http.get('/api/formations/'+formation+'/annees/'+annee+'/semestres/'+semestre+'/bulletins/'+student).success(function(data){
			controller.bulletins = data;
			console.log(controller.bulletins);
		});
	}]);
}
)();
