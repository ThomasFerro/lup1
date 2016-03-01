(function(){
  angular.module('lup1')
  .controller('MenuController', [ "$http", function($http){
    var controller = this;
    var menuItems = [];
    $http.get('models/menus/left-menu.json').success(function(data){
     controller.menuItems = data;
    });

    this.selectedMenuItem = 0;
    this.menuItems = menuItems;

    this.setSelectedMenuItem = function(setItemIndex){
      this.selectedMenuItem = setItemIndex;
    };

    this.isSelectedMenuItem = function(checkItemIndex){
      return this.selectedMenuItem === checkItemIndex;
    };

    this.imgSelectedMenuItem = function(index){
      if(this.isSelectedMenuItem(index)){
        return this.menuItems[index].img_active;
      }
      return this.menuItems[index].img_passive;
    };

    this.isHome = function(item){
      return item.name === "home";
    };

  }]);
}
)();
