angular.module("FichaApp").controller(
		"FichaCadastroController",
		function($scope, $http, urlBase) {
			var vm = this;

			vm.tipoMensagem = "";
			vm.mensagemErro;

			vm.animais = [ {} ]
			vm.animaisSelecionados = [ {} ]
			vm.ficha = null;

			vm.$onInit = function() {
				inicializaFicha();
				getAnimais();

				var url = new URL(window.location.href);
				var id = url.searchParams.get("id");
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

			function voltarAoReport() {
				window.location = "ficha-report.html";
			}

			function getAnimais() {
				$http({
					method : "POST",
					url : urlBase + "animal/busca/todos"
				}).then(function mySuccess(response) {
					vm.animais = response.data;
				}, function myError(response) {
					vm.tipoMensagem = "danger";
					vm.mensagemErro = response.statusText;
				});
			}

			function buscaPeloId(id) {
				$http({
					method : "POST",
					url : urlBase + "ficha/buscaPeloId/" + id,
				}).then(function mySuccess(response) {
					vm.ficha = response.data;
					vm.animaisSelecionados = angular.fromJson(vm.ficha.animais)
				}, function myError(response) {
					vm.tipoMensagem = "danger";
					vm.mensagemErro = response.statusText;
				});
			}

			vm.salvar = function salvar() {

				for (var i = 0; i < vm.animaisSelecionados.length; i++) {
					vm.ficha.animais[i] = angular
							.fromJson(vm.animaisSelecionados[i]);
				}

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
									+ (ehCadastro ? "inserir" : "atualizar")
									+ " ficha";
							voltarAoReport();
						}, function myError(response) {
							vm.tipoMensagem = "danger";
							vm.mensagemErro = response.statusText;
						});
			}

		});