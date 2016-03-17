(function() {
  var app = angular.module('lup1');
  app.controller('InternshipOffersController', ["$http", function($http) {
    var controller = this;
    controller.stages = [];

    $http.get('models/stages/offres/internship-offers.json').success(function(data) {
      controller.stages = data;
    });
  }])
})();
