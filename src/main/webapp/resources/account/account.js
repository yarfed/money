/**
 * Created by User on 23.08.2015.
 */

app.controller('ChangeAccountsCtrl',['$scope', 'AccountService', 'CurrencyService',
        function($scope, AccountService, CurrencyService) {
            $scope.service=AccountService;

            $scope.currencies=  CurrencyService.myCurrenciesIndex;

            $scope.$watch(
                function(){ return CurrencyService.myCurrenciesIndex },
                function(newVal){
                    if (newVal) {
                        $scope.currencies= newVal;

                    }
                }
            );
        }]
);

app.factory('AccountService',['$http' , function($http ) {
    var s={};

    s.getAccounts=function () {
        $http.get('account/?getAll').success(function (data) {

        s.accountsIndex = createIndexObj(data);

    })};
    s.getAccounts();
    s.del = function(id){
        if (id !== undefined && confirm("sure?!")) {
            $http.get('account/?delete&id=' + id).then(function (response) {

                    s.accountsIndex=createIndexObj(response.data);

                },
                function(response){
                    alert(response.data.errorMessage);
                });
        }
    };

    s.add = function(account){
        $http.post('account/add', account).then(
            function (response) {

                s.accountsIndex=createIndexObj(response.data);

            },
            function(response){
                alert(response.data.errorMessage)
            });
        s.newAccount={};
    };

    s.edit = function(account) {
        s.activeAccount = account;
    };
    return s;
}]);
