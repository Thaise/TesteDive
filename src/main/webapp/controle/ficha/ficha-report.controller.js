angular.module("FichaApp").controller("FichaReportController",
		function($scope, $http, urlBase) {
			var vm = this;
			
			vm.tipoMensagem = "";
			vm.mensagemErro;

			vm.fichas = [];
			vm.fichaFiltro = {};

			vm.$onInit = function() {
				getFichas();
			}

			function getFichas() {
				$http({
					method : "POST",
					url : urlBase + "ficha/busca/filtro",
					data : vm.fichaFiltro
				}).then(function mySuccess(response) {
					vm.fichas = response.data;
				}, function myError(response) {
					vm.tipoMensagem = "danger";
					vm.mensagemErro = response.statusText;
				});
			}

		});