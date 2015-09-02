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
                      CurrencyService.newCurrency.course=null;
                   CurrencyService.getCourse(CurrencyService.newCurrency);

                  }
                }
            );
            $scope.$watch(
                function(){ return CurrencyService.basicCurrency },
                function(newVal, oldVal){
                    if (oldVal!==undefined&&newVal!=oldVal) {
                        CurrencyService.refreshRates();
                    }
                }
            );
            $scope.getCourse=function(currency){
                if(currency.update){
                    currency.course=null;
                    CurrencyService.getCourse(currency);
                }
            }
        }
    ]);
app.factory('CurrencyService',['$http' , function($http) {
    var s={};
    $http.get('resources/currency/currencymap.json').success(function (data) {

            s.allCurrency = data;
        });

    s.getAll = function (){
        $http.get('?getCurrencies').success(function (data) {
            s.myCurrencies=data;
            s.myCurrenciesIndex=createIndexObj(data);
            s.getBsic();
        });
    };
    s.getBsic=function (){
        var found=false;
        for (var i=0;i< s.myCurrencies.length;i++){
            if (s.myCurrencies[i].basic) {
                s.basicCurrency=s.myCurrencies[i].code;
                found=true;
            }
        }
        if (!found){
            s.basicCurrency=null;
        }
    };
    s.getAll();

    s.getCourse=function(currency){
      if ( s.basicCurrency) {
          var req = "https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USD" +
              s.basicCurrency + ',USD'+currency.code + "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
          $http.get(req).success(function (data) {
              currency.course = data.query.results.rate[0].Rate/data.query.results.rate[1].Rate;
          });
      }
    };
    s.addCurrency=function(currency){
        $http.post('addCurrency', currency).then(
            function (response) {
                s.myCurrencies=response.data;
                s.myCurrenciesIndex=createIndexObj(response.data);
                s.getBsic();
                s.newCurrency={};
            },
            function(response){
                alert(response.data.errorMessage)
            });

    };
    s.edit = function(currency) {
        s.activeCurrency = currency;

    };
    s.lock=false;
    s.refreshRates=function(){
        s.lock=true;
        if (!s.basicCurrency) {
            for (var i=0;i< s.myCurrencies.length;i++){
                s.myCurrencies[i].course=null;
                s.myCurrencies[i].update=true;
            }
            s.editAll();

            return;
        }
        var req="";
        for ( i=0;i< s.myCurrencies.length;i++){
            if (s.myCurrencies[i].code== s.basicCurrency){
                s.newRate=s.myCurrencies[i].course;
                s.myCurrencies[i].basic=true;
            }else {
                s.myCurrencies[i].basic=null;
                if (s.myCurrencies[i].update) {
                    req = req + "USD" +
                        s.basicCurrency + ',USD' + s.myCurrencies[i].code + ','
                }
            }
        }

        $http.get("https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22"+
            req+"%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=").success(function (data) {

            var j=0;

            for (i=0;i< s.myCurrencies.length;i++){
                if (s.myCurrencies[i].code== s.basicCurrency) {
                    s.myCurrencies[i].course=1;
                }else{
                    if (s.myCurrencies[i].update) {
                        s.myCurrencies[i].course = data.query.results.rate[j].Rate / data.query.results.rate[j+1].Rate;
                        j=j+2;
                    } else {
                        s.myCurrencies[i].course=s.myCurrencies[i].course/ s.newRate;
                    }
                }
            }
            s.editAll();

        });
    };
    s.editAll=function(){
        $http.post('editAllCurrencies', s.myCurrencies).then(
            function (response) {
                s.myCurrencies=response.data;
                s.myCurrenciesIndex=createIndexObj(response.data);
                s.getBsic();
                s.lock=false;
            },
            function(response){
                alert(response.data.errorMessage)

            });
    };
    s.del=function(id){
        if (id !== undefined && confirm("sure?!")) {
            $http.get('?deleteCurrency&id=' + id).then(function (response) {

                    s.myCurrencies=response.data;
                    s.myCurrenciesIndex=createIndexObj(response.data);
                    s.getBsic();
                },
                function(response){
                    alert(response.data.errorMessage);
                });
        }
    };

    return s;
}]);
