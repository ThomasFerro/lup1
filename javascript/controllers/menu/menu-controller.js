(function(){
  angular.module('lup1')
  .controller('MenuController', function(){
    this.selectedMenuItem = 1;
    this.menuItems = menuItems;

    this.selectMenuItem = function(setItemIndex) {
      this.selectedMenuItem = setItem;
    };

    this.isSelectedMenuItem = function(checkItemIndex) {
      return this.selectedMenuItem === checkItemIndex;
    };

    this.imgSelectedMenuItem = function(index){
      if(this.isSelectedMenuItem(index)){
        return this.menuItems[index].img_active;
      }
      return this.menuItems[index].img_passive;
    };

    this.isHome = function(item) {
      return item.name === "home";
    };
  });

  var menuItems = [
    {
      index:0,
      name:"home",
      url:"home",
      img_active:"img/menu/home.png",
      img_passive:"img/menu/home-black.png"
    },
    {
      index:1,
      name:"gestion des notes",
      url:"notes",
      img_active:"img/menu/note.png",
      img_passive:"img/menu/note-black.png"
    },
    {
      index:2,
      name:"gestion des stages",
      url:"stages",
      img_active:"img/menu/stage.png",
      img_passive:"img/menu/stage-black.png"
    },
    {
      index:3,
      name:"gestion des absences",
      url:"absences",
      img_active:"img/menu/absence.png",
      img_passive:"img/menu/absence-black.png"
    },
    {
      index:4,
      name:"gestion des paramètres",
      url:"parametres",
      img_active:"img/menu/system.png",
      img_passive:"img/menu/system-black.png"
    }
  ];
})();
