/**
 * Created by User on 25.08.2015.
 */
app.controller('ChangeCurrencyCtrl',['$scope', 'CurrencyService',
        function($scope, CurrencyService) {
            $scope.service=CurrencyService;
            $scope.newCurrency={};
            $scope.$watch(
                function(){ return $scope.addedCurrency },
                function(newVal){
                  if (newVal) {
                      $scope.newCurrency.name = newVal.name;
                      $scope.newCurrency.code = newVal.code;
                      $scope.newCurrency.symbol = newVal.symbol_native;
                      $scope.newCurrency.update = true;
                  }
                }
            );
        }
    ]);
app.factory('CurrencyService',['$http' , function($http) {
    var s={};
    $http.get('resources/currency/currencymap.json').success(function (data) {

            s.allCurrency = data;
        });
    $http.get('?getProperty&name="basicCurrency"').success(function (data) {
        s.myCurrencies = {};
        createIndexObj( s.myCurrencies, data);
    });
    s.getAll = function (){
        $http.get('?getCurrencies').success(function (data) {
            s.myCurrencies = {};
            createIndexObj( s.myCurrencies, data);
        });
    };
    s.getAll();
    s.basicCurrency;
    return s;
}]);
