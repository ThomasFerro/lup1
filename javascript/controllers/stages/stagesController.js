(function() {
  angular.module("lup1").controller('StagesController', [ '$http', 'filterFilter', function($http, filterFilter) {
    var controller = this;
    controller.offres = [];
    controller.tab = 1;

    $http.get('json/stages.json').success(function(data) {
      controller.offres = data;
    });
  } ]);
}
)();
