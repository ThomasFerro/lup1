(function() {
  var app = angular.module('lup1');
  app.controller('ModuleController', function() {
    this.active = 'module';

    this.setActive = function (buttonName) {
      this.active = buttonName;
    };
    this.isActive = function (buttonName) {
      return this.active === buttonName;
    };
  });
})();
