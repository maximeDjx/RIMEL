// grab the packages we need
var express = require('express');
var path = require('path');
var app = express();
var port = process.env.PORT || 8005;


var bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

// routes will go here

// start the server
app.listen(port);
console.log('Server started! At http://localhost:' + port);


app.get('/',  function (req,  res)  {
    res.sendFile(__dirname + '/displayResult.html');
 });


app.get('/result',  function (req,  res)  {
    res.sendFile(path.resolve(__dirname +'/../exceptionWeka/src/main/java/rimel/results/result.json'));
 });