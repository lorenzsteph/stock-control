var stockApp = angular.module('stockApp', [ 'ngRoute' ]);

// configure our routes
stockApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	// route for the about page
	.when('/domain', {
		templateUrl : 'pages/domain.html',
		controller : 'domainController'
	})

	// route for the contact page
	.when('/contact', {
		templateUrl : 'pages/contact.html',
		controller : 'UserController'
	});
});

stockApp.controller('mainController', function($scope) {
	$scope.message = 'Everyone come and see how good I look!';
});

