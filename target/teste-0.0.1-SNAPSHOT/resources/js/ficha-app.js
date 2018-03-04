var app = angular.module("FichaApp", []);

app.controller("FichaCadastroController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.animais = [];
	$scope.animaisEscolhidos = [];
	$scope.ficha = {};
	
	this.$onInit = function() {
		getAnimais();
	}

	function getAnimais() {
		$http({
			method : "POST",
			url : $scope.urlBase + "animal/busca/todos"
		}).then(function mySuccess(response) {
			$scope.animais = response.data;
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}

	function salvar() {
		var ehCadastro = true;
		var servico = "cadastra";

		if ($scope.ficha.id) {
			servico = "atualiza";
			ehCadastro = false;
		}

		if ($scope.animaisEscolhidos) {
			$scope.ficha.animais = $scope.animaisEscolhidos;
		}

		$http({
			method : "POST",
			url : $scope.urlBase + "ficha/cadastra",
			data : $scope.ficha
		}).then(function mySuccess(response) {
			$scope.tipoMensagem = "success";
			$scope.mensagemErro = "Sucesso ao inserir ficha";
			if (ehCadastro) {
				$scope.ficha = {};
				$scope.animaisEscolhidos = [];
			}
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}
});

app.controller("FichaReportController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.fichas = [];
	$scope.fichaFiltro = {};

	function getFichas() {
		$http({
			method : "POST",
			url : $scope.urlBase + "fichas/busca/filtro",
			data : $scope.fichaFiltro
		}).then(function mySuccess(response) {
			$scope.animais = response.data;
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}

});

app.controller("AnimalCadastroController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.animal = {};

	function salvar() {
		var ehCadastro = true;
		var servico = "cadastra";

		if ($scope.animal.id) {
			servico = "atualiza";
			ehCadastro = false;
		}

		$http({
			method : "POST",
			url : $scope.urlBase + "animal/cadastra",
			data : $scope.animal
		}).then(function mySuccess(response) {
			$scope.tipoMensagem = "success";
			$scope.mensagemErro = "Sucesso ao inserir animal";
			if (ehCadastro) {
				$scope.animal = {};
			}
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}
});

app.controller("AnimalReportController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.animais = [];

	function getFichas() {
		$http({
			method : "POST",
			url : $scope.urlBase + "animal/busca/todos",
		}).then(function mySuccess(response) {
			$scope.animais = response.data;
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}

});