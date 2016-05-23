(function() {
	angular.module('lup1')
	.directive('leftMenu', function() {
		return {
			restrict: 'E',
			templateUrl: 'templates/directives/menu/left-menu.html',
			controller:'AuthenticationController',
			controllerAs: 'authCtrl',
			link: function(scope, element, attrs) {
				angular.element(document).ready(function() {
					var height = $(window).height();
					element.children(".left-menu").css('height', height);
				});

				$(window).resize(function(){
					var height = $(window).height();
					element.children(".left-menu").css('height', height);
				});
			}
		};
	});
}
)();
