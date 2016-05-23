(function() {
	angular.module('lup1')
	.filter('noquote', function () {
		return function (value) {
			return (!value) ? '' : value.replace(/'/g, '');
		};
	});
}
)();
