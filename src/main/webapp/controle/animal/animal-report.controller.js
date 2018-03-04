angular.module("AnimalApp").controller("AnimalReportController",
		function($scope, $log, $http, urlBase) {
			var vm = this;

			vm.tipoMensagem = "";
			vm.mensagemErro;

			vm.animals = [];
			vm.animalFiltro = null;

			vm.$onInit = function() {
				vm.getAnimais();
			}

			vm.getAnimais = function getAnimais() {
				$http({
					method : "POST",
					url : urlBase + "animal/busca/filtro",
					data : vm.animalFiltro
				}).then(function mySuccess(response) {
					vm.animals = response.data;
				}, function myError(response) {
					vm.tipoMensagem = "danger";
					$log.log("Erro ao buscar: " + response.statusText);
					vm.mensagemErro = "Erro ao buscar Animals";
				});
			}

			vm.limpar = function limpar() {
				vm.animalFiltro = {};
				vm.getAnimais();
			}

		});