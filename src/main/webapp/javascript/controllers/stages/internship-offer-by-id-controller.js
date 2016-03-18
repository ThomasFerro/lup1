(function() {
  var app = angular.module('lup1');
  app.controller('InternshipOfferByIdController', ["$http", function($http) {
    var controller = this;
    controller.stage = {};

    $http.get('models/stages/offres/id/internship-offer-by-id.json').success(function(data) {
      controller.stage = data;
      console.log(data);
    });
  }]);
})()
