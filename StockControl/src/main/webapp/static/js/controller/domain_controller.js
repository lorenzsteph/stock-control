'use strict';
angular.module('stockApp').controller('domainController', ['$scope', 'domainService', function($scope, DomainService) {
    var self = this;
    self.customer={idCustomer:null,descr:'',dateEndValidity:''};
    self.customers=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
    fetchAllCustomers();

    $scope.switchBool = function(value) {
       $scope[value] = !$scope[value];
    };
    
    function fetchAllCustomers(){
    	DomainService.fetchAllCustomers()
            .then(
            function(d) {
                self.customers = d;
            },
            function(errResponse){
                console.error('Error while fetching Customers');
                operationOnCustomerKo(errResponse);
            }
        );
    }
    
    function edit(idCustomer){
        console.log('id to be edited', idCustomer);
        for(var i = 0; i < self.customers.length; i++){
            if(self.customers[i].idCustomer === idCustomer) {
                self.customer = angular.copy(self.customers[i]);
                break;
            }
        }
    }

    function remove(idCustomer){
        console.log('id to be deleted', idCustomer);
        if(self.customer.idCustomer === idCustomer) {
            reset();
        }
        deleteCustomer(idCustomer);
    }
    
    function deleteCustomer(idCustomer){
    	DomainService.deleteCustomer(idCustomer)
            .then(
            operationOnCustomerOk,
            function(errResponse){
                console.error('Error while deleting Customer');
                operationOnCustomerKo(errResponse);
            }
        );
    }
    
    function submit() {
        if(self.customer.idCustomer===null){
            console.log('Saving New customer', self.customer);
            createCustomer(self.customer);
        }else{
            updateCustomer(self.customer, self.customer.idCustomer);
            console.log('customer updated with id ', self.customer.idCustomer);
        }
        reset();
    }
    
    function reset(){
        self.customer={idCustomer:null,descr:'',dateEndValidity:''};
        $scope.myForm.$setPristine(); //reset Form
    }

    function createCustomer(customer){
    	DomainService.createCustomer(customer)
            .then(
         		operationOnCustomerOk,
            function(errResponse){
         		 operationOnCustomerKo(errResponse);
            }
        );
    }
    
    function updateCustomer(customer, idCustomer){
    	DomainService.updateCustomer(customer, idCustomer)
            .then(
            operationOnCustomerOk,
            function(errResponse){
                console.error('Error while updating Customer');
                operationOnCustomerKo(errResponse);
            }
        );
    }
    
    function operationOnCustomerOk(customer){
    	$scope.textAlert = "operazione terminata con successo. id  : " + customer.idCustomer + " - descrizione : " + customer.descr;
        $scope.showInfoAlert = true;
        $scope.showErrorAlert = false;
        fetchAllCustomers();
    }
    
    function operationOnCustomerKo(){
    	$scope.textAlert = "Operazione terminata con ERRORE. " ;
    	$scope.showInfoAlert = false;
    	$scope.showErrorAlert = true;
        fetchAllCustomers();
    }

}]);
