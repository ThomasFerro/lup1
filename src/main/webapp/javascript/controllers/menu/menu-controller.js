(function(){
	angular.module('lup1')
	.controller('MenuController', ['$http', 'Authentication',function($http, authenticationFactory){
		var controller = this;

		$http.get('models/menus/left-menu.json').success(function(data){
			controller.menuItems = data;
		});

		controller.selectedMenuItem = 0;

		controller.setSelectedMenuItem = function(setItemIndex){
			controller.selectedMenuItem = setItemIndex;
		};

		controller.isSelectedMenuItem = function(checkItemIndex){
			return controller.selectedMenuItem === checkItemIndex;
		};

		controller.isHome = function(item){
			return item.name === "home";
		};

		controller.getHref = function(item){
			if(item.name === "notes"){
				if(authenticationFactory.hasRole('etudiant')){
					item.url="formations/1/annees/2015-2016/semestres/5/bulletins/"+authenticationFactory.getName();
				}
			};
			return item.url;
		};

	}]);
})();
