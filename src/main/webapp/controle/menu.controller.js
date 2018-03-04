angular.module("GeralModule").controller("MenuController",
		function($scope, $http, $log, urlBase) {
			var vm = this;

			vm.menuAtivo = null;
			vm.menuItems = [ {
				menu : 'Home',
				link : 'index.html',
				secao : '',
			}, {
				menu : 'Fichas',
				link : 'ficha-report.html',
				secao : 'fichas'
			}, {
				menu : 'Animais',
				link : 'animal-report.html',
				secao : 'animais'
			} ];

			var path = window.location.pathname;

			for (var i = 0; i < vm.menuItems.length; i++) {
				if (path.includes(vm.menuItems[i].secao) || path.includes(vm.menuItems[i].link)) {
					setAtivo(vm.menuItems[i]);
				}
			}

			function setAtivo(menuItem) {
				vm.menuAtivo = menuItem;
			}

		});