(function() {
	angular.module('lup1')
	.filter('nospace', function () {
		return function (value) {
			return (!value) ? '' : value.replace(/ /g, '');
		};
	});
}
)();