var app = angular.module('myAppRouter', [ 'ui.router' ]);

app.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/home');

	$stateProvider

	.state('home', {
		url : '/home',
		templateUrl : 'app/components/home/home.html'
	})

	.state('Fichas', {
		url : '/fichas',
		templateUrl : 'app/components/ficha/report/ficha-report.html'
	})

	.state('CadastroFicha', {
		url : '/ficha/cadastro',
		templateUrl : 'app/components/ficha/cadastro/ficha-cadastro.html'
	})

	.state('AtualizacaoFicha', {
		url : '/ficha/cadastro/:id',
		templateUrl : 'app/components/ficha/cadastro/ficha-cadastro.html'
	})

	.state('Animal', {
		url : '/animais',
		templateUrl : 'app/components/animal/report/animal-report.html'
	})

	.state('CadastroAnimal', {
		url : '/animal/cadastro',
		templateUrl : 'app/components/animal/cadastro/animal-cadastro.html'
	})
	
		.state('AtualizacaoAnimal', {
		url : '/animal/cadastro/:id',
		templateUrl : 'app/components/animal/cadastro/animal-cadastro.html'
	})
});