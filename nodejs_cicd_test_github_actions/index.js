const express = require('express');
const mongoose = require('mongoose');
const app = express()

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.use(require('./routes/usuario'))

app.get('/', function (req, res) {
  res.json('Hello Luis')
})

//USING ENVIRIONMENT VARIABLES IN NODEJS https://nodejs.dev/learn/how-to-read-environment-variables-from-nodejs
// USER_ID=239482 USER_KEY=foobar node indes.js
// so as we have got it in this example we are getting those envrionment variables when running the NODEJS application

var userid = process.env.USER_ID 
var userkey = process.env.USER_KEY

console.log("userid --> "+userid);
console.log("userkey --> "+userid);

//aca cuadra la configuracion de la base de datos
//donde dice localhost:numero puerto mongo


//FOR REPLICASET EXAMPLE mongoose.connect('mongodb://user:pw@host1.com:27017,host2.com:27017,host3.com:27017/testdb',{useNewUrlParser: true, useUnifiedTopology: true});

//mongoose.connect('mongodb://my-user:my-password@mongodb-0.mongodb-headless.app1.svc.cluster.local:27017,mongodb-1.mongodb-headless.app1.svc.cluster.local:27017,mongodb-2.mongodb-headless.app1.svc.cluster.local:27017/my-database',{useNewUrlParser: true, useUnifiedTopology: true});
var DB_USERNAME  = process.env.DB_USERNAME
var DB_PASSWORD  = process.env.DB_PASSWORD
var DB_REPLICAS  = process.env.DB_REPLICAS
var DATABASE_NAME = process.env.DATABASE_NAME

mongoose.connect('mongodb://'+DB_USERNAME+':'+DB_PASSWORD+'@'+DB_REPLICAS+'/'+DATABASE_NAME,{useNewUrlParser: true, useUnifiedTopology: true});


//en que puerto esta trabajando la app 
app.listen(3000, '0.0.0.0', ()=>{
  console.log('estoy en el puerto ', 3000)
})
