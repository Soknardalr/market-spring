angular.module('app', []).controller('cartController', function ($scope, $http) {
    const productPath = 'http://localhost:8189/app/api/v1/products';
    const cartPath = 'http://localhost:8189/app/api/v1/cart';

    $scope.loadCart = function () {
        $http.get(cartPath)
            .then(function (response) {
                console.log(response.data)
                $scope.cartList = response.data;
            });
    };
    $scope.loadCart();
});