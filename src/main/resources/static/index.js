angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1/products';

    $scope.loadProducts = function (){
        $http.get(contextPath)
            .then(function (response){
                console.log(response.data)
                $scope.productList = response.data;
            });
    };

    $scope.loadProductsWithFilter = function (){
        var minPrice = document.getElementById('min').value
        var maxPrice = document.getElementById('max').value
        $http.get(contextPath+'?min_price='+minPrice+'&max_price='+maxPrice)
            .then(function (response){
                console.log(response.data)
                $scope.productList = response.data;
            });
    };

    $scope.changePrice = function (productId, delta){
        $http({
            url: contextPath+ '/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response){
            $scope.loadProducts();
        });
    };

    $scope.deleteProduct = function (productId){
        $http.delete(contextPath+'/'+productId)
            .then(function (response){
            $scope.loadProducts();
        });
    }

    $scope.createProductJson = function (){
        console.log($scope.newProductJson)
        $http.post(contextPath, $scope.newProductJson)
            .then(function (response){
                $scope.loadProducts();
            })
    }

    $scope.loadProducts();
});