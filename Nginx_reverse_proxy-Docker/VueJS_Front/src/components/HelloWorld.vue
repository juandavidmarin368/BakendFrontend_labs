<template>
  <v-container>
    <v-layout
      text-center
      wrap
    >
      <v-flex xs12>
       
      </v-flex>

      <v-flex mb-6>
        <h3 class="display-1 font-weight-bold mb-3">
          TESTING FROM DOCKER
        </h3>
        
<v-text-field
          v-model="inputOne"
          label="Ingrese nombre"
        ></v-text-field>


        <v-btn  @click="saveData" color="primary" block>Cargar</v-btn>

<v-data-table
    :headers="headers"
    :items="desserts"
    :items-per-page="10"
    item-key="id"
    class="elevation-1"
  ></v-data-table>
<v-pagination v-model="page" :length="pageCount"  @input="renderinTable"></v-pagination>

      </v-flex>

    
      

    
    </v-layout>
  </v-container>
</template>

<script>

import axios from 'axios';

export default {

  data: () => ({
    inputOne:'',
page: 0,
      pageCount: 0,
    
     headers: [
          {
            text: 'id',
            align: 'left',
            sortable: false,
            value: 'name',
          },
          { text: 'name', value: 'id' },
         
        ],
        desserts: [
        
         
        ],
      
  

  }),

  beforeMount(){

      this.renderinTable();

  },

  methods:{

    
    renderinTable:function(){

      let url = "https://hypercaloricmassmuscle.com/docker/itemspaginados/"+this.page;
      //let url = "http://localhost:7075/docker/itemspaginados/"+this.page;
      let that = this;
      axios.get(url)
              .then(function (response) {
                  console.log(response);
                  that.pageCount = parseInt(response.data.totalPages);
                  that.desserts = response.data.items;
          }).then();

    },

    saveData:function(){

        console.log('data..');

        if(this.inputOne!=""){

          let url = "https://hypercaloricmassmuscle.com/docker/createitems/"+this.inputOne;
          //let url = "http://localhost:7075/docker/createitems/"+this.inputOne;
          console.log(url);
          let that = this;
          axios.post(url,{})
                .then(function () {
                    that.renderinTable();
                   
            }).then();


        }

         

        
    }

  }

};
</script>
