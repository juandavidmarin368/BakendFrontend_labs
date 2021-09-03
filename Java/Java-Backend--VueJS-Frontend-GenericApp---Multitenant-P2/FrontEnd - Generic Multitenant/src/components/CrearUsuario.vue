<template>
    
    <v-container class="grey lighten-5" >
 <v-row justify="center" >
    <v-col cols="12" sm="10" md="8" lg="6">

  <div class="text-center">
        <h2>Formulario</h2> <br>
        </div>

<v-card  :elevation="4"
          class="pa-2"
        
        >
<div class="text-center">
       <form>
    <v-select
      v-model="select1"
      :items="perfiles"
      label="Seleccione Cargo"
      required
      item-text="nombrePerfilCargo"
      item-value="id"

       @change="getPerfil"       
       

    ></v-select>
    <v-select
      v-model="select2"
      :items="dropdown_font"
      
      label="Seleccione 2"
      required
      @change="$v.select.$touch()"
      @blur="$v.select.$touch()"
    ></v-select>
    <v-select
      v-model="select3"
      :items="dropdown_font"
      
      label="Seleccione 3"
      required
      @change="$v.select.$touch()"
      @blur="$v.select.$touch()"
    ></v-select>
    


   
    
  </form>
  </div>

</v-card>
    
    </v-col>

  </v-row>


 <v-row justify="center" >
    <v-col cols="12" sm="10" md="8" lg="6">
<v-card  :elevation="4"
          class="pa-2"
        
        >
 <v-btn block color="success"  @click="finish">ENVIAR</v-btn>
</v-card>
    </v-col>
 </v-row>

</v-container>

</template>


<script>

import axios from "axios";
import { actionStore } from "./CheckerLocalStorage";


 export default {
    data: vm => ({
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
      menu2: false,
      e4: null,
      dropdown_font: ['Arial', 'Calibri', 'Courier', 'Verdana'],
      perfiles: [{ id: 1,nombrePerfilCargo: 'one'}],
   
      fecha:'',
      select1:null,
      select2:null,
      select3:null,
      hora:''

    }),

    computed: {
      computedDateFormatted () {

        
        return this.formatDate(this.date)
      },
    },

    watch: {
      date (val) {
        this.dateFormatted = this.formatDate(this.date)
        this.fecha = "Fecha seleccionada: "+this.dateFormatted
       
      },
      e4 (val){

        this.hora = "Hora seleccionada: "+this.e4
        console.log('it has cahnged..'+this.e4);

      }
    },

     mounted () {

        let that = this;
        let url = actionStore.methods.getInfoEmpresa().urlServer+'/register/perfilcargos?tenantId='+actionStore.methods.getInfoEmpresa().tenantId;
        console.log(url);
        axios.get(url);
            .then(function (response) {
                
                console.log(response);
                that.perfiles = response.data;
        }).then()
    },

    methods: {

      getPerfil: function(){

          console.log('perfil selected..'+this.select1);
      },

      finish:function(){

        console.log('from here..'+this.dateFormatted);
        console.log('from here..'+this.e4);

      },

      formatDate (date) {
        if (!date) return null

        const [year, month, day] = date.split('-')
        return `${year}-${month}-${day}`
      },
      parseDate (date) {
        if (!date) return null

        const [month, day, year] = date.split('/')
        return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
      },
    },
  }


</script>

