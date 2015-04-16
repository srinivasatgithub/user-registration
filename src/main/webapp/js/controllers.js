// Create a module for our online exam services
var onlineExamServices = angular.module('OnlineExam', ['ngRoute']);


// Set up our mappings between URLs, templates, and controllers
function emailRouteConfig($routeProvider) {
    $routeProvider.
            when('/', {
                controller: 'StartController',
                templateUrl: 'start.html'
            }).
// Notice that for the detail view, we specify a parameterized URL component
// by placing a colon in front of the id
            when('/page/:id', {
                controller: 'QuestionController',
                templateUrl: 'question.html'
            }).
            when('/submit/:id', {
                controller: 'submitController',
                templateUrl: 'question.html'
            }).
            otherwise({
                redirectTo: '/'
            });
}


// Set up our route so the AMail service can find it
onlineExamServices.config(emailRouteConfig);

onlineExamServices.factory("Data", function() {
    return {
        SelectedAnswer: []
        
    };
});

// Some questions
questions = [{
        id: 1, desc: 'first question', options: [{id: 'A', value: 'A'}, {id: 'B', value: 'B'}, {id: 'C', value: 'C'}],
        answer: 'A'
    }, {
        id: 2, desc: 'second question', options: [{id: 'A', value: 'A'}, {id: 'B', value: 'B'}, {id: 'C', value: 'C'}],
        answer: 'B'
    }, {
        id: 3, desc: 'third question', options: [{id: 'A', value: 'A'}, {id: 'B', value: 'B'}, {id: 'C', value: 'C'}],
        answer: 'C'
    }, ];



// Publish our messages for the list template
onlineExamServices.controller("StartController", function($scope) {
    $scope.questions = questions;
    $scope.questionNumber = 1;
});

// Get the question id from the route (parsed from the URL) and use it to
// find the right question object.
onlineExamServices.controller("QuestionController", function($scope, $routeParams, Data) {
    $scope.question = questions[$routeParams.id - 1];
    $scope.previous = parseInt($routeParams.id) % questions.length - 1;
    $scope.next = parseInt($routeParams.id) % questions.length + 1;
    $scope.data = Data;
});

// Get the question id from the route (parsed from the URL) and use it to
// find the right question object.
onlineExamServices.controller("submitController", function($scope, $routeParams, Data) {
    alert(Data.selectedOption);
    $scope.data = Data;
    answer = Data.SelectedAnswer[$routeParams.id];
    answer.id = $routeParams.id;
    answer.option = Data.selectedOption;
    $scope.question = questions[$routeParams.id - 1];
    $scope.previous = parseInt($routeParams.id) % questions.length - 1;
    $scope.next = parseInt($routeParams.id) % questions.length + 1;
});

submitAnswer = function($scope, $routeParams) {
    alert($scope.selectedOption);
    $scope.selectedAnswer = 'C';
    $scope.question = questions[$routeParams.id - 1];
    $scope.previous = parseInt($routeParams.id) % questions.length - 1;
    alert($scope.previous);
    $scope.next = parseInt($routeParams.id) % questions.length + 1;
};

