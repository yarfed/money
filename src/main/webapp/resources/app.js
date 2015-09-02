/**
 * Created by User on 27.07.2015.
 */
var app = angular.module("moneyApp",["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider.when('/operations',
        {
            templateUrl: 'resources/operation/operations.html',
            controller: 'OperationsCtrl'
        });
    $routeProvider.when('/changeAccounts',
        {
            templateUrl: 'resources/account/changeAccounts.html',
            controller: 'ChangeAccountsCtrl'
        });
    $routeProvider.when('/changeCategory/:type',
        {
            templateUrl: 'resources/category/changeCategory.html',
            controller: 'ChangeCategoryCtrl'
        });
    $routeProvider.when('/changeCurrency',
        {
            templateUrl: 'resources/currency/changeCurrency.html',
            controller: 'ChangeCurrencyCtrl'
        });

    });
function createIndexObj(data){
    var result={};
    for (var i=0; i<data.length;i++){
        result[data[i].id]=data[i];
    }
    return result;
}
