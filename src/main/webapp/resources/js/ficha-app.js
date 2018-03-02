var app = angular.module("FichaApp", []);

app.controller("FichaController", function($scope) {
	$scope.animais = [
		{nome:"Cobra"}, 
		{nome:"Rato"}, 
		{nome:"Aranha"}, 
		{nome:"Cachorro"}
	];
	$scope.animal = {};
	$scope.ficha = {
			flAtivo:1,
			observacao:"obs"
	};
	
	/*function salvar(){
	    $http({
	        method : "POST",
	        url : "http://localhost:8080/testedive/rest/ficha/cadastra"
	    }).then(function mySuccess(response) {
	        $scope.myWelcome = response.data;
	    }, function myError(response) {
	        $scope.myWelcome = response.statusText;
	    });
	}*/
});