'use strict';

angular.module('stockApp').factory('domainService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/StockControl/customer/';
    debugger;
    var factory = {
    		
    		fetchAllCustomers: fetchAllCustomers,
    		deleteCustomer: deleteCustomer,
    		createCustomer: createCustomer,
            updateCustomer: updateCustomer
    		
    };

    return factory;

    function fetchAllCustomers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Customer');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createCustomer(customer) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, customer)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating customer');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateCustomer(customer, idCustomer) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+idCustomer, customer)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating customer');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteCustomer(idCustomer) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+idCustomer)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Customer');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);
