(function(){
	angular.module('lup1')
	.controller('PanelMenuController', [ 'fileManager', function(fileManagerFactory){
		var controller = this;
		controller.selectPanel = function(folder,filename){
			var path = '/models/panels/'+folder+'/'+filename+'.json';
			fileManagerFactory.getFile(path).then(function(data) {
				controller.panel = data;
			});
		};
	}]);
})();
