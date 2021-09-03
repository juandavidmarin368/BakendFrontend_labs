const express = require('express');
const Usuario = require('../models/usuario')
const app = express();

app.get('/usuario', function (req, res) {
  Usuario.find({})
         .exec((err, usuarios)=>{
            if(err){
             return 'error 404'
            }

            res.json({
              usuarios
            })
         })
})

app.post('/usuario', function (req, res) {
  let body = req.body;
  let usuario = new Usuario({
     nombre: body.nombre,
     telefono: body.telefono,
     email: body.email,
  });

  usuario.save((err, usuarioDB)=>{
    if(err){
      return res.status(400).json({
       ok:false,
       err
      })
    }

    res.json({
      ok:true,
      usuario:usuarioDB
    })
    
  })
})

app.put('/usuario/:id', function (req, res) {
  let id = req.params.id;
  let body = req.body;
  Usuario.findByIdAndUpdate(id, body, {new:true}, (err,usuarioDB)=>{
    if(err){
      return 'error 404'
    }
    res.json({
      usuario: usuarioDB
    })
  })
})

app.delete('/usuario/:id', function (req, res) {
   let id = req.params.id;
   Usuario.findByIdAndRemove(id,(err, usuarioBorrado)=>{
     if(err){
      return 'error 404'
     }

     res.json({
      usuario: usuarioBorrado
    })

   })
})

module.exports = app;