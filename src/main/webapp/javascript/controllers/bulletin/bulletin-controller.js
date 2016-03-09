(function(){
  angular.module('lup1')
  .controller('BulletinController', [ '$http', '$routeParams', '$localStorage', function($http, $routeParams, $localStorage){
    this.bulletin = [];
    var student = $routeParams.student;
    var formation = $routeParams.formation;
    var annee = $routeParams.annee;
//    $http.get('/api/formations/'+formation+'/annees/'+annee+'/bulletins/'+student).success(function(data){
//     this.bulletin = data;
//    });
    $http({
		method: "GET",
		url: '/api/formations/'+formation+'/annees/'+annee+'/bulletins/'+student,
		headers: { 'Authorization': $localStorage.lup1 }
    }).then(function successCallback(response) {
    	
    	console.log(response);
    	console.log(response.data);
      }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
      });
  }]);
}
)();
