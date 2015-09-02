/**
 * Created by User on 24.08.2015.
 */

app.controller('ChangeCategoryCtrl',
    function($scope,$routeParams,CategoryService) {
        var type=$routeParams.type;
        $scope.categories=CategoryService[type+'Categories'];
        $scope.$watch(
            function(){ return CategoryService[type+'Categories'] },
            function(newVal){
                $scope.categories = newVal;
                $scope.newCategory.name="";
            }
        );
        $scope.categoriesIndex=CategoryService[type+'CategoriesIndex'];
        $scope.$watch(
            function(){ return CategoryService[type+'CategoriesIndex'] },
            function(newVal){
                $scope.categoriesIndex = newVal;
            }
        );
        $scope.activeCategory={};
        $scope.newCategory={};
        $scope.$watch(
            function(){ return $scope.activeCategory.id },
            function(newVal){
               if(newVal) {
                   $scope.activeCategory = $.extend({}, $scope.categoriesIndex[newVal]);
                   $scope.newCategory.parentId=$scope.activeCategory.id;
               }
            }
        );
        $scope.addNewCategory=function(){
            CategoryService.add($scope.newCategory,type);

        };
       $scope.addCategory=function(){
           CategoryService.add($scope.activeCategory,type);

       };
        $scope.eraseForm=function(){
            $scope.activeCategory = $.extend({},$scope.categoriesIndex[$scope.activeCategory.id]);
        };
        $scope.deleteCategory=function(){
            CategoryService.del($scope.activeCategory.id,type);
        }
    });
app.factory('CategoryService', function($http ) {
    var c = {};
    c.update=function (data,type) {
        c[type+'Categories'] = data;
        c[type+'CategoriesIndex'] = createIndexObj( data);;

    };

    c.getCategories = function (type) {
        $http.get('?get_'+type+'_categories').success(function(data) {
            c.update(data,type);

        });
    };
    c.getCategories('income');
    c.getCategories('expense');
    c.add = function(category,type){
        category.parentId=category.parentId||0;
        $http.post('add_'+type+'_category',category).then(
            function (response) {
                c.update(response.data,type);
            },
            function(response){
                alert(response.data.errorMessage)
            });

    };
    c.del = function(id,type){
        $http.get('?del_'+type+'_category&id=' + id).then(
            function (response) {
                c.update(response.data,type);
            },
            function(response){
                alert(response.data.errorMessage)
            });
    };
    return c;
});
app.directive("orderedList", function () {
    return {
        link: function (scope, element, attrs) {
            scope.openCategories=function(){
                scope.isOpen=!scope.isOpen;
            };

            scope.chooseCategory = function(category){
                scope.id=category.id;

                scope.isOpen=false;
                scope.pattern="";

            };
            scope.matchId = function(query) {
                return function(category) { return (category.parentId==query); }
            };
        },
        restrict: "A",
        templateUrl: "resources/category/categories.html",
        scope: {
            categories:"=orderedList",
            id:"=id",
            editable:"=editable",
            index:"=index",
            isOpen:"=open"
        }

    }

});