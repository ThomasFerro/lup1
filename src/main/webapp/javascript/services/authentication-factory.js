app.factory("Authentication", ["$http","$q","$window",function ($http, $q, $window) {
    var member;

    function signin(login, password) {
        var deferred = $q.defer();
        $http({
            method: "POST",
            url: "/api/authentication",
            data: {
                login: login,
                password: password
            }
        }).then(function (result) {
        		member ={
        				name : result.data.name,
        				token : result.data.Authorization
        		};
                $window.sessionStorage["lup1"] = JSON.stringify(member);
                deferred.resolve(member);
            }, function (error) {
                deferred.reject(error);
            });

        return deferred.promise;
    }

    function logout() {
        var deferred = $q.defer();

        $http({
            method: "POST",
            url: "/api/logout",
            headers: {
                "Authorization": member.token
            }
        }).then(function (result) {
            member = null;
            $window.sessionStorage["lup1"] = null;
            deferred.resolve(result);
        }, function (error) {
            deferred.reject(error);
        });

        return deferred.promise;
    }

    function getMember() {
        return member;
    }

    function init() {
        if ($window.sessionStorage["lup1"]) {
            members = JSON.parse($window.sessionStorage["lup1"]);
        }
    }
    
    init();

    return {
    	signin: signin,
        logout: logout,
        getMember: getMember
    };
}]);