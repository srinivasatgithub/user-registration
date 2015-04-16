// Create a module for search services
var searchServices = angular.module('ndc.search', ['ngRoute']);


searchServices.controller('SearchController', function ($scope, $http, $log, $modal) {
    $scope.headline = "Edit User";
    $scope.isAdd = false;
    $scope.search = {ndc:"100",productName:"ABC"};
   
    $scope.searchButtonClicked = function () {
        var query = "nationalDrugCode=" + $scope.search.ndc + "&" + "productName=" + $scope.search.productName;
    	$http.get("/ndc-new/api/products/search/bestRate?"+query)
          .success(function (data) {
            alert("Search is successfully");
          })
          .error(function (err) {
            var errorMsg = 'Error searching products. ' + angular.toJson(err);
            $log.error(errorMsg);
            alert(errorMsg);
          });
      };

});
