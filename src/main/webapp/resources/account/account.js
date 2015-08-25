/**
 * Created by User on 23.08.2015.
 */

app.controller('ChangeAccountsCtrl',['$scope', 'AccountService',
        function($scope, AccountService) {
            $scope.service=AccountService;
        }]
);

app.factory('AccountService',['$http' , function($http ) {
    var s={};

    s.getAccounts=function ()
    {$http.get('?getAccounts').success(function (data) {

        s.accountsIndex = {};
        createIndexObj(s.accountsIndex, data);
    })};
    s.getAccounts();
    s.del = function(id){
        if (id !== undefined && confirm("sure?!")) {
            $http.get('?deleteAccount&id=' + id).then(function (response) {

                    s.accountsIndex={};
                    createIndexObj(s.accountsIndex,response.data);
                },
                function(response){
                    alert(response.data.errorMessage);
                });
        }
    };

    s.add = function(account){
        $http.post('add_account',account).then(
            function (response) {

                s.accountsIndex={};
                createIndexObj(s.accountsIndex,response.data);
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
