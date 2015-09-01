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
                   CurrencyService.getCourse();

                  }
                }
            );
            $scope.$watch(
                function(){ return CurrencyService.basicCurrency },
                function(newVal, oldVal){
                    if (oldVal) {
                        alert();
                       CurrencyService.setBasic(newVal);

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
                s.basicCurrency = data.value;
            }

        });
    };
    s.getBasic();
    s.getAll = function (){
        $http.get('?getCurrencies').success(function (data) {
            s.myCurrencies=data;
        });
    };
    s.getAll();

    s.setBasic=function(code,isNew){
        $http.get('?setProperty&name=basicCurrency&value='+code).success(function (data) {
            s.basicCurrency = data.value;
            if (isNew) {
                s.newCurrency.course=1;
                s.addCurrency(s.newCurrency);
            } else{
                s.refreshRates();
            }

        });
    };
    s.getCourse=function(){
      if ( s.basicCurrency) {
          var req = "https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USD" +
              s.basicCurrency + ',USD'+s.newCurrency.code + "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
          $http.get(req).success(function (data) {
              s.newCurrency.course = data.query.results.rate[0].Rate/data.query.results.rate[1].Rate;
          });
      }
    };
    s.addCurrency=function(currency){
        $http.post('addCurrency', currency).then(
            function (response) {
                s.myCurrencies=response.data;
                s.newCurrency={};
            },
            function(response){
                alert(response.data.errorMessage)
            });

    };
    s.edit = function(currency) {
        s.activeCurrency = currency;

    };
    s.refreshRates=function(){
        var req="";
        for (i=0;i< s.myCurrencies.length;i++){
            if (s.myCurrencies[i].code== s.basicCurrency){
                s.newRate=s.myCurrencies[i].course;
            }else {
                if (s.myCurrencies[i].update) {
                    req = req + "USD" +
                        s.basicCurrency + ',USD' + s.myCurrencies[i].code + ','
                }
            }
        }

        $http.get("https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22"+
            req+"%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=").success(function (data) {
            s.newRates=[];
            var j=0;
            var currency={};
            for (i=0;i< s.myCurrencies.length;i++){
                if (s.myCurrencies[i].code== s.basicCurrency) {
                    currency.course=1;
                }else{
                    if (s.myCurrencies[i].update) {
                        currency.course = data.query.results.rate[j].Rate / data.query.results.rate[j+1].Rate;
                        j=j+2;
                    } else {
                        currency.course=s.myCurrencies[i].course/ s.newRate;
                    }
                }
                currency.id = s.myCurrencies[i].id;
                s.newRates.push(currency);
                currency={};
            }

        });
    };
    s.del=function(id){
        if (id !== undefined && confirm("sure?!")) {
            $http.get('?deleteCurrency&id=' + id).then(function (response) {

                    s.myCurrencies=response.data;

                },
                function(response){
                    alert(response.data.errorMessage);
                });
        }
    };

    return s;
}]);
