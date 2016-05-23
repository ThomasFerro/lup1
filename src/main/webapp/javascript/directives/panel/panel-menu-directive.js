(function() {
    angular.module('lup1')
    .directive('panelMenu', function() {
      return {
        restrict: 'E',
        templateUrl: 'templates/directives/panel/panel-menu.html',
  			link: function(scope, element, attrs) {
  		        angular.element(document).ready(function() {
            console.log(element.children('.panel-menu-wrapper'))
            var height = $(window).height();
            element.children('.panel-menu-wrapper').css('height', height);
  				});

          $(window).resize(function(){
            var height = $(window).height();
            element.children('.panel-menu-wrapper').css('height', height);
          });
  			}
  		};
  	});
  }
  )();
