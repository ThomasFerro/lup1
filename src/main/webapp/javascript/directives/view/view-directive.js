(function() {
    angular.module('lup1')
    .directive('ngView', function() {
      return {
        restrict: 'AE',
		link: function(scope, element, attrs) {
	        angular.element(document).ready(function() {
				var height = $(window).height();
				element.children(".login").css('height', height);
			});
		}
      };
    });
}
)();
