<template>
  <div class="about">
     <center><div id="nav">
      <router-link to="/">WebSockets</router-link> |
      <router-link to="/about">Form</router-link>
    </div>

<h1>FormDT</h1>
 <form>
    <v-text-field
      v-model="cc"
      v-on:keyup="disableMessage"
      label="CC"
      required
    
    ></v-text-field>
    <v-text-field
      v-model="name"
     
      label="NAME"
      required
      v-on:keyup="disableMessage"
     
    ></v-text-field>

  <v-alert type="success" v-if="statusSend">
      RECORD SENT
    </v-alert>

    <v-btn class="mr-4" @click="send">SENT DATA</v-btn>
   
  </form>

    </center>
    
  </div>
</template>

<script>
import axios from 'axios';


  export default {
    data () {
      return {
        
        cc:'',
        name:'',
        statusSend:false

      }
    },
    methods:{

     disableMessage:function(){

       this.statusSend = false;

     }, 

     send:function(){

       let that = this;
       let url = "http://localhost:8445/get/adduser"
       axios.post(url, {
                          name:this.name,
                          cc:this.cc,
                        
                        })
                        .then(function () {
                           // console.log("send it ok!");
                            that.statusSend=true;
                        })
                       


     }

    }
  }
</script>