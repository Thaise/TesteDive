angular.module("GeralModule").directive('section', function() {
	return {
		transclude : true,
		scope : {
			title : '@'
		},
		templateUrl : 'template.html'
	};
})