angular
		.module("FichaApp")
		.controller(
				"FichaReportController",
				function($scope, $log, $http, urlBase) {
					var vm = this;

					vm.tipoMensagem = "";
					vm.mensagemErro;

					vm.fichas = [];
					vm.fichaFiltro = null;
					vm.fichaExcluir = null;

					vm.$onInit = function() {
						vm.getFichas();
					}

					vm.getFichas = function getFichas() {
						$http({
							method : "POST",
							url : urlBase + "ficha/busca/filtro",
							data : vm.fichaFiltro
						}).then(function mySuccess(response) {
							vm.fichas = response.data;
						}, function myError(response) {
							vm.tipoMensagem = "danger";
							$log.log("Erro ao buscar: " + response.statusText);
							vm.mensagemErro = "Erro ao buscar Fichas";
						});
					}

					vm.limpar = function limpar() {
						vm.fichaFiltro = {};
						vm.getFichas();
					}

					vm.setaParaExcluir = function setaParaExcluir(f) {
						vm.fichaExcluir = f;
					}

					vm.excluir = function excluir() {
						$http({
							method : "POST",
							url : urlBase + "ficha/remove",
							data : vm.fichaExcluir
						})
								.then(
										function mySuccess(response) {
											vm.tipoMensagem = "success";
											vm.mensagemErro = "Ficha removida com sucesso";
											vm.getFichas();
											document.body.scrollTop = document.documentElement.scrollTop = 0;
										},
										function myError(response) {
											vm.tipoMensagem = "danger";
											$log.log("Erro ao remover: "
													+ response.statusText);
											vm.mensagemErro = "Erro ao revomer Ficha";
										});
					}

				});