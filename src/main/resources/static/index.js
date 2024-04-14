angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function (){
        $http.get(contextPath+'/products')
            .then(function (response){
                $scope.productList = response.data;
            });
    };

    $scope.changePrice = function (productId, delta){
        $http({
            url: contextPath+ '/product/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response){
            $scope.loadProducts()
        });
    };

    $scope.deleteProduct = function (productId){
        $http.get(contextPath+'/product/delete/'+productId)
            .then(function (response){
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
});