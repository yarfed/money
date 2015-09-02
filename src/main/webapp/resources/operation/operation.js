/**
 * Created by User on 25.08.2015.
 */

app.controller('OperationsCtrl',
    function OperationsCtrl($scope, AccountService,OperationService,CategoryService,CurrencyService) {

        $scope.accountsIndex=AccountService.accountsIndex;
        $scope.$watch(
            function(){ return AccountService.accountsIndex },
            function(newVal){
                $scope.accountsIndex = newVal;
            }
        );

        $scope.expenseCategoriesIndex=CategoryService.expenseCategoriesIndex;
        $scope.$watch(
            function(){ return CategoryService.expenseCategoriesIndex },
            function(newVal){
                $scope.expenseCategoriesIndex = newVal;
            }
        );
        $scope.expenseCategories=CategoryService.expenseCategories;
        $scope.$watch(
            function(){ return CategoryService.expenseCategories },
            function(newVal){
                $scope.expenseCategories = newVal;
            }
        );
        $scope.incomeCategories=CategoryService.incomeCategories;
        $scope.$watch(
            function(){ return CategoryService.incomeCategories },
            function(newVal){
                $scope.incomeCategories = newVal;
            }
        );
        $scope.incomeCategoriesIndex=CategoryService.incomeCategoriesIndex;
        $scope.$watch(
            function(){ return CategoryService.incomeCategoriesIndex },
            function(newVal){
                $scope.incomeCategoriesIndex = newVal;
            }
        );
        $scope.operations=OperationService.operations;
        $scope.$watch(
            function(){ return OperationService.operations },
            function(newVal){
                $scope.operations = newVal;
                $scope.eraseForm();
            }
        );
        $scope.currencies=  CurrencyService.myCurrenciesIndex;

        $scope.$watch(
            function(){ return CurrencyService.myCurrenciesIndex },
            function(newVal){
                if (newVal) {
                    $scope.currencies= newVal;

                }
            }
        );

        $scope.operation={};
        $scope.operation.date=(+new Date);
        $scope.changeDate=function(n){
            $scope.operation.date = $scope.operation.date+n*86400000;
        };
        $scope.operation.type='expense';

        $scope.operation.comment="";

        $scope.eraseForm = function() {
            var type=$scope.operation.type;
            $scope.operation={};
            $scope.operation.date=(+new Date);
            $scope.operation.type=type;

        };
        $scope.addOperation = function(){
          OperationService.addOperation( $scope.operation)
        };

        $scope.deleteOperation = function(id){
            OperationService.deleteOperation(id);
        };

        $scope.setOperation=function(operation){
            $scope.operation=$.extend({}, operation);


        };
        $scope.setFile = function () {
            return  'resources/operation/'+$scope.operation.type+'.html';
        };
        $scope.setMode=function(mode){
            $scope.operation.type=mode;
            $scope.eraseForm();
        }

    });

app.factory('OperationService',['$http' , 'AccountService',function($http, AccountService) {
    var o={};

    o.getOperations=function ()
    {$http.get('?getOperations').success(function (data) {
        o.operations=data;
    })}();

    o.deleteOperation = function(id){
        if (id !== undefined && confirm("sure?!")) {
            $http.get('?deleteOperation&id=' + id).then(function (response) {
                    o.operations=response.data;
                    AccountService.getAccounts();
                },
                function(response){
                    alert(response.data.errorMessage);
                });
        }
    };

    o.addOperation = function(operation){
        $http.post('addOperation',operation).then(
        function (response) {
            o.operations = response.data;
            AccountService.getAccounts();

        },
        function(response){
            alert(response.data.errorMessage)
        });
    };

    return o;
}]);




