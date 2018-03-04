angular
		.module("AnimalApp")
		.controller(
				"AnimalCadastroController",
				function($scope, $http, $log, urlBase) {
					var vm = this;

					vm.tipoMensagem = "";
					vm.mensagemErro;

					vm.animal = null;

					vm.$onInit = function() {
						inicializaAnimal();

						var url = new URL(window.location.href);
						var id = url.searchParams.get("id");
						if (id) {
							vm.animal = buscaPeloId(id);
						}
					}

					function inicializaAnimal() {
						vm.animal = {
							flAtivo : 1,
						};
					}

					vm.voltarAoReport = function voltarAoReport() {
						window.location = "animal-report.html";
					}

					function buscaPeloId(id) {
						$http({
							method : "POST",
							url : urlBase + "animal/buscaPeloId/" + id,
						}).then(
								function mySuccess(response) {
									vm.animal = response.data;
								},
								function myError(response) {
									vm.tipoMensagem = "danger";
									$log.log("Erro ao buscar animal: "
											+ response.statusText);
									vm.mensagemErro = "Erro ao buscar Animal";
								});
					}

					vm.salvar = function salvar() {

						var ehCadastro = true;
						var servico = "cadastra";

						if (vm.animal.idAnimal) {
							servico = "atualiza";
							ehCadastro = false;
						}

						$http({
							method : "POST",
							url : urlBase + "animal/" + servico,
							data : vm.animal
						}).then(
								function mySuccess(response) {
									vm.tipoMensagem = "success";
									vm.mensagemErro = "Sucesso ao "
											+ (ehCadastro ? "inserir"
													: "atualizar") + " animal";
								},
								function myError(response) {
									vm.tipoMensagem = "danger";
									$log.log("Erro ao salvar: "
											+ response.statusText);
									vm.mensagemErro = "Erro ao salvar Animal";
								});

						document.body.scrollTop = document.documentElement.scrollTop = 0;

					}

				});