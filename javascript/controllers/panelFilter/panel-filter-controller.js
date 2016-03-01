(function(){
  angular.module('lup1')
  .controller('PanelInternshipFilterController', [ "$http", function($http){
    var controller = this;
    controller.filters = [];
    controller.filterSelected;

    this.selectFilter = function(index){
      controller.filterSelected = controller.filters[index];
    }

    $http.get('models/filters/internship-filter.json').success(function(data) {
     controller.filters = data;
    });

  }])
  ;
})();
