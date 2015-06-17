var codePuzzlesApp = angular.module('codePuzzlesApp',[]);

//angular controller
codePuzzlesApp.controller('greetingController', function($scope, $http) {
  $http.get('/resource/').success(function(data) {
    $scope.greeting = data;
  })
});

codePuzzlesApp.controller('binaryTreeFormController', function ($scope, $http)
{
  $scope.tree = {
    shown: false,
    ancestorId: 0,
    nodeId1:5,
    nodeId2:7,
    root:JSON.stringify({
         id:1,
         left:{
           id:2,
           left:{
               id:4
           },
           right:{
               id:5
           }
         },
         right:{
           id:3,
           left:{
               id:6
           },
           right:{
               id:7
           }
         }
       }, null, 2)
  };


  $scope.submit = function (tree, resultVarName)
  {
    console.log("submit form");

    //parse the json
    tree.root = JSON.parse(tree.root);

    var config = {
    };

    $http.post('/ancestor', tree, config)
          .success(function (data, status, headers, config)
          {
                tree.root = JSON.stringify(data.root, null, 2);
                tree.shown = true;
                tree.ancestorId = data.ancestorId;
                $scope[resultVarName] = data;

          })
          .error(function (data, status, headers, config)
          {
                $scope[resultVarName] = "SUBMIT ERROR";
          });
  };
});




