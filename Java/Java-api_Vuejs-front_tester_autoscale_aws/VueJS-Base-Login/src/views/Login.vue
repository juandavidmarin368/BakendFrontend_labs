<template>
<div id="app">
 <span class="bg"></span>
  <v-app id="inspire">
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                
                <v-toolbar-title>IP SERVER INSTANCE: {{ipserver}}<br>LOGIN</v-toolbar-title>
                <div class="flex-grow-1"></div>
              
               
              </v-toolbar>
              <v-card-text>
                <v-form>

                    <v-icon slot="prependIcon" large color="primary">comment</v-icon>
                  <v-text-field
                    label="Nombre de usuario"
                    v-model="username"
                    prepend-icon="mdi-account-circle"
                    type="text"
                  ></v-text-field>

                  <v-text-field

                  
                    id="password"
                    label="Clave"
                    v-model="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-on:keyup.enter="getMe"
                     v-on:keyup="checkMe"
                  ></v-text-field>
                </v-form>
              </v-card-text>
                



              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn block color="primary" @click.prevent="getMe">Iniciar</v-btn>
              </v-card-actions>

            
            </v-card>
<br>
           <v-alert type="warning" v-if="statusEmpty">
      No deje campos vacios
    </v-alert>

    <v-alert type="error" v-if="statusFailure">
      Credenciales invalidas, intente nuevamente
    </v-alert>

          </v-col>

       
        </v-row>


         


      </v-container>
    </v-content>
  </v-app>
  </div>
</template>

<script>

import axios from "axios";
import { actionStore } from "../components/CheckerLocalStorage.js";




  export default {
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
      statusFailure:false,
      statusEmpty:false,
      username:'',
      password:'',
      ipserver:''
        

    }),

  beforeMount:function(){


       let url = actionStore.methods.getEndpoints().URL_BACKEND+"/api3/gettingip";
        
        console.log("THE URL "+url);
        let that = this;
        axios.get(url)
            .then(function (response) {
                console.log(response);
                that.ipserver = response.data;
        }).then();

  },

     methods:{

       checkMe:function(){

         if(this.statusEmpty==true){

          this.statusFailure = false;
           if(this.username!='' && this.password!=''){

             this.statusEmpty = false;
             this.statusFailure = false;

           }

         }

       },

        getMe:function(){
           

            if(this.username!='' && this.password!=''){

                var details={
                            "usernameOrEmail":this.username,
                            "password":this.password};

            axios
            .post(actionStore.methods.getEndpoints().URL_BACKEND+"/api/auth/signin",details)
            .then(response => {
             
                let newToken = {
                            "authLogin":true,
                            "accessToken":response.data.accessToken,
                            "roles":response.data.roles
                }

                

                localStorage.setItem('todos', JSON.stringify(newToken));


                newToken.roles.map((item)=>{

                    if(item.role_id == 1){
        
                      window.location.href = actionStore.methods.getEndpoints().URL_FRONTEND+"/homenduser";
        
                    }
                    if(item.role_id == 2){
        
                        window.location.href = actionStore.methods.getEndpoints().URL_FRONTEND+"/homeadmin";
                    }
                    if(item.role_id == 3){
        
                        window.location.href = actionStore.methods.getEndpoints().URL_FRONTEND+"/homesuperadmin";
                        
                    }
                           // 
              })

               // 
                //console.log(response.data);
             
            })
             .catch(error => {
              if (error.response.status == 401) {
               
                 this.statusFailure = true;
              }
            });

            }else{

                this.statusEmpty = true;

            }
            

           /*let authOk={authLogin:true};
           localStorage.setItem('todos', JSON.stringify(authOk));
           window.location.href = "http://192.168.199.89:8080";*/

        },
        chaning: function(){
            this.statusEmpty = false;
            this.statusFailure = false;
        }

    }


  }
</script>