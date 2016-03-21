(function(){
	angular.module('lup1')
	.controller('AuthenticationController', ['$scope', '$location', '$window', 'Authentication',function ($scope, $location, $window, authenticationFactory) {
		$scope.signin = function () {
			authenticationFactory.signin($scope.username, $scope.password)
			.then(function (result) {
				$location.path("/");
			}, function (error) {
				$window.alert("Invalid credentials");
				console.log(error);
			});
		};

		$scope.cancel = function () {
			$scope.username = "";
			$scope.password = "";
		};
		
		this.isConnected = function(){
			return authenticationFactory.isConnected();
		}
		
		this.getUserName = function(){
			return authenticationFactory.getName();
		}
		
		this.logout = function(){
			authenticationFactory.logout();
			$location.path("/login");
		}
	}]);
})();