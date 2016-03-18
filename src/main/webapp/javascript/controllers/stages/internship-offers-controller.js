(function() {
  var app = angular.module('lup1');
  app.controller('InternshipOffersController', ["$http", function($http) {
    var controller = this;
    controller.stages = [];

    /*$http.get('models/stages/offres/internship-offers.json').success(function(data) {
      controller.stages = data;
    });*/
    $http.get('/api/formations/1/annees/2015-2016/stages').success(function(data) {
      controller.stages = data;
    });
  }])
})();
