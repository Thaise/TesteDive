angular.module("appModule").controller("MenuController",
		function($scope, $http, $log) {
			var vm = this;

			vm.menuAtivo = null;
			vm.menuItems = [ {
				menu : 'Home',
				link : '/home',
			}, {
				menu : 'Fichas',
				link : '/fichas',
			}, {
				menu : 'Animais',
				link : '/animais',
			} ];

			vm.menuAtivo = vm.menuItems[0];

			vm.setAtivo = function setAtivo(menuItem) {
				vm.menuAtivo = menuItem;
			}

		});