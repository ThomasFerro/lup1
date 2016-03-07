app.controller("AuthenticationController", ["$scope", "$location", "$window", "Authentication",function ($scope, $location, $window, authenticationFactory) {
    $scope.member = null;
    $scope.signin = function () {
    	authenticationFactory.signin($scope.username, $scope.password)
            .then(function (result) {
                $scope.member = result;
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
}]);