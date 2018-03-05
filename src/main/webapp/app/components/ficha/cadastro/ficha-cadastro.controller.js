angular
		.module("FichaApp")
		.controller(
				"FichaCadastroController",
				function($scope, $http, $log, $stateParams, urlBase) {
					var vm = this;

					vm.tipoMensagem = "";
					vm.mensagemErro;

					vm.animais = [ {} ]
					vm.animaisSelecionados = [ {} ]
					vm.ficha = null;

					vm.$onInit = function() {
						inicializaFicha();
						getAnimais();

						var id = $stateParams.id;
						if (id) {
							vm.ficha = buscaPeloId(id);
						}
					}

					function inicializaFicha() {
						vm.ficha = {
							flAtivo : 1,
							animais : [ {} ]
						};
					}

					function getAnimais() {
						$http({
							method : "POST",
							url : urlBase + "animal/busca/todos"
						})
								.then(
										function mySuccess(response) {
											vm.animais = response.data;
											vm.animais = angular
													.fromJson(vm.animais);
										},
										function myError(response) {
											vm.tipoMensagem = "danger";
											$log.log("Erro ao buscar animais: "
													+ response.statusText);
											vm.mensagemErro = "Erro ao buscar lista de Animais";
										});
					}

					function buscaPeloId(id) {
						$http({
							method : "POST",
							url : urlBase + "ficha/buscaPeloId/" + id,
						}).then(
								function mySuccess(response) {
									vm.ficha = response.data;
									vm.animaisSelecionados = angular
											.fromJson(vm.ficha.animais)
								},
								function myError(response) {
									vm.tipoMensagem = "danger";
									$log.log("Erro ao buscar ficha: "
											+ response.statusText);
									vm.mensagemErro = "Erro ao buscar Ficha";
								});
					}

					vm.salvar = function salvar() {

						vm.ficha.animais = vm.animaisSelecionados;

						var ehCadastro = true;
						var servico = "cadastra";

						if (vm.ficha.idFicha) {
							servico = "atualiza";
							ehCadastro = false;
						}

						$http({
							method : "POST",
							url : urlBase + "ficha/" + servico,
							data : vm.ficha
						}).then(
								function mySuccess(response) {
									vm.tipoMensagem = "success";
									vm.mensagemErro = "Sucesso ao "
											+ (ehCadastro ? "inserir"
													: "atualizar") + " ficha";
								},
								function myError(response) {
									vm.tipoMensagem = "danger";
									$log.log("Erro ao salvar: "
											+ response.statusText);
									vm.mensagemErro = "Erro ao salvar Ficha";
								});

						document.body.scrollTop = document.documentElement.scrollTop = 0;
					}

				});