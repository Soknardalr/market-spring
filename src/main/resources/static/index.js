angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contexPath = 'http://localhost:8189/app';

    $scope.loadClients = function (){
        $http.get(contexPath+'/clients')
            .then(function (response){
                $scope.clientList = response.data;
            });
    };

    $scope.changeScore = function (clientId, delta){
        $http({
            url: contexPath+ '/client/change_score',
            method: 'GET',
            params: {
                clientId: clientId,
                delta: delta
            }
        }).then(function (response){
            $scope.loadClients()
        });
    };

    $scope.deleteClient = function (clientId){
        $http.get(contexPath+'/client/delete/'+clientId)
            .then(function (response){
            $scope.loadClients();
        });
    }

    $scope.loadClients();
});