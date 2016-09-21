// create the module and name it stockApp
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
		controller : 'UserController'
	})

	// route for the contact page
	.when('/contact', {
		templateUrl : 'pages/contact.html',
		controller : 'contactController'
	});
});

// create the controller and inject Angular's $scope
stockApp.controller('mainController', function($scope) {
	// create a message to display in our view
	$scope.message = 'Everyone come and see how good I look!';
});

stockApp.controller('contactController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

stockApp.controller('UserController', [ '$scope', 'UserService' ]);
