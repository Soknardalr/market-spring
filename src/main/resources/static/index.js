angular.module('app', ['ngStorage', 'ngCookies'])
    .controller('indexController', function ($scope, $rootScope, $http, $localStorage, $cookies) {
    const contextPath = 'https://localhost:8189/app/api/v1/products';
    const cartPath = 'https://localhost:8189/app/api/v1/cart';

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                console.log(response.data)
                $scope.productList = response.data;
            });
    };


    $scope.tryToAuth = function () {

        $http.defaults.headers.common.Authorization = `Basic ${btoa($scope.user.username + ":" + $scope.user.password)}`;
        $http.get('https://localhost:8189/csrf')
            .then(function successCallback(response) {
                console.log(response)
                $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
                $localStorage.isAuthenticated = true;
                $http.defaults.headers.common.Authorization = undefined;

                $scope.user.username = '';
                $scope.user.password = '';

            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $localStorage.isAuthenticated = false;
        console.log($cookies.get("XSRF-TOKEN"));
        $http.post(`https://localhost:8189/logout?_csrf=${$cookies.get('XSRF-TOKEN')}`);
    };

    $rootScope.isUserLoggedIn = function () {
        return $localStorage.isAuthenticated;
    };

    $scope.loadProductsWithFilter = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.productList = response.data;
        });
    };

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.addToCart = function (productTitle) {
        $http.post(cartPath + '/' + productTitle);
    }


    $scope.createProductJson = function () {
        console.log($scope.newProductJson)
        $http.post(contextPath, $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            })
    }

    $scope.loadProducts();
});