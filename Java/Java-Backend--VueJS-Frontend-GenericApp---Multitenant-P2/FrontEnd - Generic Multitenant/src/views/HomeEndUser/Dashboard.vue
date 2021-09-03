<template>
<div>
<v-navigation-drawer
      v-model="drawer"
      app
      clipped
    >
      <v-list dense>
        <v-list-item @click.prevent="dash" >
          <v-list-item-action>
            <v-icon>mdi-view-dashboard</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Dashboard</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="" :to="{path: '/enduser/solicitud' }">
          <v-list-item-action>
            <v-icon>mdi-account-badge-horizontal</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Solicitud</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item @click="" :to="{path: '/enduser/historico'}">
          <v-list-item-action>
            <v-icon>mdi-account-clock</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Historico</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item @click="exit">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title >Salir</v-list-item-title>
          </v-list-item-content>
        </v-list-item>


      </v-list>
    </v-navigation-drawer>


<v-app-bar
      app
      clipped-left
      color="primary"
      dark
    >
    
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title dark color="primary">Application</v-toolbar-title>

         <v-spacer></v-spacer>
  <v-toolbar-items>
      <v-btn :disabled="true"
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Usuario: {{nombreUsuario}}</span>
      </v-btn>
        <v-btn icon>
          <v-icon  @click="dialog3 = true">mdi-dialpad</v-icon>
        </v-btn>

       

  </v-toolbar-items>

    </v-app-bar>

<v-dialog
        v-model="dialog3"
        max-width="500px"
      >
        <v-card>
          <v-card-title>
            <span>Detalles</span>
            <div class="flex-grow-1">

            </div>
         
          </v-card-title>

 <v-card-text>

   <h3>Nit Empresa : {{nitEmpresa}}</h3>
   <h3>Empresa : {{nombreEmpresa}}</h3>
   <h3>Tipo Usuario : {{tipoUsuario}}</h3>
   <h3>Usuario : {{nombreUsuario}}</h3>
   

 </v-card-text>
        
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="exit"
            >
              Cerrar sesion
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>


    </div>
</template>
    <script>
    
import { actionStore } from "../../components/CheckerLocalStorage.js";
    
  export default {
    data: vm => ({
 
      drawer: null,
      dialog: false,
      dialog3: false,
      nombreUsuario:'',
      nombreEmpresa:'',
      nitEmpresa:'',
      tipoUsuario:''

    }),
    created () {
      //this.$vuetify.theme.dark = true
    },

    methods:{

      dash:function(){

        console.log('in here...');
        //router.push({ path: '/enduser/solicitud' })
      },
        exit:function(){

          console.log('exit')
            window.location.href = actionStore.methods.getEndpoints().URL_FRONTEND;
            localStorage.clear();
            

      },

     

    },
    mounted() {

      //console.log('calling..');
      this.nombreUsuario = actionStore.methods.getInfoEmpresa().username;
      this.nombreEmpresa = actionStore.methods.getInfoEmpresa().empresa;
      this.nitEmpresa = actionStore.methods.getInfoEmpresa().nitempresa;
      this.tipoUsuario = actionStore.methods.getInfoEmpresa().tipoRol;
     // console.log(actionStore.methods.getInfoEmpresa());

    }

  }
    
    </script>