angular.module("FichaApp").directive('section', function() {
	return {
		transclude : true,
		scope : {
			title : '@'
		},
		templateUrl : 'template.html'
	};
})