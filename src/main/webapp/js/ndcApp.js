'use strict';

/* App Module */

var ndcApp = angular.module('ndcApp', [
  'ngRoute',
  'ndc.search'
]);


ndcApp.config(['$routeProvider',
  function ($routeProvider) {
    console.log("configuring routes");
    $routeProvider.
      when('/', {
        templateUrl: 'templates/searchPage.tpl.html',
        controller: 'SearchController'
      }).
      // ToDo: account, logout
      otherwise({
        redirectTo: '/'
      });
  }]);

ndcApp.run(function ($rootScope, $location, appConfigManager, categoriesManager, classificationsManager, citiesAndNeighborhoodsManager, userManager) {
  appConfigManager.fetchAppSettings(function (err, cfg) {
    $rootScope.appConfigManager = appConfigManager;
  }, true);
  userManager.getCurrentUser(function (err) {
    if (!err) {
      userManager.getGroupsForCurrentUser();
    }
  });
  $rootScope.userManager = userManager;
  $rootScope.poiSearchText = '';
  $rootScope.searchPOIs = function () {
    $location.url('/businesses?name=' + $rootScope.poiSearchText);
  };
  categoriesManager.reloadCategoryData();
  classificationsManager.reloadClassificationData();
  citiesAndNeighborhoodsManager.fetchAllCityAndNeighborhoodData();
});