(function() {
    angular.module('lup1')
    .directive('notation', function() {
      return {
        replace: true,
        templateUrl: 'templates/directives/notations/tr-notation.html',
		link: function(scope, element, attrs) {
	        angular.element(document).ready(function() {
	        	var oldMark;
	        	element.on("click", '.edit-mark', function () {
	        		var button = $(this);
	        		if(!button.parent().children('.undo-edit').length){
	        		console.log($(this));
	        		var tr = button.parent().parent();
	        		var mark = tr.children('td.mark-value');
	        		oldMark = mark.text();
	        		mark.text("");
	        		tr.children('td.mark-value').append('<input type="number" class="form-control" min="0" max="20" value="'+oldMark+'"/>');
	        		button.after('<a class="btn btn-success btn-sm undo-edit"><span class="glyphicon fa fa-undo"></span></a>')
	        		}
	        	});
	        	
	        	element.on("click", '.undo-edit', function () {
	        		var button = $(this);
	        		var tr = button.parent().parent();
	        		var mark = tr.children('td.mark-value');
	        		mark.text("");
	        		tr.children('td.mark-value').append(oldMark);
	        		button.remove();
	        	});
			});
		}
      };
    });
}
)();
