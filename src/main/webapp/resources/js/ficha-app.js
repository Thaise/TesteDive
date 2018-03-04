var app = angular.module("FichaApp", []);

app.service("FichaDataService", function() {
	var fichaAtualizacao = {};
});

app.directive('section', function() {
	return {
		restrict : 'E',
		transclude : true,
		scope : {
			title : '@'
		},
		templateUrl : 'template.html'
	};
})

app.controller("FichaReportController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.fichas = [];
	$scope.fichaFiltro = {};

	this.$onInit = function() {
		getFichas();
	}

	function getFichas() {
		$http({
			method : "POST",
			url : $scope.urlBase + "ficha/busca/filtro",
			data : $scope.fichaFiltro
		}).then(function mySuccess(response) {
			$scope.fichas = response.data;
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}

});

app.controller("FichaCadastroController", function($scope, $http) {

	$scope.urlBase = "http://localhost:8080/testedive/rest/";
	$scope.tipoMensagem = "";
	$scope.mensagemErro;

	$scope.animais = [ {} ]
	$scope.animaisSelecionados = [ {} ]
	$scope.ficha = null;

	this.$onInit = function() {
		inicializaFicha();
		getAnimais();

		var url = new URL(window.location.href);
		var id = url.searchParams.get("id");
		if (id) {
			$scope.ficha = buscaPeloId(id);
		}
	}

	function inicializaFicha() {
		$scope.ficha = {
			flAtivo : 1,
			animais : [ {} ]
		};
	}
	
	function voltarAoReport() {
		window.location = "index.html";
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

	function buscaPeloId(id) {
		$http({
			method : "POST",
			url : $scope.urlBase + "ficha/buscaPeloId/" + id,
		}).then(function mySuccess(response) {
			$scope.ficha = response.data;
			$scope.animaisSelecionados = angular.fromJson($scope.ficha.animais)
		}, function myError(response) {
			$scope.tipoMensagem = "danger";
			$scope.mensagemErro = response.statusText;
		});
	}

	$scope.salvar = function salvar() {

		for (var i = 0; i < $scope.animaisSelecionados.length; i++) {
			$scope.ficha.animais[i] = angular
					.fromJson($scope.animaisSelecionados[i]);
		}

		var ehCadastro = true;
		var servico = "cadastra";

		if ($scope.ficha.idFicha) {
			servico = "atualiza";
			ehCadastro = false;
		}

		$http({
			method : "POST",
			url : $scope.urlBase + "ficha/" + servico,
			data : $scope.ficha
		})
				.then(
						function mySuccess(response) {
							$scope.tipoMensagem = "success";
							$scope.mensagemErro = "Sucesso ao "
									+ (ehCadastro ? "inserir" : "atualizar")
									+ " ficha";
							voltarAoReport();
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

	$scope.salvar = function salvar() {
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

	$scope.getAnimais = function getAnimais() {
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