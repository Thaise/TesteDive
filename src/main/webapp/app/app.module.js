var app = angular.module('appModule', [ 'myAppRouter',
		'FichaApp', 'AnimalApp' ]);

app.value("urlBase", "http://localhost:8080/testedive/rest/")