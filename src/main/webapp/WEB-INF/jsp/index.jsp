<!DOCTYPE html>
<html lang="en" ng-app="ndcApp">
<head>
<meta charset="UTF-8">
<title>NDC Portal</title>

<script type="text/javascript"
	src="//maps.googleapis.com/maps/api/js?libraries=places"></script>

<script src="lib/underscore-min-1.5.2.js"></script>
<script
	src="bower_components/ng-file-upload/angular-file-upload-shim.min.js"></script>
<script src="bower_components/angular/angular.min.js"></script>
<script src="bower_components/ng-file-upload/angular-file-upload.min.js"></script>
<script src="bower_components/angular-route/angular-route.min.js"></script>
<script src="bower_components/angular-animate/angular-animate.min.js"></script>
<script src="bower_components/angular-touch/angular-touch.min.js"></script>
<script src="bower_components/angular-strap/dist/angular-strap.min.js"></script>
<script
	src="bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
<script src="bower_components/ng-table/ng-table.min.js"></script>
<script src="bower_components/jquery/dist/jquery.js"></script>
<script
	src="bower_components/angular-unsavedChanges/dist/unsavedChanges.js"></script>
<script src="bower_components/spin.js/spin.js"></script>
<script src="bower_components/angular-spinner/angular-spinner.js"></script>
<script src="lib/ui-bootstrap-tpls-0.11.0.min.js"></script>
<script src="lib/async.js"></script>
<script src="js/ndcApp.js"></script>
<!--<script src="js/services/userManager.js"></script>
<script src="js/services/appConfigManager.js"></script>
<script src="js/directives/ng-really.js"></script>
<script src="js/directives/stateOptions.js"></script>
<script src="js/filters/unsafeFilter.js"></script>
<script src="js/filters/arrayNoneFilter.js"></script>
<script src="js/providers/stateValueProvider.js"></script>
<script src="js/controllers/homeController.js"></script>
<script src="js/controllers/conditionController.js"></script> -->
<script src="js/controllers/searchController.js"></script>


<link rel="stylesheet"
	href="bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="bower_components/ng-table/ng-table.css">
<link rel="stylesheet" href="css/app.css">

</head>
<body>
	<h1>Search</h1>
	<div class="container">
		<div class="view-container">
			<div ng-view class="view-frame"></div>
		</div>
	</div>

</body>
</html>

