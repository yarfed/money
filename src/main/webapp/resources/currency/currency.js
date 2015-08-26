/**
 * Created by User on 25.08.2015.
 */
app.controller('ChangeCurrencyCtrl',['$scope', 'CurrencyService',
        function($scope, CurrencyService) {
            $scope.service=CurrencyService;
            CurrencyService.newCurrency={};
            $scope.$watch(
                function(){ return $scope.addedCurrency },
                function(newVal){
                  if (newVal) {
                      CurrencyService.newCurrency.name = newVal.name;
                      CurrencyService.newCurrency.code = newVal.code;
                      CurrencyService.newCurrency.symbol = newVal.symbol_native;
                      CurrencyService.newCurrency.update = true;
                   CurrencyService.getCourse();

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
    s.getBasic=function() {
        $http.get('?getProperty&name=basicCurrency').success(function (data) {
            if (data) {
                alert(data.value);
            }
            s.basicCurrency = data.value;
        });
    };
    s.getBasic();
    s.getAll = function (){
        $http.get('?getCurrencies').success(function (data) {

            s.myCurrencies=data;
        });
    };
    s.getAll();
    s.setBasic=function(code){
        $http.get('?setProperty&name=basicCurrency&value='+code).success(function (data) {
            s.getBasic();
        });
    };
    s.getCourse=function(){
        var req="https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22"+
            s.basicCurrency+ s.newCurrency.code+"%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        $http.get(req).success(function (data) {
            s.newCurrency.value =(data.query.results.rate.Rate);
        });

    };
    s.addCurrency=function(){
        $http.post('addCurrency', s.newCurrency).then(
            function (response) {
                s.myCurrencies=response.data;
            },
            function(response){
                alert(response.data.errorMessage)
            });

    };
    return s;
}]);
