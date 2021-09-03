const mongoose = require('mongoose');
let Schema = mongoose.Schema;

let usuarioSchema = new Schema({
   //aqui van los campos de la tabla usuario

   nombre:{type: String, require: true},
   telefono:{type: String, require: true},
   email:{type: String, require: true}

});

module.exports = mongoose.model('Usuario', usuarioSchema);



