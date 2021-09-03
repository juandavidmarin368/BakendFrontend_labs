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
      :items="dropdown_font"
      
      label="Seleccione 1"
      required
      @change="$v.select.$touch()"
      @blur="$v.select.$touch()"
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
    

<v-date-picker v-model="date" no-title @input="menu1 = false" locale="es"></v-date-picker>


<v-text-field
      v-model="fecha"
      :counter="10"
     
      required
    ></v-text-field>

      <v-time-picker v-model="e4" ></v-time-picker>

<v-text-field
      v-model="hora"
      :counter="10"
     
      required
    ></v-text-field>

   
    
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

 export default {
    data: vm => ({
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
      menu2: false,
      e4: null,
      dropdown_font: ['Arial', 'Calibri', 'Courier', 'Verdana'],
   
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

    methods: {



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

