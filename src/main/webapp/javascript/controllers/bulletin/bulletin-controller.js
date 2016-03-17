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
//		var token = $localStorage.lup1;
//		console.log(token);
//		$http({	
//	        method: 'GET',
//	        url: 'http://meleze03:8080/api/formations/'+formation+'/annees/'+annee+'/semestres/'+semestre+'/bulletins/'+student,
//	        headers: {'Authorization': JSON.stringify(token)}
//	     }).success(function(data){
//	    	 controller.bulletins = data;
//			console.log(controller.bulletins);
//	    }).error(function(){
//	        alert("error");
//	    });
	}]);
}
)();
