(function() {
  angular.module("lup1").controller('OffresController',function($filter) {
    this.tab = 1;
    this.setSelected = function(setTab) {
      this.tab = setTab;
    };

    this.isSelected = function(checkTab) {
      return this.tab === checkTab;
    }
  });
}
)();
